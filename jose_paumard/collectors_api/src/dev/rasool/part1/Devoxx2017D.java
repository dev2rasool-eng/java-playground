package main.java.dev.rasool.part1;

import main.java.dev.rasool.model.Article;
import main.java.dev.rasool.model.Author;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Devoxx2017D {

    static void main() {

        Set<Article> articles = Article.readAll();

        // find the highest articles published in a year
        Map.Entry<Integer, Long> integerLongMap =
                articles.stream()
                        .collect(
                                Collectors.collectingAndThen(
                                        groupingByAndCounting(Article::getInceptionYear), // downstream
                                        maxEntryByValue() // finisher
                                )
                        );

        System.out.println("Max articles published in a year: "+ integerLongMap);
    }

    private static Collector<Article, ?, Map<Integer, Long>> groupingByAndCounting(Function<Article, Integer> classifier) {
        return Collectors.groupingBy(
                classifier, // classifier
                Collectors.counting() // downstream
        );
    }

    private static <K, V extends Comparable<? super V>> Function<Map<K, V>, Map.Entry<K, V>> maxEntryByValue() {
        return map -> map.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get();
    }

}
