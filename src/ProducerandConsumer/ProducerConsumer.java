package ProducerandConsumer;

public class ProducerConsumer {
    public static void main(String[] args) {

        SharedResource SharedResourceObj = new SharedResource();
        Thread producerthread = new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            SharedResourceObj.addItem();
        });
        Thread consumerthread = new Thread(()->{
            SharedResourceObj.consumeItem();
        });

        producerthread.start();
        consumerthread.start();

    }
}

