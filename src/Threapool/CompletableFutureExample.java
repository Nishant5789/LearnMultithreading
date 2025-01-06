package Threapool;

import java.util.concurrent.*;

public class CompletableFutureExample {
    public static void main(String[] args) {
        try {
            ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                    1,
                    1,
                    1,
                    TimeUnit.HOURS,
                    new ArrayBlockingQueue<>(10),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.AbortPolicy()
            );
//            CompletableFuture<String> asyncTask = CompletableFuture.supplyAsync(() -> {
//                // "this is the task which needs to be completed by thread"
//                return "task completed";
//            }, poolExecutor)
//            System.out.println(asyncTask.get());

            CompletableFuture<String> asyncTask1 = CompletableFuture.supplyAsync(() -> {
                // "this is the task which needs to be completed by thread"
                System.out.println("thread name which runs supplyasync"+ Thread.currentThread().getName());
                return "Nishant ";
            }, poolExecutor).thenApply((String val)->{
                // funtionality which can work on the result of previous async  task
                System.out.println("thread name which runs apply"+ Thread.currentThread().getName());
                return val + "bhandari";
            });
            System.out.println(asyncTask1.get());

            CompletableFuture<String> asyncTask2 = CompletableFuture.supplyAsync(() -> {
                // "this is the task which needs to be completed by thread"
                System.out.println("thread name which runs supplyasync"+ Thread.currentThread().getName());
                return "Nishant ";
            }, poolExecutor).thenApplyAsync((String val)->{
                // funtionality which can work on the result of previous async  task
                System.out.println("thread name which runs applyasync"+ Thread.currentThread().getName());
                return val + "Milanbhai ";
            }).thenApplyAsync((String val)->{
                // funtionality which can work on the result of previous async  task
                System.out.println("thread name which runs apply async"+ Thread.currentThread().getName());
                return val + "bhandari";
            });
            System.out.println(asyncTask2.get());

//            ----------------------------------
            CompletableFuture<String> asyncTask3 = CompletableFuture
                    .supplyAsync(() -> {
                        System.out.println("Thread Name which runs 'supplyAsync': " + Thread.currentThread().getName());
                        return "Nishant";
                    }, poolExecutor)
                    .thenCompose((String val) -> {
                        return CompletableFuture.supplyAsync(() -> {
                            System.out.println("Thread Name which runs 'thenCompose': " + Thread.currentThread().getName());
                            return val + " Milanbhai";
                        });
                    }).thenCompose((String val) -> {
                        return CompletableFuture.supplyAsync(() -> {
                            System.out.println("Thread Name which runs 'thenCompose': " + Thread.currentThread().getName());
                            return val + " Bhandari";
                        });
                    });

            asyncTask3.thenAccept(result -> System.out.println("Result: " + result));
            // First Async Task
            CompletableFuture<Integer> asyncTask4 = CompletableFuture
                    .supplyAsync(() -> {
                        System.out.println("Thread running asyncTask1: " + Thread.currentThread().getName());
                        return 10;
                    }, poolExecutor);

            // Second Async Task
            CompletableFuture<String> asyncTask5 = CompletableFuture
                    .supplyAsync(() -> {
                        System.out.println("Thread running asyncTask2: " + Thread.currentThread().getName());
                        return " K";
                    }, poolExecutor);

            // Combining results of both tasks
            CompletableFuture<String> combinedFutureObj = asyncTask4.thenCombine(
                    asyncTask5,
                    (Integer val1, String val2) -> val1 + val2
            );

            // Consuming the combined result
            combinedFutureObj.thenAccept(result -> System.out.println("Combined Result: " + result));

            poolExecutor.shutdown();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
