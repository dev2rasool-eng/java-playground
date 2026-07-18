package dev.rasool.sec08;

import dev.rasool.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class Lec01SimpleCompletableFuture {

    private static final Logger LOGGER = LoggerFactory.getLogger(Lec01SimpleCompletableFuture.class);

    static void main() {
        LOGGER.info("main starts");
        CompletableFuture<String> cf = slowTask();
//        String result = cf.join(); // blocking
        cf.thenAccept(s -> LOGGER.info("Result: {}", s)); // non-blocking
//        LOGGER.info("Result: {}", result);
        LOGGER.info("main ends");
        CommonUtils.sleep(Duration.ofSeconds(2));
    }

    private static CompletableFuture<String> fastTask() {
        LOGGER.info("method starts");
        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete("Hello!");
        LOGGER.info("method ends");
        return cf;
    }

    private static CompletableFuture<String> slowTask() {
        LOGGER.info("method starts");
        CompletableFuture<String> cf = new CompletableFuture<>();
        Thread.ofVirtual().start(() -> {
            CommonUtils.sleep(Duration.ofSeconds(1));
            cf.complete("Hello!");
        });
        LOGGER.info("method ends");
        return cf;
    }
}
