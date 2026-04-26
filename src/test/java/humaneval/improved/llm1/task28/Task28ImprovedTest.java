package humaneval.improved.llm1.task28;

import humaneval.llm1.task28.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

public class Task28ImprovedTest {
    @Test
    void testConcatenate() {
        Solution s = new Solution();
        
        // Branch: strings == null
        assertEquals("", s.concatenate(null));
        
        // Branch: strings.isEmpty()
        assertEquals("", s.concatenate(Collections.emptyList()));
        
        // Loop branch
        assertEquals("abc", s.concatenate(Arrays.asList("a", "b", "c")));
    }
}
