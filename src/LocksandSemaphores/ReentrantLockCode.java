package LocksandSemaphores;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCode {
    public static void main(String[] args) {
         ReentrantLock lock = new ReentrantLock();
        SharedResource1 resource1 = new SharedResource1();
        SharedResource1 resource2 = new SharedResource1();

        Thread t1 = new Thread(() -> {
            resource1.producer(lock);
        });

        Thread t2 = new Thread(() -> {
            resource2.producer(lock);
        });

        t1.start();
        t2.start();
    }
}

class SharedResource1 {

    public  void producer(ReentrantLock lock) {
        lock.lock();
        try {
            System.out.println("Lock acquired by: " + Thread.currentThread().getName());
            Thread.sleep(4000); // Simulating some work
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("Lock released by: " + Thread.currentThread().getName());
        }
    }
}
