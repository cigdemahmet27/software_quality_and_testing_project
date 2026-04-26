package humaneval.improved.llm2.task11;

import humaneval.llm2.task11.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task11ImprovedTest {
    @Test
    void testSameBits() {
        Solution s = new Solution();
        // Branch: a.charAt(i) == b.charAt(i) → append "0"
        assertEquals("000", s.stringXor("111", "111"));
    }

    @Test
    void testDifferentBits() {
        Solution s = new Solution();
        // Branch: a.charAt(i) != b.charAt(i) → append "1"
        assertEquals("111", s.stringXor("000", "111"));
    }

    @Test
    void testMixedBits() {
        Solution s = new Solution();
        // Both branches in single call
        assertEquals("100", s.stringXor("010", "110"));
    }

    @Test
    void testEmptyStrings() {
        Solution s = new Solution();
        // Branch: loop never entered
        assertEquals("", s.stringXor("", ""));
    }

    @Test
    void testSingleBit() {
        Solution s = new Solution();
        // Minimal case — one iteration
        assertEquals("1", s.stringXor("0", "1"));
        assertEquals("0", s.stringXor("1", "1"));
    }

    @Test
    void testLongerInput() {
        Solution s = new Solution();
        // Multiple iterations
        assertEquals("0110", s.stringXor("1010", "1100"));
    }
}
