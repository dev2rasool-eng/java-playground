package dev.rasool.sec08.aggregator;

import dev.rasool.sec08.external_service.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

public class AggregatorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AggregatorService.class);

    private final ExecutorService executorService;


    public AggregatorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public Product getProduct(int id) {
        CompletableFuture<String> productFuture = CompletableFuture
                .supplyAsync(() -> Client.getProduct(id), executorService)
                .exceptionally(ex -> {
                    LOGGER.error(ex.getMessage());
                    return null;
                });
        CompletableFuture<Integer> ratingFuture = CompletableFuture
                .supplyAsync(() -> Client.getRating(id), executorService)
                .exceptionally(ex -> {
                    LOGGER.error(ex.getMessage());
                    return -1;
                });
        try {
            return new Product(id, productFuture.get(), ratingFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
