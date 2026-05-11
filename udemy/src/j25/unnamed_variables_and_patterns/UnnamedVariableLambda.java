package j25.unnamed_variables_and_patterns;

import java.util.List;
import java.util.stream.IntStream;

public class UnnamedVariableLambda {

    public static void main(String[] args) {
        List<String> names = List.of("Rasool", "Ansari");

        names.forEach(name -> System.out.println("Side effect..."));
        names.forEach(_ -> System.out.println("Side effect..."));

        IntStream.range(0, 5).forEach(i -> System.out.println("Repeating action..."));
        IntStream.range(0, 5).forEach(_ -> System.out.println("Repeating action..."));
    }
}
