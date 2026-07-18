package dev.rasool.sec04;

import dev.rasool.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class CooperativeSchedulingDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(CooperativeSchedulingDemo.class);

    static {
        System.setProperty("jdk.virtualThreadScheduler.parallelism", "1");
        System.setProperty("jdk.virtualThreadScheduler.maxPoolSize", "1");
    }

    static void main(String[] args) {
        Thread.Builder.OfVirtual virtualBuilder = Thread.ofVirtual();
        virtualBuilder.start(() -> demo(1));
        virtualBuilder.start(() -> demo(2));
        virtualBuilder.start(() -> demo(3));

        CommonUtils.sleep(Duration.ofSeconds(2));
    }

    private static void demo(int threadNumber) {
        LOGGER.info("thread-{} started", threadNumber);
        for (int i = 0; i < 10; i++) {
            LOGGER.info("thread-{} is printing {}. Thread: {}",
                    threadNumber, i, Thread.currentThread());
            Thread.yield();
        }
        LOGGER.info("thread-{} ended", threadNumber);
    }

}
