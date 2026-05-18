package main.java.dev.rasool.part1;

import main.java.dev.rasool.model.Article;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Devoxx2017E {

    static void main() {
        Set<Article> articles = Article.readAll();

        // Get Max Articles published Years
        // {articles = [years]} - Map<Long, List<Integer>>

        // 1. Get no.of Articles published in a year (groupingBy)
        // Map<Year, Articles_Count>
        Map<Integer, Long> noOfArticlesPerYear = articles.stream()
                .collect(
                        Collectors.groupingBy(
                                Article::getInceptionYear,
                                Collectors.counting()
                        )
                );
//        System.out.println("Number of Articles Per Year: " + noOfArticlesPerYear);

        // [OPTION-1] --  2. Max Articles published Years
        // Map.Entry<Article_Count, List<Year>>
        Map.Entry<Long, List<Integer>> longListEntry =
            noOfArticlesPerYear.entrySet().stream()
                .collect(
                    Collectors.groupingBy(
                        Map.Entry::getValue, // Article_Count -- new map key
                        Collectors.mapping(  // new map value
                                Map.Entry::getKey,
                                Collectors.toList()
                        )
                    )
                ) // Map<Long, List<Integer>>
                .entrySet().stream()
                .max(Map.Entry.comparingByKey())
                .get();
        System.out.println("Max Articles Years [option-1]: " + longListEntry);

        // [OPTION-2]
        Map.Entry<Long, List<Integer>> longListEntry1 = noOfArticlesPerYear.entrySet().stream()
                .collect(
                        Collectors.groupingBy(
                                Map.Entry::getValue
                        )
                ) // Map<Long, List<Map.Entry<Integer, Long>>>
                .entrySet().stream()
                .collect(
                        Collectors.toMap(
                                e -> e.getKey(),
                                e -> e.getValue().stream().map(Map.Entry::getKey).collect(Collectors.toList())
                        )
                ) // Map<Long, List<Integer>>
                .entrySet().stream()
                .max(Map.Entry.comparingByKey())
                .get();
        System.out.println("Max Articles Years [option-2]: " + longListEntry1);
    }
}
