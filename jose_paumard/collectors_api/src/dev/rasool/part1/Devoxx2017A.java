package main.java.dev.rasool.part1;

import main.java.dev.rasool.model.Article;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Set;
import java.util.stream.Collectors;

public class Devoxx2017A {

    public static void main(String[] args) {

        Set<Article> articles = Article.readAll();
        System.out.println("articles read = " + articles.size());

        // Total Number of Articles / count of articles
        long totalArticles = articles.stream()
                .collect(Collectors.counting());
//                .count();
        System.out.println("Total Articles Count: " + totalArticles);

        // Minimum Inception Year
        Integer minInceptionYear = articles.stream()
                .filter(article -> article.getInceptionYear() > 1900)
                .map(Article::getInceptionYear)
//                .min(Comparator.naturalOrder())
                .collect(Collectors.minBy(Comparator.naturalOrder()))
                .get();
        System.out.println("Minimum Inception Year: " + minInceptionYear);

        // Maximum Inception Year
        Integer maxInceptionYear = articles.stream()
                .map(Article::getInceptionYear)
                .filter(year -> year > 1900)
//                .max(Comparator.naturalOrder())
                .collect(Collectors.maxBy(Comparator.naturalOrder()))
                .get();

        System.out.println("Maximum Inception Year: "+ maxInceptionYear);

        // Articles published in 1960
        String titles1960 = articles.stream()
                .filter(article -> article.getInceptionYear() == 1960)
                .map(article -> article.getTitle())
                .collect(Collectors.joining(", "));

        System.out.println("Articles in 1960: " + titles1960);

        // Int Summary Statistics on Articles Inception Year
        IntSummaryStatistics intSummaryStatistics1 = articles.stream()
                .filter(article -> article.getInceptionYear() > 1900)
                .mapToInt(Article::getInceptionYear)
                .summaryStatistics();
        System.out.println("Summary Statistics: "+ intSummaryStatistics1);

        // Int Summary Statistics on Articles Inception Year (collect)
        IntSummaryStatistics intSummaryStatistics2 = articles.stream()
                .filter(article -> article.getInceptionYear() > 1900)
                .collect(Collectors.summarizingInt(Article::getInceptionYear));
        System.out.println("Summary Statistics (collect): "+ intSummaryStatistics1);

    }
}