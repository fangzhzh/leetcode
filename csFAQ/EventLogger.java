import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

// 假设这是底层提供的网络库（您不需要实现具体逻辑，只要调用它即可）
interface NetworkClient {
    // 模拟网络耗时操作
    void sendLogs(List<String> logs);
}

public class EventLogger {
    private final int batchSize;
    private final long flushIntervalMs;
    private volatile long lastSentTime = 0;

    private final NetworkClient networkClient;
    LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<> ();
    Thread thread;
    AtomicBoolean lived = new AtomicBoolean(false);


    
    // TODO: 定义您需要的内部数据结构（如 Queue, 锁, 定时器等）

    public EventLogger(int batchSize, long flushIntervalMs, NetworkClient client) {
        this.batchSize = batchSize;
        this.flushIntervalMs = flushIntervalMs;
        this.networkClient = client;
        this.lastSentTime = System.currentTimeMillis();
        lived.set(true);
        List<String> buffer = new ArrayList<>(batchSize);
        thread = new Thread(()-> {
            while(lived.get() || !queue.isEmpty()) {
                try{
                    long timeSinceLast = System.currentTimeMillis() - lastSentTime;
                    long waitTime = Math.max(0, flushIntervalMs - timeSinceLast);
                    String first = queue.poll(waitTime, TimeUnit.MILLISECONDS);
                    if(first != null) {
                        buffer.add(first);
                    }
                    if(!buffer.isEmpty() && (buffer.size() >= batchSize || queue.size() >= batchSize || lastSentTime + flushIntervalMs < System.currentTimeMillis())) {
                        queue.drainTo(buffer, batchSize - buffer.size());
                        networkClient.sendLogs(new ArrayList<>(buffer));
                        buffer.clear();
                        lastSentTime = System.currentTimeMillis();
                    }
                } catch(Exception e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        thread.start();
        // TODO: 初始化您的数据结构 and 后台线程
    }

    /**
     * 业务方多线程调用的入口
     * 要求：尽可能快，不阻塞主线程
     */
    public void logEvent(String event) {
        if(!lived.get()) return;
        queue.offer(event);
        // TODO: 实现日志写入逻辑
    }
    
    /**
     * 内部触发的批量发送逻辑
     */
    private void flush() {
         if(!queue.isEmpty()) {
            List<String> logs = new ArrayList<>();
            queue.drainTo(logs);
            networkClient.sendLogs(logs);
        }
        // TODO: 取出积攒的日志，调用 networkClient.sendLogs() 发送
    }
    
    /**
     * 优雅关闭（可选，但写出来会很加分）
     */
    public void shutdown() {
        lived.set(false);
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if(!queue.isEmpty()) {
            List<String> logs = new ArrayList<>();
            queue.drainTo(logs);
            networkClient.sendLogs(logs);
        }
        // TODO: 停止接收新日志，并将缓存中剩余的日志发送出去
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

        CountDownLatch latch = new CountDownLatch(threadCount);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    System.out.println("--- 线程 " + Thread.currentThread().getName() + " 发送日志 ");
                    for (int j = 0; j < logsPerThread; j++) {
                        logger.logEvent(Thread.currentThread().getName() + "-event-" + j);
                    }
                } finally {
                    latch.countDown();
                }
            }).start();
        }

        latch.await();
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
