package dev.rasool.sec08;

import dev.rasool.sec08.external_service.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class Lec04GetProducts {

    private static final Logger LOGGER = LoggerFactory.getLogger(Lec04GetProducts.class);

    static void main() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            CompletableFuture<String> productFuture1 = CompletableFuture.supplyAsync(() -> Client.getProduct(1), executor);
            CompletableFuture<String> productFuture2 = CompletableFuture.supplyAsync(() -> Client.getProduct(2), executor);
            CompletableFuture<String> productFuture3 = CompletableFuture.supplyAsync(() -> Client.getProduct(3), executor);

            productFuture1.thenAccept(p1 -> LOGGER.info("Product-1: {}", p1));
            productFuture2.thenAccept(p2 -> LOGGER.info("Product-2: {}", p2));
            productFuture3.thenAccept(p3 -> LOGGER.info("Product-3: {}", p3));
        }
    }
}
