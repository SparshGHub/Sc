package scicalc.operations;

public class ScientificOperations {
    public double logarithm(int a, int b){
    
        if(a <= 0){
            throw new IllegalArgumentException("Logarithm of non-positive numbers is not defined.");
        }

        if(b <= 1){
            throw new IllegalArgumentException("Logarithm base must be greater than 1.");
        }

        return Math.log(a)/Math.log(b);
    }

    public double exponentiation(int a, int b){

        if(a == 0){
            System.out.println("0 raised to any power is 0.");
        }

        if(b < 0){
            throw new IllegalArgumentException("Negative exponent results in a fraction.");
        }

        return Math.pow(a, b);
    
    }
    
    public double squareRoot(int a){
    
        if(a < 0){
            throw new IllegalArgumentException("Square root of negative numbers is not defined.");
        }
    
        return Math.sqrt(a);
    }
}
