package humaneval.improved.llm1.task3;

import humaneval.llm1.task3.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

public class Task3ImprovedTest {
    @Test
    void testBelowZero() {
        Solution s = new Solution();
        // Empty list (loop skipped)
        assertFalse(s.belowZero(Collections.emptyList()));
        // Never below zero
        assertFalse(s.belowZero(Arrays.asList(1, 2, 3)));
        // Goes below zero
        assertTrue(s.belowZero(Arrays.asList(1, 2, -4, 5)));
    }
}
