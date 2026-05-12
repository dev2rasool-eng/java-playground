package main.java.dev.rasool.part1;

import main.java.dev.rasool.model.Article;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Devoxx2017B {

    static void main() {
        Set<Article> articles = Article.readAll();

        // Number of articles per Year
        Map<Integer, Long> noOfArticlesPerYear = articles.stream()
                .collect(Collectors.groupingBy(
                        article -> article.getInceptionYear(),
                        Collectors.counting()
                        )
                );
        System.out.println("Number of Articles per Year: " + noOfArticlesPerYear);

        // Get the Year that has max number of Articles published
        // drawback of below approach is it fetches the first max. Not all maxes
        Map.Entry<Integer, Long> maxNoOfArticlesYear = noOfArticlesPerYear.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get();
        System.out.println("Max Number Of Articles Pushed Year: "+ maxNoOfArticlesYear);

        // All years with max number of Articles published
        Map.Entry<Long, List<Map.Entry<Integer, Long>>> allMaxesArticlesYear = noOfArticlesPerYear.entrySet().stream() // Stream<Map.Entry<Integer, Long>>
                .collect(Collectors.groupingBy(Map.Entry::getValue)) // Map<Long, List<Map.Entry<Integer, Long>>>
                .entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getKey))
                .get();
        System.out.println("All Max Number of Articles Published Year: "+ allMaxesArticlesYear);

    }
}
