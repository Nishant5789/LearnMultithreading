package Threapool;
import java.util.concurrent.*;

public class FutureExample {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                1,
                1,
                1,
                TimeUnit.HOURS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        Future<?> futureObj = poolExecutor.submit(() -> {
            try {
                Thread.sleep(7000);
                System.out.println("This is the task, which thread will execute");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        System.out.println("is Done: " + futureObj.isDone());

        try {
            futureObj.get(2, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("TimeoutException happened");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            futureObj.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("is Done: " + futureObj.isDone());
        System.out.println("is Cancelled: " + futureObj.isCancelled());

        poolExecutor.shutdown();
    }
}

