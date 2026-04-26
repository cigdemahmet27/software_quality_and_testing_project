package humaneval.improved.llm2.task49;

import humaneval.llm2.task49.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task49ImprovedTest {
    @Test
    void testZeroExponent() {
        Solution s = new Solution();
        // Branch: loop not entered → result stays 1
        assertEquals(1, s.modp(0, 101));
    }

    @Test
    void testOneExponent() {
        Solution s = new Solution();
        // Branch: loop runs once → result = 2 % p
        assertEquals(2, s.modp(1, 5));
    }

    @Test
    void testSmallExponent() {
        Solution s = new Solution();
        // 2^3 = 8, 8 % 5 = 3
        assertEquals(3, s.modp(3, 5));
    }

    @Test
    void testModpThree() {
        Solution s = new Solution();
        // 2^3 = 8, 8 % 11 = 8
        assertEquals(8, s.modp(3, 11));
    }

    @Test
    void testLargeExponent() {
        Solution s = new Solution();
        // 2^1101 % 101 = 2 (from docstring)
        assertEquals(2, s.modp(1101, 101));
    }

    @Test
    void testPowerEqualsModulus() {
        Solution s = new Solution();
        // 2^100 % 101 = 1 (Fermat's little theorem: 2^100 ≡ 1 mod 101)
        assertEquals(1, s.modp(100, 101));
    }

    @Test
    void testModTwo() {
        Solution s = new Solution();
        // 2^n % 2 = 0 for any n >= 1
        assertEquals(0, s.modp(5, 2));
    }
}
