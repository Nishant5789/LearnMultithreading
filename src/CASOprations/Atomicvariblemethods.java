package CASOprations;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomicvariblemethods {
    public static void main(String[] args) {
        // Initialize AtomicInteger
        AtomicInteger atomicInteger = new AtomicInteger(10);

        System.out.println("Initial Value: " + atomicInteger.get()); // get()

        // set() method
        atomicInteger.set(20);
        System.out.println("After set(20): " + atomicInteger.get());

        // lazySet() method
        atomicInteger.lazySet(25);
        System.out.println("After lazySet(25): " + atomicInteger.get());

        // compareAndSet() method
        boolean isUpdated = atomicInteger.compareAndSet(25, 30);
        System.out.println("After compareAndSet(25, 30): " + atomicInteger.get() + " | Was Updated? " + isUpdated);

        // getAndSet() method
        int oldValue = atomicInteger.getAndSet(35);
        System.out.println("After getAndSet(35): Old Value: " + oldValue + ", Current Value: " + atomicInteger.get());

        // Increment and Decrement methods
        System.out.println("After incrementAndGet(): " + atomicInteger.incrementAndGet());
        System.out.println("After getAndIncrement(): " + atomicInteger.getAndIncrement() + ", Current Value: " + atomicInteger.get());
        System.out.println("After decrementAndGet(): " + atomicInteger.decrementAndGet());
        System.out.println("After getAndDecrement(): " + atomicInteger.getAndDecrement() + ", Current Value: " + atomicInteger.get());

        // Add and Accumulate methods
        System.out.println("After addAndGet(10): " + atomicInteger.addAndGet(10));
        System.out.println("After getAndAdd(5): " + atomicInteger.getAndAdd(5) + ", Current Value: " + atomicInteger.get());

        // updateAndGet() method
        int updatedValue = atomicInteger.updateAndGet(x -> x * 2); // Multiply current value by 2
        System.out.println("After updateAndGet(x -> x * 2): " + updatedValue);

        // getAndUpdate() method
        int oldUpdatedValue = atomicInteger.getAndUpdate(x -> x - 10); // Subtract 10 from current value
        System.out.println("After getAndUpdate(x -> x - 10): Old Value: " + oldUpdatedValue + ", Current Value: " + atomicInteger.get());

        // accumulateAndGet() method
        int accumulatedValue = atomicInteger.accumulateAndGet(3, (current, x) -> current + x * 2); // Add (3 * 2) to current value
        System.out.println("After accumulateAndGet(3, (current, x) -> current + x * 2): " + accumulatedValue);

        // getAndAccumulate() method
        int oldAccumulatedValue = atomicInteger.getAndAccumulate(4, (current, x) -> current * x); // Multiply current value by 4
        System.out.println("After getAndAccumulate(4, (current, x) -> current * x): Old Value: " + oldAccumulatedValue + ", Current Value: " + atomicInteger.get());
    }
}
