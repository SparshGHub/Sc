import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import scicalc.App.Calculator;

import static org.junit.jupiter.api.Assertions.*;

public class BasicOperationsTests {

    private static Calculator calculator;

    @BeforeAll
    public static void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testAdd() {
        assertEquals(5, calculator.add(2, 3), 1e-9);
        assertEquals(-1, calculator.add(-2, 1), 1e-9);
        assertEquals(0, calculator.add(0, 0), 1e-9);
    }

    @Test
    public void testSub() {
        assertEquals(-1, calculator.subtract(2, 3), 1e-9);
        assertEquals(-3, calculator.subtract(-2, 1), 1e-9);
        assertEquals(0, calculator.subtract(0, 0), 1e-9);
    }

    @Test
    public void testMul() {
        assertEquals(6, calculator.multiply(2, 3), 1e-9);
        assertEquals(-2, calculator.multiply(-2, 1), 1e-9);
        assertEquals(0, calculator.multiply(0, 5), 1e-9);
    }

    @Test
    public void testDiv() {
        
        //TESTS FOR BASIC FUNCTIONALITY
        
        assertEquals(2, calculator.divide(6, 3), 1e-9);
        assertEquals(-2, calculator.divide(-4, 2), 1e-9);
        assertEquals(0, calculator.divide(0, 5), 1e-9);
        assertEquals(1, calculator.divide(5, 3), 1e-9);
        assertEquals(0, calculator.divide(12, 32), 1e-9);


        //TESTS TO CHECK FOR ARITHMETIC EXCEPTIONS
        
        assertThrows(ArithmeticException.class, () -> calculator.divide(5, 0));


    }
}