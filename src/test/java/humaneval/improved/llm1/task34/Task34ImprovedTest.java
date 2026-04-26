package humaneval.improved.llm1.task34;

import humaneval.llm1.task34.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

public class Task34ImprovedTest {
    @Test
    void testUnique() {
        Solution s = new Solution();
        
        // Empty list
        assertEquals(Collections.emptyList(), s.unique(Collections.emptyList()));
        
        // List with duplicates, should sort and make unique
        assertEquals(Arrays.asList(0, 2, 3, 5, 9, 123), s.unique(Arrays.asList(5, 3, 5, 2, 3, 3, 9, 0, 123)));
    }
}
