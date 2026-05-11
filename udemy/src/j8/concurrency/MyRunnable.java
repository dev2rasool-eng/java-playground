package j8.concurrency;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        IO.println("run(): " + Thread.currentThread().getName());
    }

    static void main() {
        new Thread(new MyRunnable()).start();
        IO.println("main(): " + Thread.currentThread().getName());
    }
}
