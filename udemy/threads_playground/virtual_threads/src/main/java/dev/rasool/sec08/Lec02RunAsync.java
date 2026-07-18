package dev.rasool.sec08;

import dev.rasool.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class Lec02RunAsync {

    private static final Logger LOGGER = LoggerFactory.getLogger(Lec02RunAsync.class);

    static void main() {
        LOGGER.info("main starts");
        runAsync()
                .thenRun(() -> LOGGER.info("it is done"))
                .exceptionally(ex -> {
                    LOGGER.error("Problem: ", ex);
                    return null;
                });
        LOGGER.info("main ends");
        CommonUtils.sleep(Duration.ofSeconds(2));
    }

    private static CompletableFuture<Void> runAsync() {
        LOGGER.info("method starts");
        CompletableFuture<Void> taskCompleted = CompletableFuture.runAsync(() -> {
            CommonUtils.sleep(Duration.ofSeconds(1));
//            LOGGER.info("task completed"); // success case
            throw new RuntimeException("oops");
        }, Executors.newVirtualThreadPerTaskExecutor());
        // If we do not mention the executor (virtual) above, it uses the fork-join pool
        // which is costly. Virtual threads are not costly.
        LOGGER.info("method ends");
        return taskCompleted;
    }
}
