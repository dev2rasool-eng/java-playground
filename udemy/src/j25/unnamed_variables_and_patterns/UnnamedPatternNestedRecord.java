package j25.unnamed_variables_and_patterns;

record Person(String name, int age, Address address) {}
record Address(String city, String country) {}

public class UnnamedPatternNestedRecord {

    public static void main(String[] args) {
        Person person = new Person("Rasool", 43,
                new Address("Hyderabad", "India"));

        if (person instanceof Person(String name, int age, Address(String city, String country))) {
            System.out.println(name + " lives in " + country);
        }

        if (person instanceof Person(String name, _, Address(_, String country))) {
            System.out.println(name + " lives in " + country);
        }
    }


}
