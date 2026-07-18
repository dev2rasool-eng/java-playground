package dev.rasool.sec08;

import dev.rasool.sec08.aggregator.AggregatorService;
import dev.rasool.sec08.aggregator.Product;
import dev.rasool.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class Lec05AggregatorDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(Lec05AggregatorDemo.class);

    static void main() {
        LOGGER.info("main starts");
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        var aggregator = new AggregatorService(executor);
        LOGGER.info("Product: {}", aggregator.getProduct(52));
    }

}
