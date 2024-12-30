package LocksandSemaphores;

import java.util.concurrent.Semaphore;

public class Semaphores {
    public static void main(String args[]) {
        SharedResource resource = new SharedResource();

        Thread th1 = new Thread(() -> {
            resource.producer();
        });

        Thread th2 = new Thread(() -> {
            resource.producer();
        });

        Thread th3 = new Thread(() -> {
            resource.producer();
        });

        Thread th4 = new Thread(() -> {
            resource.producer();
        });

        th1.start();
        th2.start();
        th3.start();
        th4.start();
    }
}

class SharedResource {
    boolean isAvailable = false;
    Semaphore lock = new Semaphore(2); // Allows 2 permits

    public void producer() {
        try {
            lock.acquire();
            System.out.println("Lock acquired by: " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(4000); // Simulate some work
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.release();
            System.out.println("Lock released by: " + Thread.currentThread().getName());
        }
    }
}

