package humaneval.improved.llm1.task7;

import humaneval.llm1.task7.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

public class Task7ImprovedTest {
    @Test
    void testFilterBySubstring() {
        Solution s = new Solution();
        
        // Branch: strings == null
        assertEquals(Collections.emptyList(), s.filterBySubstring(null, "a"));
        // Branch: substring == null
        assertEquals(Collections.emptyList(), s.filterBySubstring(Arrays.asList("a"), null));
        
        // Branch: loop execution with null string element
        assertEquals(Collections.emptyList(), s.filterBySubstring(Arrays.asList((String)null), "a"));
        
        // Branch: s does not contain substring
        assertEquals(Collections.emptyList(), s.filterBySubstring(Arrays.asList("xyz"), "a"));
        
        // Branch: s contains substring
        assertEquals(Arrays.asList("abc", "bacd", "array"), s.filterBySubstring(Arrays.asList("abc", "bacd", "cde", "array"), "a"));
    }
}
