package dev.rasool.sec07;

import dev.rasool.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Lec02ExecutorServiceTypes {

    private static final Logger LOGGER = LoggerFactory.getLogger(Lec02ExecutorServiceTypes.class);

    static void main() {
        virtual();
    }

    private static void single() {
        execute(Executors.newSingleThreadExecutor(), 5);
    }

    private static void fixed() {
        execute(Executors.newFixedThreadPool(5), 20);
    }

    private static void cached() {
        execute(Executors.newCachedThreadPool(), 20);
    }

    private static void scheduled() {
//        try (var executorService = Executors.newScheduledThreadPool(5)) {
        try (var executorService = Executors.newSingleThreadScheduledExecutor()) {
            executorService.scheduleAtFixedRate(() -> ioTask(1), 0, 1, TimeUnit.SECONDS);
            LOGGER.info("submitted");
            CommonUtils.sleep(Duration.ofSeconds(5));
        }
    }

    private static void virtual() {
        execute(Executors.newVirtualThreadPerTaskExecutor(), 100);
    }

    private static void execute(ExecutorService executorService, int taskCount) {
        try (executorService) {
            for (int i = 0; i < taskCount; i++) {
                int finalI = i;
                executorService.submit(() -> ioTask(finalI));
            }
            LOGGER.info("submitted");
        }
    }

    private static void ioTask(int i) {
        LOGGER.info("Task started: {}. Thread info {}", i, Thread.currentThread());
        CommonUtils.sleep(Duration.ofSeconds(3));
        LOGGER.info("Task ended: {}. Thread info {}", i, Thread.currentThread());
    }


}
