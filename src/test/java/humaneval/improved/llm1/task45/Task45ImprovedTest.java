package humaneval.improved.llm1.task45;

import humaneval.llm1.task45.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task45ImprovedTest {
    @Test
    void testTriangleArea() {
        Solution s = new Solution();
        assertEquals(7.5, s.triangleArea(5, 3), 0.0001);
        assertEquals(0.0, s.triangleArea(0, 3), 0.0001);
    }
}
