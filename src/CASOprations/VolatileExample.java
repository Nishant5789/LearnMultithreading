package CASOprations;


class SharedObj{
    private volatile boolean  flag = false;

    public void setFlagTrue() {
        System.out.println("writer thread made the flag true...");
        this.flag = true;
    }
    public  void printIfFlagTrue(){
        while(!flag){
            // do nothing
        }
        System.out.println("Flag is true...");
    }
}
public class VolatileExample {
    public static void main(String[] args) {
        SharedObj sharedObj = new SharedObj();

        Thread writerThread = new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sharedObj.setFlagTrue();
        });
        Thread readerThread = new Thread(()->{
            sharedObj.printIfFlagTrue();
        });

        writerThread.start();
        readerThread.start();

    }
}
