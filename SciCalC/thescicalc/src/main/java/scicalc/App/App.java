package scicalc.App;

import java.util.Scanner;

public class App {

    Calculator app = new Calculator();
    Scanner sc = new Scanner(System.in);

    public void printMenu() {
        System.out.println("\n1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.println("5. Logarithm");
        System.out.println("6. Exponentiation");
        System.out.println("7. SquareRoot");
        System.out.println("8. Factorial");
        System.out.println("9. Exit\n");
    }

    public void run() {
        int a, b;
        int choice;
        while (true) {
            printMenu();
            System.out.println("\nEnter your choice: \n");

            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter two numbers: \n");
                    a = sc.nextInt();
                    b = sc.nextInt();

                    try {
                        int result = app.add(a, b);
                        System.out.println("\nRESULT: " + result + "\n");

                    } catch (Exception e) {
                        System.out.println("\n" + (e.getMessage()).toUpperCase() + "\n");
                    }

                    break;

                case 2:
                    System.out.println("Enter two numbers: \n");
                    a = sc.nextInt();
                    b = sc.nextInt();

                    try {
                        int result = app.subtract(a, b);
                        System.out.println("\nRESULT: " + result + "\n");

                    } catch (Exception e) {
                        System.out.println("\n" + (e.getMessage()).toUpperCase() + "\n");
                    }
                    break;

                case 3:
                    System.out.println("Enter two numbers: \n");
                    a = sc.nextInt();
                    b = sc.nextInt();
                    try {
                        int result = app.multiply(a, b);
                        System.out.println("\nRESULT: " + result + "\n");

                    } catch (Exception e) {
                        System.out.println("\n" + (e.getMessage()).toUpperCase() + "\n");
                    }
                    break;

                case 4:
                    System.out.println("Enter two numbers: \n");
                    a = sc.nextInt();
                    b = sc.nextInt();

                    try {
                        int result = app.divide(a, b);
                        System.out.println("\nRESULT: " + result + "\n");

                    } catch (Exception e) {
                        System.out.println("\n" + (e.getMessage()).toUpperCase() + "\n");
                    }
                    break;

                case 5:
                    System.out.println("Enter the number and the base: \n");
                    a = sc.nextInt();
                    b = sc.nextInt();

                    try {
                        double result = app.logarithm(a, b);
                        System.out.println("\nRESULT: " + result + "\n");

                    } catch (Exception e) {
                        System.out.println("\n" + (e.getMessage()).toUpperCase() + "\n");
                    }
                    break;

                case 6:
                    System.out.println("Enter the base and the exponent: \n");
                    a = sc.nextInt();
                    b = sc.nextInt();

                    try {
                        double result = app.exponentiation(a, b);
                        System.out.println("\nRESULT: " + result + "\n");

                    } catch (Exception e) {
                        System.out.println("\n" + (e.getMessage()).toUpperCase() + "\n");
                    }
                    break;

                case 7:
                    System.out.println("Enter the number: \n");
                    a = sc.nextInt();
                    try {
                        double result = app.squareRoot(a);
                        System.out.println("\nRESULT: " + result + "\n");

                    } catch (Exception e) {
                        System.out.println("\n" + (e.getMessage()).toUpperCase() + "\n");
                    }
                    break;

                case 8:
                    System.out.println("Enter the number : \n");
                    a = sc.nextInt();
                    try {
                        int result = app.factorial(a);
                        System.out.println("\nRESULT: " + result + "\n");
                    } catch (Exception e) {
                        System.out.println("\n" + (e.getMessage()).toUpperCase() + "\n");
                    }
                    break;

                case 9:
                    System.out.println("\nExiting...\n");
                    sc.close();
                    return;
            }
        }

    }
}
