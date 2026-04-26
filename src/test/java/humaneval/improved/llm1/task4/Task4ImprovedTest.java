package humaneval.improved.llm1.task4;

import humaneval.llm1.task4.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

public class Task4ImprovedTest {
    @Test
    void testMeanAbsoluteDeviation() {
        Solution s = new Solution();
        // Branch 1: numbers == null
        assertEquals(0.0, s.meanAbsoluteDeviation(null), 0.0001);
        // Branch 2: numbers.isEmpty()
        assertEquals(0.0, s.meanAbsoluteDeviation(Collections.emptyList()), 0.0001);
        // Branch 3: typical list
        assertEquals(1.0, s.meanAbsoluteDeviation(Arrays.asList(1.0, 2.0, 3.0, 4.0)), 0.0001);
    }
}
