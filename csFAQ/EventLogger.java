import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

// 假设这是底层提供的网络库（您不需要实现具体逻辑，只要调用它即可）
interface NetworkClient {
    // 模拟网络耗时操作
    void sendLogs(List<String> logs);
}

/**
 * EventLogger 是一个经典的「生产者-消费者」多线程模型实现。
 * 
 * 核心多线程概念：
 * 1. 线程安全队列 (LinkedBlockingQueue)：用于多个业务线程并发写入，不发生冲突。
 * 2. 状态可见性 (volatile & AtomicBoolean)：保证状态修改在多线程间立即可见。
 * 3. 阻塞与超时唤醒 (poll(timeout))：既能避免 CPU 空转，又能按时间间隔定时触发。
 * 4. 优雅关闭与资源防丢 (Graceful Shutdown)：在被中断退出时，确保缓冲区和队列中的数据 100% 刷盘。
 */
public class EventLogger {
    private final int batchSize;
    private final long flushIntervalMs;
    
    // volatile 保证该变量在多线程间的「内存可见性」，防止后台线程修改后，其他线程读到过期数据
    private volatile long lastSentTime = 0;

    private final NetworkClient networkClient;
    
    // LinkedBlockingQueue 是线程安全的阻塞队列，充当生产者和消费者之间的缓冲区
    private final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private final Thread workerThread;
    
    // AtomicBoolean 保证布尔值读写的原子性，用于控制 Logger 的生命周期状态
    private final AtomicBoolean lived = new AtomicBoolean(false);

    public EventLogger(int batchSize, long flushIntervalMs, NetworkClient client) {
        this.batchSize = batchSize;
        this.flushIntervalMs = flushIntervalMs;
        this.networkClient = client;
        this.lastSentTime = System.currentTimeMillis();
        this.lived.set(true);

        // 创建后台单线程消费者，专门负责从队列提取日志、组装 Batch 并通过网络发送
        this.workerThread = new Thread(() -> {
            // 局部变量 buffer，由于只在 workerThread 中被访问，因此它天然是线程安全的，不需要加锁
            List<String> buffer = new ArrayList<>(batchSize);

            // 只要系统处于活跃状态，或者队列中还有未消费完的数据，消费者就持续运转
            while (lived.get() || !queue.isEmpty()) {
                try {
                    // 计算距离上一次发送的时间差，动态决定 poll 的最大等待时间，实现定时刷新
                    long timeSinceLast = System.currentTimeMillis() - lastSentTime;
                    long waitTime = Math.max(0, flushIntervalMs - timeSinceLast);

                    // 阻塞式获取：如果在 waitTime 内没有新日志加入，则返回 null，解除阻塞并触发定时刷新检测
                    String log = queue.poll(waitTime, TimeUnit.MILLISECONDS);
                    if (log != null) {
                        buffer.add(log);
                    }

                    // 满足以下任一条件则触发批量发送：
                    // 1. 缓冲区满了 (buffer.size() >= batchSize)
                    // 2. 队列中积压了足够的数据 (queue.size() >= batchSize)
                    // 3. 距离上一次发送已经超过了指定时间间隔 (System.currentTimeMillis() - lastSentTime >= flushIntervalMs)
                    if (!buffer.isEmpty() && (
                            buffer.size() >= batchSize || 
                            queue.size() >= batchSize || 
                            System.currentTimeMillis() - lastSentTime >= flushIntervalMs)) {
                        
                        // 尽量从队列中一次性取满当前 Batch 剩余所需的额度，提升批量发送效率
                        queue.drainTo(buffer, batchSize - buffer.size());
                        flushBuffer(buffer);
                    }
                } catch (InterruptedException e) {
                    // 收到外界的中断信号（如 shutdown 时），重置中断标志位，跳出循环进入清理阶段
                    Thread.currentThread().interrupt();
                    break;
                } catch (Exception e) {
                    System.err.println("Error in worker thread: " + e.getMessage());
                }
            }

            // ================= 优雅退出保障 (Finally Clean Up) =================
            // 当循环因 lived=false 且队列为空，或者因被外部 interrupt 而退出时，
            // 必须保障 buffer 和 queue 中残留的日志不丢失。
            
            // 1. 发送局部缓存 buffer 中已被 poll 出但还没来得及发送的日志
            if (!buffer.isEmpty()) {
                flushBuffer(buffer);
            }
            // 2. 发送队列 queue 中残留的日志
            if (!queue.isEmpty()) {
                List<String> remaining = new ArrayList<>();
                queue.drainTo(remaining);
                flushBuffer(remaining);
            }
        }, "LogConsumerThread");

        this.workerThread.setDaemon(true); // 设置为守护（Daemon）线程，确保在 JVM 退出时不会因为此后台线程而阻塞
        this.workerThread.start();
    }

