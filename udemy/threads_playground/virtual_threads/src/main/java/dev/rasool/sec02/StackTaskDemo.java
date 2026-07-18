package dev.rasool.sec02;

import dev.rasool.util.CommonUtils;

import java.time.Duration;

public class StackTaskDemo {

    static void main() {
        // Execute Platform Thread
//        execute(Thread.ofPlatform());

        // Execute Virtual Thread
        execute(Thread.ofVirtual().name("Virtual-", 1));
        CommonUtils.sleep(Duration.ofSeconds(2)); // This is needed to see the result as Virtual runs as Daemon or countdownlatch
    }

    public static void execute(Thread.Builder threadBuilder) {
        for (int i = 1; i < 20; i++) {
            int j = i;
            threadBuilder.start(() -> Task.execute(j));
        }
    }

}
