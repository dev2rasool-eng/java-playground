package dev.rasool.sec08;

import dev.rasool.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class Lec03SupplyAsync {

    private static final Logger LOGGER = LoggerFactory.getLogger(Lec03SupplyAsync.class);

    static void main() {
        LOGGER.info("main starts");
        supplyAsync()
                .thenAccept(s -> LOGGER.info("Result: {}", s));
        LOGGER.info("main ends");
        CommonUtils.sleep(Duration.ofSeconds(2));
    }

    private static CompletableFuture<String> supplyAsync() {
        LOGGER.info("method starts");
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Supply Async");
            CommonUtils.sleep(Duration.ofSeconds(1));
            return "hi";
        }, Executors.newVirtualThreadPerTaskExecutor());
        LOGGER.info("method ends");
        return cf;
    }
}
