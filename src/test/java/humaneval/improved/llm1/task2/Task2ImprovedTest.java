package humaneval.improved.llm1.task2;

import humaneval.llm1.task2.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task2ImprovedTest {
    @Test
    void testTruncateNumber() {
        Solution s = new Solution();
        assertEquals(0.5, s.truncateNumber(3.5), 0.0001);
        assertEquals(0.0, s.truncateNumber(3.0), 0.0001);
        assertEquals(0.99, s.truncateNumber(0.99), 0.0001);
    }
}
