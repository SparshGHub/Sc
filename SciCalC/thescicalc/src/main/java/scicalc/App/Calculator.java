package scicalc.App;

import scicalc.operations.BasicOperations;
import scicalc.operations.ScientificOperations;

public class Calculator {
    private BasicOperations basicOps;
    private ScientificOperations sciOps;

    public Calculator() {
        basicOps = new BasicOperations();
        sciOps = new ScientificOperations();
    }
    
    public int add(int a, int b) {
        return basicOps.add(a, b);
    }

    public int subtract(int a, int b) {
        return basicOps.subtract(a, b);
    }

    public int multiply(int a, int b) {
        return basicOps.multiply(a, b);
    }

    public int divide(int a, int b) {
        return basicOps.divide(a, b);
    }

    public double logarithm(int a, int b) {
        return sciOps.logarithm(a, b);
    }

    public double exponentiation(int a, int b) {
        return sciOps.exponentiation(a, b);
    }

    public double squareRoot(int a) {
        return sciOps.squareRoot(a);
    }
    
    public int factorial(int a){
        if(a < 0){
            throw new IllegalArgumentException("Factorial of negative numbers is not defined.");
        }
        int result = 1;
        for(int i = 1; i <= a; i++){
            result *= i;
        }
        return result;  
    }
}