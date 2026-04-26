package humaneval.improved.llm1.task5;

import humaneval.llm1.task5.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

public class Task5ImprovedTest {
    @Test
    void testIntersperse() {
        Solution s = new Solution();
        // Branch 1: numbers == null
        assertEquals(Collections.emptyList(), s.intersperse(null, 4));
        // Branch 2: numbers.isEmpty()
        assertEquals(Collections.emptyList(), s.intersperse(Collections.emptyList(), 4));
        
        // List with one element (loop runs once, branch i < size - 1 is false)
        assertEquals(Arrays.asList(1), s.intersperse(Collections.singletonList(1), 4));
        
        // List with multiple elements (branch i < size - 1 is true)
        assertEquals(Arrays.asList(1, 4, 2, 4, 3), s.intersperse(Arrays.asList(1, 2, 3), 4));
    }
}
