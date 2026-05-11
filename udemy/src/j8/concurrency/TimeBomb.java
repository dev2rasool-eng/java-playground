package j8.concurrency;

class CountDown implements Runnable {

    @Override
    public void run() {
        String[] timeStr = { "nine", "eight", "seven", "six", "five", "four", "three", "two", "one"};

        for (String time : timeStr) {
            IO.println(time);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

/**
 * Sample code to demonstrate sleep() and join()
 */
public class TimeBomb {

    static void main() {
        Thread timer = new Thread(new CountDown());
        IO.println("Starting 10 countdown...");
        timer.start();
        try {
            timer.join();  // main thread waits till the "countDown" thread (timer) completes
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        IO.println("Boom!!!");
    }
}
