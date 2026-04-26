package humaneval.improved.llm1.task27;

import humaneval.llm1.task27.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task27ImprovedTest {
    @Test
    void testFlipCase() {
        Solution s = new Solution();
        
        // Branch: string == null
        assertNull(s.flipCase(null));
        
        // Branch: string.isEmpty()
        assertEquals("", s.flipCase(""));
        
        // Branch coverage for isLowerCase, isUpperCase, and else
        assertEquals("hELLO wORLD 123!", s.flipCase("Hello World 123!"));
    }
}
