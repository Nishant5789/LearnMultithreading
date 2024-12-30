//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
class Myrunnabletask implements Runnable{
    @Override
    public void run() {
        System.out.println("Thread is running: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        Myrunnabletask task = new Myrunnabletask();

        Thread thread = new Thread(task);
        thread.start();

        System.out.println("Main thread: " + Thread.currentThread().getId());
    }
}