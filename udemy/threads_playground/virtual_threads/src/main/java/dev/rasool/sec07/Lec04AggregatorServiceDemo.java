package dev.rasool.sec07;

import dev.rasool.sec07.aggregator.AggregatorService;
import dev.rasool.sec07.aggregator.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lec04AggregatorServiceDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(Lec04AggregatorServiceDemo.class);

    static void main() {

//        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

        ExecutorService executorService = Executors.newThreadPerTaskExecutor(Thread.ofVirtual().name("kt-", 1).factory());

        AggregatorService aggregatorService = new AggregatorService(executorService);

        List<Future<Product>> futureProducts = new ArrayList<>();

        /*for (int i = 1; i <= 50; i++) {
            int id = i;
            Future<Product> productFuture = executorService.submit(() -> aggregatorService.getProduct(id));
            futureProducts.add(productFuture);
        }*/

        futureProducts = IntStream.rangeClosed(1, 50)
                .mapToObj(id -> executorService.submit(() -> aggregatorService.getProduct(id)))
                .toList();

        List<Product> products = getProducts(futureProducts);

        LOGGER.info("products: {}", products);


    }

    private static List<Product> getProducts(List<Future<Product>> futureProducts) {
        return futureProducts.stream()
                .map(pf -> {
                    try {
                        return pf.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }
}
