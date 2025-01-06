package LocksandSemaphores;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCode {
    public static void main(String[] args) {
        SharedResource1 resource1 = new SharedResource1();
        SharedResource1 resource2 = new SharedResource1();

        Thread t1 = new Thread(() -> {
            resource1.producer();
        });

        Thread t2 = new Thread(() -> {
            resource2.producer();
        });

        t1.start();
        t2.start();
    }
}

class SharedResource1 {
    private boolean isAvailable = false;
    private ReentrantLock lock = new ReentrantLock();

    public  void producer() {
        try {
            lock.lock();
            System.out.println("Lock acquired by: " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(4000); // Simulating some work
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("Lock released by: " + Thread.currentThread().getName());
        }
    }
}
