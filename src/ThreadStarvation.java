public class ThreadStarvation {
    public static void main(String[] args) throws InterruptedException {

        Resource resource = new Resource();
        System.out.println("main thread started");

        Thread th1 = new Thread(()->{
            System.out.println("theard1 calling produce method");
            try {
                resource.produce(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread th2 = new Thread(()->{
            System.out.println("theard2 calling produce method");
            try {
                Thread.sleep(3000);
                resource.produce(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        th1.start();
        th2.start();

        System.out.println("thread1 is suspended");
        th1.suspend();
        th1.resume();

        th1.join();
        th2.join();
//        th1.setDaemon(true);
//        used in logging, autosave, garbage collector
        System.out.println("Main thread is finishing its work");
    }
}

class Resource {
    boolean isAvailable = false;

    public synchronized void produce(int threadno) throws InterruptedException {
        System.out.println("Lock acquired"+threadno);
        isAvailable=true;
        Thread.sleep(4000);
        System.out.println("lock release"+threadno);
    }
}


