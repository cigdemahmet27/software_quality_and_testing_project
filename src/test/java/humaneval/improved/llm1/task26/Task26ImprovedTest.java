package humaneval.improved.llm1.task26;

import humaneval.llm1.task26.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

public class Task26ImprovedTest {
    @Test
    void testRemoveDuplicates() {
        Solution s = new Solution();
        
        // Branch: numbers == null
        assertEquals(Collections.emptyList(), s.removeDuplicates(null));
        
        // Branch: numbers.isEmpty()
        assertEquals(Collections.emptyList(), s.removeDuplicates(Collections.emptyList()));
        
        // Remove duplicates functionality
        assertEquals(Arrays.asList(1, 3, 4), s.removeDuplicates(Arrays.asList(1, 2, 3, 2, 4)));
        
        // All duplicates
        assertEquals(Collections.emptyList(), s.removeDuplicates(Arrays.asList(2, 2, 2)));
        
        // No duplicates
        assertEquals(Arrays.asList(1, 2, 3), s.removeDuplicates(Arrays.asList(1, 2, 3)));
    }
}
