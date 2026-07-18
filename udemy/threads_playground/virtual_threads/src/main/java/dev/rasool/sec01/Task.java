package dev.rasool.sec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);

    public static void toIntensive(int i) {
        try {
            LOGGER.info("starting I/O task {}. Thread Info {}", i, Thread.currentThread());
            Thread.sleep(Duration.ofSeconds(10));
            LOGGER.info("ending I/O task {}. Thread Info {}", i, Thread.currentThread());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
