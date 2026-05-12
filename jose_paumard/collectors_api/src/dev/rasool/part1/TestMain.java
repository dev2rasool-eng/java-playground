package main.java.dev.rasool.part1;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestMain {

    static void main() {
        String[] strings = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "zero"};

        // Collect to a list
        List<String> stringList = Arrays.stream(strings)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        System.out.println("Result: "+ stringList);

        // Group by based on length of a string
        Map<Integer, List<String>> lengthMap = Arrays.stream(strings)
                .collect(Collectors.groupingBy(String::length));
//                .collect(Collectors.groupingBy(String::length, Collectors.toList()));
        System.out.println("Map of strings based on length: "+ lengthMap);

        // Group by with downstream collectors
        Map<Integer, Long> integerLongMap = Arrays.stream(strings)
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println("Map of strings based on length and their count: " + integerLongMap);
    }
}
