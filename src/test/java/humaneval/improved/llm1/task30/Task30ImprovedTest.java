package humaneval.improved.llm1.task30;

import humaneval.llm1.task30.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

public class Task30ImprovedTest {
    @Test
    void testGetPositive() {
        Solution s = new Solution();
        
        // Empty list
        assertEquals(Collections.emptyList(), s.getPositive(Collections.emptyList()));
        
        // Branch coverage: n > 0 true and false
        assertEquals(Arrays.asList(2, 5, 6), s.getPositive(Arrays.asList(-1, 2, -4, 5, 6, 0)));
        
        // Only negatives
        assertEquals(Collections.emptyList(), s.getPositive(Arrays.asList(-1, -2, -3)));
    }
}
