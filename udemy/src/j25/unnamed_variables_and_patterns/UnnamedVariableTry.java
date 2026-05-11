package j25.unnamed_variables_and_patterns;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UnnamedVariableTry {

    public static void main(String[] args) {
        try (BufferedReader in =
                new BufferedReader(new FileReader("example.txt"))) {
            System.out.println("File opened successfully");
        } catch (IOException ioe) {
            System.out.println("Error reading the file");
        }

        try (BufferedReader _ =
                     new BufferedReader(new FileReader("example.txt"))) {
            System.out.println("File opened successfully");
        } catch (IOException _) {
            System.out.println("Error reading the file");
        }
    }
}
