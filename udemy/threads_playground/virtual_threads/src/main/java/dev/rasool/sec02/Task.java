package dev.rasool.sec02;

import dev.rasool.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.Duration;

public class Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);

    public static void execute(int i) {
        LOGGER.info("starting task {}", i);
        try {
            method1(i);
        } catch (Exception e) {
            LOGGER.error("error occurred for task {}", i, e);
        }
        LOGGER.info("ending task {}", i);
    }

    private static void method1(int i) {
        CommonUtils.sleep(Duration.ofMillis(300));
        try {
            method2(i);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void method2(int i) {
        CommonUtils.sleep(Duration.ofMillis(100));
        method3(i);
    }

    private static void method3(int i) {
        CommonUtils.sleep(Duration.ofMillis(500));
        if (i == 4) {
            throw new IllegalArgumentException("i cannot be 4");
        }
    }
}
