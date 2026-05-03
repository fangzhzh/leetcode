import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

/**
 * 练习：手动实现一个简单的线程池 (SimpleThreadPool)
 * 
 * 目标：
 * 1. 理解线程复用 (Thread Reuse)
 * 2. 掌握 Worker 模式
 * 3. 理解阻塞队列在线程池中的核心作用
 */
public class SimpleThreadPool {
    // 任务队列：存放待执行的任务
    private final BlockingQueue<Runnable> taskQueue;
    // 工作线程列表
    private final List<Worker> workers;
    // 线程池状态
    private final AtomicBoolean isShutdown = new AtomicBoolean(false);

    public SimpleThreadPool(int threadCount, int queueSize) {
        this.taskQueue = new LinkedBlockingQueue<>(queueSize);
        this.workers = new ArrayList<>(threadCount);

        // TODO 1: 初始化并启动 threadCount 个 Worker 线程
        // 提示：创建 Worker 对象，将其加入 workers 列表，并调用 start()
    }

    /**
     * 提交任务
     */
    public void execute(Runnable task) {
        if (isShutdown.get()) {
            throw new IllegalStateException("ThreadPool is shut down");
        }
        // TODO 2: 将任务放入队列
        // 提示：使用 taskQueue.offer() 或 put()。如果是练习，可以先考虑 offer() 失败时的拒绝策略。
    }

    /**
     * 优雅关闭
     */
    public void shutdown() {
        isShutdown.set(true);
        // TODO 4: 中断所有工作线程
        for (Worker worker : workers) {
            worker.interrupt();
        }
    }

    /**
     * 内部工作类 (Worker)
     */
    private class Worker extends Thread {
        @Override
        public void run() {
            // TODO 3: 实现核心循环逻辑
            // 提示：只要线程没被中断且 (队列不为空 或 线程池未关闭)，就不断从队列中 take() 任务并运行
            /*
            while (!isInterrupted()) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    break;
                }
            }
            */
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 测试我们的线程池
        System.out.println("--- 启动 SimpleThreadPool (2 个核心线程) ---");
        SimpleThreadPool pool = new SimpleThreadPool(2, 10);

        // 提交 5 个任务
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            pool.execute(() -> {
                System.out.println("│ [" + Thread.currentThread().getName() + "] 正在执行任务 " + taskId);
                try {
                    Thread.sleep(500); // 模拟耗时操作
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        Thread.sleep(2000);
        System.out.println("--- 准备关闭线程池 ---");
        pool.shutdown();
        System.out.println("--- 线程池已关闭 ---");
    }
}
