package humaneval.improved.llm1.task0;

import humaneval.llm1.task0.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

public class Task0ImprovedTest {
    @Test
    void testHasCloseElements() {
        Solution s = new Solution();
        // Branch 1: numbers == null
        assertFalse(s.hasCloseElements(null, 0.5));
        
        // Branch 2: numbers.size() < 2
        assertFalse(s.hasCloseElements(Collections.emptyList(), 0.5));
        assertFalse(s.hasCloseElements(Collections.singletonList(1.0), 0.5));
        
        // Branch 3: diff < threshold
        assertTrue(s.hasCloseElements(Arrays.asList(1.0, 2.8, 3.0, 4.0, 5.0, 2.0), 0.3));
        
        // Branch 4: diff >= threshold
        assertFalse(s.hasCloseElements(Arrays.asList(1.0, 2.0, 3.0), 0.5));
    }
}
