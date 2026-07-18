package dev.rasool.sec07.external_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

    private static final String PRODUCT_REQUEST_FORMAT = "http://localhost:7070/sec01/product/%d";
    private static final String RATING_REQUEST_FORMAT = "http://localhost:7070/sec01/rating/%d";

    /*static void main() {
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 1; i <= 10; i++) {
                int id = i;
                executorService.submit(() -> {
                    String idInfo = getProduct(id);
                    LOGGER.info("{} Product info: {}", id, idInfo);
                });

                executorService.submit(() -> {
                    int idRating = getRating(id);
                    LOGGER.info("{} Rating info: {}", id, idRating);
                });
            }
        }
    }*/

    public static String getProduct(int id) {
        return callExternalService(PRODUCT_REQUEST_FORMAT.formatted(id));
    }

    public static Integer getRating(int id) {
        return Integer.parseInt(
            callExternalService(RATING_REQUEST_FORMAT.formatted(id)));
    }

    private static String callExternalService(String url) {
        LOGGER.info("calling {}", url);

        // external service call
        try (InputStream inputStream = URI.create(url).toURL().openStream()) {
            return new String(inputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
