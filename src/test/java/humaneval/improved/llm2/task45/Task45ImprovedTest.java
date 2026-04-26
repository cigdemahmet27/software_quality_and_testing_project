package humaneval.improved.llm2.task45;

import humaneval.llm2.task45.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task45ImprovedTest {
    @Test
    void testStandardTriangle() {
        Solution s = new Solution();
        // 5 * 3 / 2 = 7.5
        assertEquals(7.5, s.triangleArea(5, 3), 0.0001);
    }

    @Test
    void testZeroBase() {
        Solution s = new Solution();
        // 0 * h = 0
        assertEquals(0.0, s.triangleArea(0, 5), 0.0001);
    }

    @Test
    void testZeroHeight() {
        Solution s = new Solution();
        // a * 0 = 0
        assertEquals(0.0, s.triangleArea(5, 0), 0.0001);
    }

    @Test
    void testUnitTriangle() {
        Solution s = new Solution();
        // 1 * 1 / 2 = 0.5
        assertEquals(0.5, s.triangleArea(1, 1), 0.0001);
    }

    @Test
    void testLargeValues() {
        Solution s = new Solution();
        // 100 * 200 / 2 = 10000
        assertEquals(10000.0, s.triangleArea(100, 200), 0.0001);
    }

    @Test
    void testDecimalValues() {
        Solution s = new Solution();
        // 2.5 * 4.0 / 2 = 5.0
        assertEquals(5.0, s.triangleArea(2.5, 4.0), 0.0001);
    }
}
