
package mg.eni;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        System.out.println("Application Maven ENI - CI/CD Automatique");
        System.out.println("2 + 3 = " + calculator.add(2, 3));
    }
}
