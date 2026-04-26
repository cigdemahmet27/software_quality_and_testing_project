package humaneval.improved.llm1.task42;

import humaneval.llm1.task42.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

public class Task42ImprovedTest {
    @Test
    void testIncrList() {
        Solution s = new Solution();
        
        // Empty list
        assertEquals(Collections.emptyList(), s.incrList(Collections.emptyList()));
        
        // Typical list
        assertEquals(Arrays.asList(2, 3, 4), s.incrList(Arrays.asList(1, 2, 3)));
    }
}
