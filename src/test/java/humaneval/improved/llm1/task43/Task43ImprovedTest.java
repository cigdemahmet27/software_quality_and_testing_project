package humaneval.improved.llm1.task43;

import humaneval.llm1.task43.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

public class Task43ImprovedTest {
    @Test
    void testPairsSumToZero() {
        Solution s = new Solution();
        
        // Empty list
        assertFalse(s.pairsSumToZero(Collections.emptyList()));
        
        // Single element
        assertFalse(s.pairsSumToZero(Collections.singletonList(1)));
        
        // No pair sums to zero
        assertFalse(s.pairsSumToZero(Arrays.asList(1, 2, 3, 7)));
        
        // Pair sums to zero
        assertTrue(s.pairsSumToZero(Arrays.asList(2, 4, -5, 3, 5, 7)));
        
        // Zero handling
        assertTrue(s.pairsSumToZero(Arrays.asList(1, 0, -1)));
    }
}
