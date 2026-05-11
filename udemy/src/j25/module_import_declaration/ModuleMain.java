package j25.module_import_declaration;

/*import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;*/

import module java.base;

public class ModuleMain {

    void main() throws Exception{
        Path path = Path.of("D:\\playground\\kutty\\git_playground\\java-playground\\udemy\\data.txt");
        List<String> lines = Files.readAllLines(path);

        Map<Integer, Long> lineLengthCount = lines.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));
        IO.println(lineLengthCount);

        Map<String, Long> stringLongMap = lines.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        IO.println(stringLongMap);

        String longestStr = lines.stream()
                .max(Comparator.comparing(String::length))
                .orElse("<none>");
        IO.println("longest: " + longestStr);
    }
}