    /**
     * 业务方多线程调用的入口 (生产者)
     * 要求：尽可能快，决不能阻塞主线程
     */
    public void logEvent(String event) {
        if (!lived.get()) {
            System.err.println("Logger is shut down. Rejected event: " + event);
            return;
        }
        // queue.offer(event) 是非阻塞的线程安全写入操作。
        // 如果队列未满会立即返回 true。由于使用的是默认无界的 LinkedBlockingQueue，这里不会阻塞业务线程。
        queue.offer(event);
    }
    
    /**
     * 内部抽取出的统一发送并更新时间的方法
     */
    private void flushBuffer(List<String> buffer) {
        if (buffer.isEmpty()) return;
        try {
            networkClient.sendLogs(new ArrayList<>(buffer));
        } catch (Exception e) {
            System.err.println("Failed to send logs: " + e.getMessage());
        } finally {
            buffer.clear();
            lastSentTime = System.currentTimeMillis();
        }
    }
    
    /**
     * 手动触发刷盘的辅助接口
     */
    public void flush() {
        if (!queue.isEmpty()) {
            List<String> logs = new ArrayList<>();
            queue.drainTo(logs);
            networkClient.sendLogs(logs);
            lastSentTime = System.currentTimeMillis();
        }
    }
    
    /**
     * 优雅关闭（Graceful Shutdown）
     * 步骤：
     * 1. 将状态设为不可用（拒收新日志）。
     * 2. 对后台线程发送 interrupt 信号，唤醒它从阻塞的 poll 方法中退出。
     * 3. 阻塞等待后台线程消费并发送完所有历史残留数据。
     */
    public void shutdown() {
        if (!lived.compareAndSet(true, false)) {
            return; // 已经被关闭过了
        }
        
        // 中断后台线程（如果它正阻塞在 queue.poll()，会立刻抛出 InterruptedException 被唤醒）
        workerThread.interrupt();
        
        try {
            // 等待后台消费者线程彻底执行完其清理逻辑并死亡
            workerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Shutdown was interrupted while waiting for worker thread.");
        }

        // 双重保险：如果在 join 期间或之后队列又进入了零星日志，进行最后一次强行清空
        if (!queue.isEmpty()) {
            List<String> remaining = new ArrayList<>();
            queue.drainTo(remaining);
            if (!remaining.isEmpty()) {
                networkClient.sendLogs(remaining);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 1. 实现一个简单的 Mock 网络客户端来打印收到的日志
        NetworkClient mockClient = new NetworkClient() {
            private final AtomicInteger totalSent = new AtomicInteger(0);
            @Override
            public void sendLogs(List<String> logs) {
                if (logs.isEmpty()) return;
                int current = totalSent.addAndGet(logs.size());
                System.out.printf("│ [%s] 发 送 了  %d 条 日 志 ， 累 计 发 送 : %-15d │\n", 
                    Thread.currentThread().getName(), logs.size(), current);
            }
        };

        // 2. 初始化 Logger：满 100 条 或 过 1 秒发一次
        int batchSize = 100;
        long flushIntervalMs = 1000;
        EventLogger logger = new EventLogger(batchSize, flushIntervalMs, mockClient);

        // 3. 模拟 1000 个业务线程并发写入日志
        int threadCount = 1000;
        int logsPerThread = 10;
        int totalExpected = threadCount * logsPerThread;
        System.out.println("--- 模拟 " + threadCount + " 个线程并发写入 " + totalExpected + " 条日志 ---");

        // CountDownLatch 用于协调多线程，当所有 1000 个生产线程都运行完毕后才向下执行
        CountDownLatch latch = new CountDownLatch(threadCount);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < threadCount; i++) {
            final int id = i;
            new Thread(() -> {
                try {
                    for (int j = 0; j < logsPerThread; j++) {
                        logger.logEvent("Thread-" + id + "-event-" + j);
                    }
                } finally {
                    latch.countDown(); // 每个线程写完自己那份就打卡下班
                }
            }).start();
        }

        latch.await(); // 主线程在此阻塞，直到 latch 计数清零（即 1000 个线程全写完）
        long endTime = System.currentTimeMillis();
        System.out.println("--- 所有线程写入完毕，共计耗时: " + (endTime - startTime) + "ms ---");
        System.out.println("--- 等待一段时间让后台线程处理... ---");
        Thread.sleep(2000);

        // 4. 模拟系统优雅关闭
        System.out.println("--- 系统准备关闭 ---");
        logger.shutdown();

        System.out.println("--- 系统完全退出 ---");
    }
}
