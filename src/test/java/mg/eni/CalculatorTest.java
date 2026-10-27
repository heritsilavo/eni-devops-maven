
package mg.eni;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void testAddition() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    void testMultiplication() {
        assertEquals(12, calculator.multiply(3, 4));
    }

    @Test
    void testNegativeNumbers() {
        assertEquals(-2, calculator.add(-5, 3));
    }
}
