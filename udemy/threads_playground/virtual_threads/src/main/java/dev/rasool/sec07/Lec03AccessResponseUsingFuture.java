package dev.rasool.sec07;

import dev.rasool.sec07.external_service.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Lec03AccessResponseUsingFuture {

    private static final Logger LOGGER = LoggerFactory.getLogger(Lec03AccessResponseUsingFuture.class);

    static void main() {
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            var product_1 = executorService.submit(() -> Client.getProduct(1));
            var product_2 = executorService.submit(() -> Client.getProduct(2));
            var product_3 = executorService.submit(() -> Client.getProduct(3));
            LOGGER.info("Product-1 info: {}", product_1.get());
            LOGGER.info("Product-2 info: {}", product_2.get());
            LOGGER.info("Product-3 info: {}", product_3.get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}
