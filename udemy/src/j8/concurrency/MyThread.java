package j8.concurrency;

public class MyThread extends Thread{

    @Override
    public void run() {
        IO.println("run(): " + Thread.currentThread().getName());
    }

    static void main() {
        new MyThread().start();
        IO.println("main(): " + Thread.currentThread().getName());
    }
}
