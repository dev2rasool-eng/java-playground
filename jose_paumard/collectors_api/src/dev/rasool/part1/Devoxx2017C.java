package main.java.dev.rasool.part1;

import main.java.dev.rasool.model.Article;
import main.java.dev.rasool.model.Author;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Devoxx2017C {

    static void main() {
        Set<Article> articles = Article.readAll();

        // Number of Articles per Author
        Map<Author, Long> noOfArticlesPerAuthor = articles.stream()
                .flatMap(article -> article.getAuthors().stream())
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                );

//        System.out.println("Number of Articles per Author: "+ noOfArticlesPerAuthor);

        // Max Articles published by an author
        Map.Entry<Author, Long> authorWithMostArticles = noOfArticlesPerAuthor.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get();

        System.out.println("Author with Most Articles: " + authorWithMostArticles);
    }
}
