package micellonous;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        int numTasks = 3;
        CountDownLatch latch = new CountDownLatch(numTasks);

        for (int i = 1; i <= numTasks; i++) {
            new Thread(new Worker(latch, "Task-" + i)).start();
        }

        System.out.println("Main thread waiting for tasks to complete...");
        latch.await(); // Wait until count reaches 0
        System.out.println("All tasks completed. Main thread resumes.");
    }
}

class Worker implements Runnable {
    private CountDownLatch latch;
    private String taskName;

    public Worker(CountDownLatch latch, String taskName) {
        this.latch = latch;
        this.taskName = taskName;
    }

    public void run() {
        try {
            System.out.println(taskName + " is executing...");
            Thread.sleep(2000); // Simulating work
            System.out.println(taskName + " finished.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown(); // Decrease count
        }
    }
}
