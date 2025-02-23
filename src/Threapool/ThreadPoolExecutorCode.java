package Threapool;
import java.util.concurrent.*;

public class ThreadPoolExecutorCode {
    public static void main(String[] args) {
//        Future
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,              // corePoolSize
                4,                           // maximumPoolSize
                1,                           // keepAliveTime
                TimeUnit.MINUTES,            // timeUnit for keepAliveTime
                new ArrayBlockingQueue<>(2), // capacity of the queue
                new CustomThreadFactory(),
                new CustomRejectedHandler()
        );

        threadPoolExecutor.allowCoreThreadTimeOut(true);
//        try with value 5 and 7 and figure out yor answer

//        if you select 5 then 2 task work on thread pool and 2 task added in queue and now
//        threadpoolexecutor need to create one more thread for task 5
//        if you select 7 then for task 7 is reject and handle by CustomRejectedHandler policy

        for (int i = 1; i <= 7; i++) {
            final int taskId = i;
            threadPoolExecutor.submit(() -> {
                try {
                    System.out.println("Task " + taskId + " is running on " + Thread.currentThread().getName());
                    Thread.sleep(4000); // Simulate task
                } catch (InterruptedException e) {
                    System.out.println("Task " + taskId + " was interrupted.");
                }
            });
        }
        // Shut down the thread pool
        threadPoolExecutor.shutdown();
    }
}

class CustomRejectedHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        // Logging
        System.out.println("Task denied: " + r.toString());
    }
}

class CustomThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        return t;
    }
}

