package ProducerandConsumer;

public class SharedResource {
    boolean isItemPresent = false;

    //    put the monitor lock on this instace of class
    public synchronized void addItem(){
        isItemPresent=true;
        System.out.println("item added by " + Thread.currentThread().getName()+ "and invoking all threads which are blocked ");
        notifyAll();
    }

    public synchronized void consumeItem(){
        System.out.println("consume item method invoked by :"+ Thread.currentThread().getName());
        // avoid suporious wakeup
        while (!isItemPresent){
            try {
                System.out.println("Thread "+ Thread.currentThread().getName()+" is waiting now");
                wait(); // it release the monitor locks
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("consume");
        isItemPresent = false;
    }
}
