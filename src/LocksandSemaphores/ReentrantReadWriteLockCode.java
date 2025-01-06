package LocksandSemaphores;

import java.util.concurrent.locks.ReentrantReadWriteLock;

//The provided image showcases the use of the ReadWriteLock in Java, which is a synchronization mechanism used
// for optimizing thread-safe operations. Below is the code based on the image and its concepts:
//Concept: ReadWriteLock
//Read Lock: Allows multiple threads to acquire the lock concurrently for reading, as long as no thread holds the write lock.
//Write Lock: Allows only one thread to acquire the lock for writing, and no other thread (reader or writer) can acquire
// the lock simultaneously.
//This makes it ideal for scenarios where:

//Multiple threads read shared data frequently.
//Write operations are rare but require exclusive access.


public class ReentrantReadWriteLockCode {
    public static void main(String[] args) {
        SharedResource2 resource = new SharedResource2();
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        Thread t1 = new Thread(() -> {
            resource.producer(lock);
        });
        Thread t2 = new Thread(() -> {
            resource.producer(lock);
        });

        SharedResource2 resource1 = new SharedResource2();
        Thread t3 = new Thread(() -> {
            resource1.consume(lock);
        });

        t1.start();
        t2.start();
        t3.start();
    }
}

class SharedResource2 {

    public void producer(ReentrantReadWriteLock lock) {
        try {
            lock.readLock().lock();
            System.out.println("Read Lock acquired by: " + Thread.currentThread().getName());
            Thread.sleep(3000); // Simulate some work
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
            System.out.println("Read Lock released by: " + Thread.currentThread().getName());
        }
    }

    public void consume(ReentrantReadWriteLock lock) {
        try {
            lock.writeLock().lock();
            System.out.println("Write Lock acquired by: " + Thread.currentThread().getName());
            Thread.sleep(3000); // Simulate some work
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
            System.out.println("Write Lock released by: " + Thread.currentThread().getName());
        }
    }
}

