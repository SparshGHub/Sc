import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import scicalc.App.Calculator;

import static org.junit.jupiter.api.Assertions.*;


public class ScientificOperationsTests {
    private static Calculator calculator;

    @BeforeAll
    public static void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testLogarithm() {
        
        //TESTS TO CHECK FOR NORMAL FUINCTIONALITY
        
        assertEquals(3.0, calculator.logarithm(1000, 10), 1e-9);
        assertEquals(2.0, calculator.logarithm(16, 4), 1e-9);
        assertEquals(0.0, calculator.logarithm(1, 5), 1e-9);
        
        //TESTS TO CHECK FOR ILLEGAL ARGUMENT EXCEPTIONS
        
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.logarithm(-10, 10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.logarithm(10, 1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.logarithm(10, -2);
        });
    }

    @Test
    public void testExponentiation() {

        //TESTS TO CHECK FOR NORMAL FUNCTIONALITY

        assertEquals(8.0, calculator.exponentiation(2, 3), 1e-9);
        assertEquals(27.0, calculator.exponentiation(3, 3), 1e-9);
        assertEquals(1.0, calculator.exponentiation(5, 0), 1e-9);

        //TEST TO CHECK FOR ILLEGAL ARGUMENT EXCEPTION

        assertThrows(IllegalArgumentException.class, () -> {
            calculator.exponentiation(2, -3);
        });
        assertEquals(0.0, calculator.exponentiation(0, 5), 1e-9);   
    }

    @Test
    public void testSquareRoot() {
        
        //TESTS TO CHECK FOR NORMAL FUNCTIONALITY
        
        assertEquals(4.0, calculator.squareRoot(16), 1e-9);
        assertEquals(5.0, calculator.squareRoot(25), 1e-9);
        assertEquals(0.0, calculator.squareRoot(0), 1e-9);

        //TEST TO CHECK FOR ILLEGAL ARGUMENT EXCEPTION
        
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.squareRoot(-16);
        });
    }      
     
    @Test
        public void testFactorial() {
            //TESTS TO CHECK FOR NORMAL FUNCTIONALITY
            
            assertEquals(120, calculator.factorial(5), 1e-9);
            assertEquals(1, calculator.factorial(0), 1e-9);
            assertEquals(1, calculator.factorial(1), 1e-9);
    
            //TEST TO CHECK FOR ILLEGAL ARGUMENT EXCEPTION
            
            assertThrows(IllegalArgumentException.class, () -> {
                calculator.factorial(-5);
            }); 
        }
}
