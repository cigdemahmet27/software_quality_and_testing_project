package humaneval.improved.llm2.task15;

import humaneval.llm2.task15.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task15ImprovedTest {
    @Test
    void testZero() {
        Solution s = new Solution();
        // Branch: loop runs once (i==0), i > 0 is false → no space
        assertEquals("0", s.stringSequence(0));
    }

    @Test
    void testOne() {
        Solution s = new Solution();
        // Branch: loop runs twice, i > 0 true on second → space added
        assertEquals("0 1", s.stringSequence(1));
    }

    @Test
    void testFive() {
        Solution s = new Solution();
        // Multiple iterations
        assertEquals("0 1 2 3 4 5", s.stringSequence(5));
    }

    @Test
    void testThree() {
        Solution s = new Solution();
        // Standard case
        assertEquals("0 1 2 3", s.stringSequence(3));
    }
}
