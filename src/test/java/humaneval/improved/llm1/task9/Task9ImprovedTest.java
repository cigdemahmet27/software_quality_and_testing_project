package humaneval.improved.llm1.task9;

import humaneval.llm1.task9.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

public class Task9ImprovedTest {
    @Test
    void testRollingMax() {
        Solution s = new Solution();
        
        // Branch: empty list
        assertEquals(Collections.emptyList(), s.rollingMax(Collections.emptyList()));
        
        // Loop execution and branch n > currentMax (both true and false paths)
        assertEquals(Arrays.asList(1, 2, 3, 3, 3, 4, 4), s.rollingMax(Arrays.asList(1, 2, 3, 2, 3, 4, 2)));
        
        // Ensure negative numbers are handled correctly
        assertEquals(Arrays.asList(-5, -2, -2), s.rollingMax(Arrays.asList(-5, -2, -10)));
    }
}
