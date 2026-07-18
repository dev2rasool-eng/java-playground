package dev.rasool.sec05;

import dev.rasool.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Lec01RaceCondition {

    private static final Logger LOGGER = LoggerFactory.getLogger(Lec01RaceCondition.class);

    private static final List<Integer> numbers = new ArrayList<>();

    static void main(String[] args) {
        demo(Thread.ofVirtual().name("Virtual-", 1));
        CommonUtils.sleep(Duration.ofSeconds(2));
        LOGGER.info("Numbers Size: {}", numbers.size());
    }

    private static void demo(Thread.Builder threadBuilder) {
        for (int i = 0; i < 50; i++) {
            int k = i;
            threadBuilder.start(() -> {
                LOGGER.info("Started Task {}. Thread {}", k, Thread.currentThread());
                for (int j = 0; j < 200; j++) {
                    inMemoryTask();
                }
                LOGGER.info("Ended Task {}. Thread {}", k, Thread.currentThread());
            });
        }
    }

    private static synchronized void inMemoryTask() {
        numbers.add(1);
    }
}
