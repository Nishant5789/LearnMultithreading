package LocksandSemaphores;

import java.util.concurrent.locks.StampedLock;

public class StamedLock {
    public static void main(String args[]) {
        SharedResource4 resource = new SharedResource4();

        Thread th1 = new Thread(() -> {
            resource.producer();
        });

        Thread th2 = new Thread(() -> {
            resource.producer();
        });

        Thread th3 = new Thread(() -> {
            resource.consume();
        });

        th3.start();
        th1.start();
        th2.start();
    }
}

class SharedResource4 {
    StampedLock lock = new StampedLock();

    public void producer() {
        long stamp = lock.readLock();
        try {
            System.out.println("Read Lock acquired by: " + Thread.currentThread().getName());
            Thread.sleep(6000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlockRead(stamp);
            System.out.println("Read Lock released by: " + Thread.currentThread().getName());
        }
    }

    public void consume() {
        long stamp = lock.writeLock();
        try {
            System.out.println("Write Lock acquired by: " + Thread.currentThread().getName());
            Thread.sleep(4000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlockWrite(stamp);
            System.out.println("Write Lock released by: " + Thread.currentThread().getName());
        }
    }
}






