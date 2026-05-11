package j25.unnamed_variables_and_patterns;

public class UnnamedVariableLoop {

    public static void main(String[] args) {
        String[] data = {"april", "december"};

        for (String name : data) { // name not used
            System.out.println("Performing a side-effect....");
        }

        for (String _ : data) {
            System.out.println("Performing a side-effect....");
        }
    }
}
