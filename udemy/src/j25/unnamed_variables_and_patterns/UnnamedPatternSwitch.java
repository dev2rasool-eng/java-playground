package j25.unnamed_variables_and_patterns;

public class UnnamedPatternSwitch {

    static void process(Object obj) {
        switch (obj) {
            case String s -> System.out.println("It is a String");
            case Integer i -> System.out.println("It is an Integer");
            case null, default -> System.out.println("Something else");
        }

        switch (obj) {
            case String _ -> System.out.println("It is a String");
            case Integer _ -> System.out.println("It is an Integer");
            case null, default -> System.out.println("Something else");
        }
    }

    public static void main(String[] args) {
        process("Rasool");
        process(12);
        process(3.1415);
    }

}
