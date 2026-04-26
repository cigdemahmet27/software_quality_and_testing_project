package humaneval.improved.llm1.task14;

import humaneval.llm1.task14.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

public class Task14ImprovedTest {
    @Test
    void testAllPrefixes() {
        Solution s = new Solution();
        
        // Branch: string == null
        assertEquals(Collections.emptyList(), s.allPrefixes(null));
        
        // Branch: string.isEmpty()
        assertEquals(Collections.emptyList(), s.allPrefixes(""));
        
        // Loop branch
        assertEquals(Arrays.asList("a", "ab", "abc"), s.allPrefixes("abc"));
    }
}
