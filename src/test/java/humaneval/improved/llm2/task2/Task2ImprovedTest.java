package humaneval.improved.llm2.task2;

import humaneval.llm2.task2.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task2ImprovedTest {
    @Test
    void testWholeNumber() {
        Solution s = new Solution();
        // Branch: number is exactly an integer → decimal part is 0.0
        assertEquals(0.0, s.truncateNumber(3.0), 0.0001);
    }

    @Test
    void testFractionalPart() {
        Solution s = new Solution();
        // Standard case with fraction
        assertEquals(0.5, s.truncateNumber(3.5), 0.0001);
    }

    @Test
    void testSmallFraction() {
        Solution s = new Solution();
        // Number less than 1 → decimal is the number itself
        assertEquals(0.99, s.truncateNumber(0.99), 0.0001);
    }

    @Test
    void testLargeNumber() {
        Solution s = new Solution();
        // Large number with decimal part
        assertEquals(0.14159, s.truncateNumber(3.14159), 0.0001);
    }

    @Test
    void testZeroDecimal() {
        Solution s = new Solution();
        // Zero input → decimal is 0.0
        assertEquals(0.0, s.truncateNumber(0.0), 0.0001);
    }
}
