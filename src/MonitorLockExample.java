public class MonitorLockExample {
    public static void main(String[] args) {
        Monitorlock obj = new Monitorlock();
        Thread t1 = new Thread(()->{obj.task1();});
        Thread t2 = new Thread(()->{obj.task2();});
        Thread t3 = new Thread(()->{obj.task3();});

        t1.start();
        t3.start();
        t2.start();
    }
}

 class Monitorlock {
     public synchronized void task1() {
         System.out.println("inside task 1");
         try {
             Thread.sleep(4000);
         } catch (InterruptedException e) {
             throw new RuntimeException(e);
         }
         System.out.println("task 1 completed");
     }

     public void task2() {
         System.out.println("task2, before synchronized");
         synchronized (this) {
             System.out.println("task 2 inside synchronized");
         }
         System.out.println("task 2 completed");
     }

     public void task3() {
         System.out.println("task3");
     }
 }






