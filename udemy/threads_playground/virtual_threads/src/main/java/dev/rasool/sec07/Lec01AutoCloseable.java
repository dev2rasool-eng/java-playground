package dev.rasool.sec07;

import dev.rasool.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lec01AutoCloseable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Lec01AutoCloseable.class);

    static void main(String[] args) {
        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            executorService.submit(() -> task());
            executorService.submit(() -> task());
            executorService.submit(() -> task());
            executorService.submit(() -> task());
            LOGGER.info("submitted");
        }
    }

    public static void task() {
        CommonUtils.sleep(Duration.ofSeconds(1));
        LOGGER.info("task executed");
    }


}
