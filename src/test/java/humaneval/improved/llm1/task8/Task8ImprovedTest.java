package humaneval.improved.llm1.task8;

import humaneval.llm1.task8.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

public class Task8ImprovedTest {
    @Test
    void testSumProduct() {
        Solution s = new Solution();
        
        // Empty list (skip loop)
        assertEquals(Arrays.asList(0, 1), s.sumProduct(Collections.emptyList()));
        
        // Typical list
        assertEquals(Arrays.asList(10, 24), s.sumProduct(Arrays.asList(1, 2, 3, 4)));
    }
}
