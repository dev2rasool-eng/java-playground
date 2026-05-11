package j8.concurrency;

public class MyRunnableUsingLambda {

    static void main() {
        Thread t = new Thread(() -> IO.println("run(): " + Thread.currentThread().getName()));
        t.start();
        IO.println("main(): " + Thread.currentThread().getName());
    }
}
