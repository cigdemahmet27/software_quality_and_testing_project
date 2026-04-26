package humaneval.improved.llm1.task23;

import humaneval.llm1.task23.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task23ImprovedTest {
    @Test
    void testStrlen() {
        Solution s = new Solution();
        assertEquals(0, s.strlen(""));
        assertEquals(3, s.strlen("abc"));
    }
}
