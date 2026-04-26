package humaneval.improved.llm1.task16;

import humaneval.llm1.task16.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task16ImprovedTest {
    @Test
    void testCountDistinctCharacters() {
        Solution s = new Solution();
        
        // Branch: string == null
        assertEquals(0, s.countDistinctCharacters(null));
        
        // Branch: string.isEmpty()
        assertEquals(0, s.countDistinctCharacters(""));
        
        // Loop branch
        assertEquals(3, s.countDistinctCharacters("xyzXYZ"));
        assertEquals(4, s.countDistinctCharacters("Jerry"));
    }
}
