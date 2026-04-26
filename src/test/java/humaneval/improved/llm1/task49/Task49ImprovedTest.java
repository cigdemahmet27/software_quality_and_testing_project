package humaneval.improved.llm1.task49;

import humaneval.llm1.task49.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task49ImprovedTest {
    @Test
    void testModp() {
        Solution s = new Solution();
        
        // Branch: n == 0, p > 1
        assertEquals(1, s.modp(0, 101));
        
        // Branch: n == 0, p == 1
        assertEquals(0, s.modp(0, 1));
        
        // Loop coverage and branch: exponent % 2 == 1
        // n = 3 (11 in binary)
        assertEquals(3, s.modp(3, 5));
        
        // Exponent with both 1s and 0s
        assertEquals(2, s.modp(1101, 101));
        
        // Large modulo
        assertEquals(8, s.modp(3, 11));
    }
}
