package main.java.dev.rasool.part1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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

        //
        String string = Arrays.stream(strings)
//                .parallel()
                .filter(str -> str.length() == 3)
                .collect(StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append)
                .toString();
        System.out.println("Response: "+ string);

        // Example of collectingAndThen
        List<String> stringList1 = Arrays.stream(strings)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                Collections::unmodifiableList
                        )
                );
        System.out.println("Collecting And Then:" + stringList1);

        // missing least positive number
        int[] numbers = {-3, -5, 0, 3, 2};

        // find the max number from array
        int maxInt = Arrays.stream(numbers)
                .max()
                .getAsInt();
        System.out.println("max: "+maxInt);

        // find least positive number
        for (int i = 0; i <= maxInt; i++) {
            if (!Arrays.stream(numbers).boxed().toList().contains(i)) {
                System.out.println("Least positive number: "+ i);
                break;
            }
        }

        // Duplicate Numbers
        int[] dumbers = {1, 2, 3, 4, 4, 3, 2, 5, 2, 3};
        Map<Integer, Long> integerLongMap1 = Arrays.stream(dumbers)
                .boxed()
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        ));

        // Duplicate Numbers
        List<Integer> list1 = integerLongMap1
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .toList();

        System.out.println(list1);
    }
}
