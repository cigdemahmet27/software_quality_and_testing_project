package humaneval.improved.llm1.task35;

import humaneval.llm1.task35.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class Task35ImprovedTest {
    @Test
    void testMaxElement() {
        Solution s = new Solution();
        
        // List with elements
        assertEquals(3, s.maxElement(Arrays.asList(1, 2, 3)));
        assertEquals(123, s.maxElement(Arrays.asList(5, 3, -5, 2, -3, 3, 9, 0, 123, 1, -10)));
        
        // Single element
        assertEquals(5, s.maxElement(Arrays.asList(5)));
    }
}
