package ProducerandConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResourceWithBuffer {
    private Queue<Integer> sharedBuffer;
    private int buffersize;

    public SharedResourceWithBuffer(int buffersize) {
        this.buffersize = buffersize;
        sharedBuffer = new LinkedList<>();
    }

    public synchronized void produce(int item) throws InterruptedException {
        while(sharedBuffer.size() == buffersize){
            System.out.println("Buffer is full, Producre is waiting or consumer");
            wait();
        }
        sharedBuffer.add(item);
        System.out.println("Produced: "+item);
        notify();
    }

    public synchronized int consume() throws InterruptedException {
        while(sharedBuffer.isEmpty()){
            System.out.println("buffer is empty, consume is waiting for producer");
            wait();
        }
        int item = sharedBuffer.poll();
        System.out.println("consumed: " + item);
        notify();
        return item;
    }
}
