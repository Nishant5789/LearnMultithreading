package ProducerandConsumer;

public class ProducerConsumerWithBuffer {

    public static void main(String[] args) {
        SharedResourceWithBuffer sharedBuffer = new SharedResourceWithBuffer(4);

        Thread producerthread = new Thread(()->{
            for (int i = 1; i <=10; i++) {
                try {
                    sharedBuffer.produce(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread consumethread = new Thread(()->{
            for (int i = 1; i <=10; i++) {
                try {
                    sharedBuffer.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        producerthread.start();
        consumethread.start();
    }
}

