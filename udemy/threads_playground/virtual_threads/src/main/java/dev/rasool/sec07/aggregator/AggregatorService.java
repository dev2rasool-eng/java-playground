package dev.rasool.sec07.aggregator;

import dev.rasool.sec07.external_service.Client;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class AggregatorService {

    private final ExecutorService executorService;


    public AggregatorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public Product getProduct(int id) {
        Future<String> productFuture = executorService.submit(() -> Client.getProduct(id));
        Future<Integer> ratingFuture = executorService.submit(() -> Client.getRating(id));
        try {
            return new Product(id, productFuture.get(), ratingFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
