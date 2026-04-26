package humaneval.improved.llm1.task13;

import humaneval.llm1.task13.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task13ImprovedTest {
    @Test
    void testGreatestCommonDivisor() {
        Solution s = new Solution();
        
        // Loop execution
        assertEquals(1, s.greatestCommonDivisor(3, 5));
        assertEquals(5, s.greatestCommonDivisor(25, 15));
        
        // Loop not executed (b is already 0)
        assertEquals(5, s.greatestCommonDivisor(5, 0));
        
        // Negatives
        assertEquals(5, s.greatestCommonDivisor(-25, 15));
        assertEquals(5, s.greatestCommonDivisor(25, -15));
    }
}
