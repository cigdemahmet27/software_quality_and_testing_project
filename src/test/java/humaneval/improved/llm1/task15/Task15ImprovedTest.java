package humaneval.improved.llm1.task15;

import humaneval.llm1.task15.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task15ImprovedTest {
    @Test
    void testStringSequence() {
        Solution s = new Solution();
        
        // Branch: n < 0
        assertEquals("", s.stringSequence(-1));
        
        // Loop and branch i < n
        assertEquals("0", s.stringSequence(0));
        assertEquals("0 1 2 3 4 5", s.stringSequence(5));
    }
}
