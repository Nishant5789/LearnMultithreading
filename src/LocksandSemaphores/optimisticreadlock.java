package LocksandSemaphores;

import java.util.concurrent.locks.StampedLock;

public class optimisticreadlock {
    public static void main(String args[]) {
        SharedResource3 resource = new SharedResource3();

        Thread th1 = new Thread(() -> {
            resource.producer();
        });
        Thread th2 = new Thread(() -> {
            resource.consumer();
        });

        th1.start();
        th2.start();
    }
}

 class SharedResource3 {
    int a = 10;
    StampedLock lock = new StampedLock();

    public void producer() {
        long stamp = lock.tryOptimisticRead();
        System.out.println("Taken optimistic lock");
        a = 11;
        try {
            Thread.sleep(5000);
            if(lock.validate(stamp)){
                System.out.println("updated sucessfully");
            }
            else{
                System.out.println("roll back of work");
                a = 10;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void consumer() {
        long stamp = lock.writeLock();
        System.out.println("write lock accquired by : " + Thread.currentThread().getName());
        try {
            System.out.println("performing work");
            a=9;
        } finally {
            lock.unlockWrite(stamp);
            System.out.println("write lock release by: " + Thread.currentThread().getName());
        }
    }
}

