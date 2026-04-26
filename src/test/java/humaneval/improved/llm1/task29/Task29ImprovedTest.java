package humaneval.improved.llm1.task29;

import humaneval.llm1.task29.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

public class Task29ImprovedTest {
    @Test
    void testFilterByPrefix() {
        Solution s = new Solution();
        
        // Empty list
        assertEquals(Collections.emptyList(), s.filterByPrefix(Collections.emptyList(), "a"));
        
        // Branch coverage: s != null, s.startsWith(prefix)
        assertEquals(Arrays.asList("abc", "array"), s.filterByPrefix(Arrays.asList("abc", "bcd", null, "cde", "array"), "a"));
        
        // Prefix not found
        assertEquals(Collections.emptyList(), s.filterByPrefix(Arrays.asList("bcd", "cde"), "a"));
    }
}
