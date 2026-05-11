package j25.unnamed_variables_and_patterns;

record Somebody(String name, int age) {}
public class UnnamedPatternRecord {

    public static void main(String[] args) {
        Somebody somebody = new Somebody("Rasool", 43);

        if (somebody instanceof Somebody(String name, int age)) {
            System.out.println("Name: "+ name);
        }

        if (somebody instanceof Somebody(String name, _)) {
            System.out.println("Name: " + name);
        }
    }
}
