package CASOprations;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomicvariables {
    public static void main(String[] args) throws InterruptedException {

//        excuteincremantwithoutthread();
        excuteincremantwiththread();
    }

    public static void excuteincremantwithoutthread() {
        SharedResourcewithoutAtomicvariable resource = new SharedResourcewithoutAtomicvariable();
        for (int i = 0; i < 10000; i++) {
            resource.increment();
        }
        System.out.println(resource.get());

    }

    public static void excuteincremantwiththread() throws InterruptedException {
//        SharedResourcewithoutAtomicvariable resource = new SharedResourcewithoutAtomicvariable();
        SharedResourcewithAtomicvariable resource = new SharedResourcewithAtomicvariable();

        Thread t1 = new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                resource.increment();
            }
        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                resource.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(resource.get());
    }
}
class SharedResourcewithAtomicvariable {
    AtomicInteger counter = new AtomicInteger(0);
    public void increment() {
        counter.incrementAndGet();
    }
    public int get() {
        return counter.get();
    }
}


class SharedResourcewithoutAtomicvariable {
    int counter;
    public void increment() {
        counter++;
    }
    public int get() {
        return counter;
    }
}

