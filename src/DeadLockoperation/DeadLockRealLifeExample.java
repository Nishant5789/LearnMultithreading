package DeadLockoperation;

public class DeadLockRealLifeExample {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(8000);
        Product product = new Product();
        Checkout checkout = new Checkout();

        Product.checkout = checkout;
        Checkout.product = product;

        Thread customerOneThread = new Thread(new CustomerOne(product, checkout), "CustomerOneThread");
        Thread customerTwoThread = new Thread(new CustomerTwo(product, checkout), "CustomerTwoThread");

        customerOneThread.start();
        customerTwoThread.start();
    }
}

class Product {
    static Checkout checkout;
    public synchronized void confirmPurchase() {
        System.out.println(Thread.currentThread().getName() + " Confirming product purchase");
        try {
            System.out.println(Thread.currentThread().getName() + " Went away to another Site");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " Continuing process and now need to process the payment");
        checkout.processPayment();
    }

    public synchronized void modifyStock() {
        System.out.println(Thread.currentThread().getName() + " Modifying stock in product database.");
    }
}

class Checkout {
    static Product product;

    public synchronized void processPayment() {
        System.out.println(Thread.currentThread().getName() + " Started payment process");
        try {
            System.out.println(Thread.currentThread().getName() + " Taking some time to verify payment with partner bank");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "Completed the payment now need to modify the stock");
        product.modifyStock();
    }
}

class CustomerOne implements Runnable {

    Product product;
    Checkout checkout;

    public CustomerOne(Product product, Checkout checkout) {
        this.product = product;
        this.checkout = checkout;
    }

    public void run() {
        /* Customer One first buys the product and then process the payment*/
        product.confirmPurchase();
    }
}

class CustomerTwo implements Runnable {

    Product product;
    Checkout checkout;

    public CustomerTwo(Product product, Checkout checkout) {
        this.product = product;
        this.checkout = checkout;
    }

    public void run() {
        /*
         * Customer Two already added product to the cart and now directly
         * processing the payment.
         */
        checkout.processPayment();
    }
}

