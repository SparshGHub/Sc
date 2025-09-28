package scicalc.operations;

public class BasicOperations {
    public int add(int a, int b){
        return a + b;
    }
    public int subtract(int a, int b){
        return a - b;
    }
    public int multiply(int a, int b){
        // Check for integer overflow
        long result = (long) a * (long) b;

        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            System.out.println("Multiplication result overflows integer range.");
            return 0;
        }

        return (int) result;
    }
    public int divide(int a, int b){

        if(b == 0){
            throw new ArithmeticException("Division by zero is not allowed.");
        }

        return (int)a / b;
    }
}
