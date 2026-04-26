package humaneval.improved.llm2.task4;

import humaneval.llm2.task4.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task4ImprovedTest {
    @Test
    void testUniformList() {
        Solution s = new Solution();
        // All elements equal → MAD == 0
        assertEquals(0.0, s.meanAbsoluteDeviation(Arrays.asList(5.0, 5.0, 5.0)), 0.0001);
    }

    @Test
    void testSymmetricList() {
        Solution s = new Solution();
        // Symmetric around mean → known MAD = 1.0
        assertEquals(1.0, s.meanAbsoluteDeviation(Arrays.asList(1.0, 2.0, 3.0, 4.0)), 0.0001);
    }

    @Test
    void testSingleElement() {
        Solution s = new Solution();
        // Single element → MAD == 0
        assertEquals(0.0, s.meanAbsoluteDeviation(Collections.singletonList(7.0)), 0.0001);
    }

    @Test
    void testTwoElements() {
        Solution s = new Solution();
        // Two elements: mean=3.0, MAD = (|1-3| + |5-3|)/2 = 2.0
        assertEquals(2.0, s.meanAbsoluteDeviation(Arrays.asList(1.0, 5.0)), 0.0001);
    }

    @Test
    void testNegativeNumbers() {
        Solution s = new Solution();
        // Negative numbers: mean = 0, MAD = (2+1+1+2)/4 = 1.5
        assertEquals(1.5, s.meanAbsoluteDeviation(Arrays.asList(-2.0, -1.0, 1.0, 2.0)), 0.0001);
    }
}
