package humaneval.improved.llm2.task14;

import humaneval.llm2.task14.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task14ImprovedTest {
    @Test
    void testEmptyString() {
        Solution s = new Solution();
        // Branch: loop never entered (string.length() == 0)
        assertEquals(List.of(), s.allPrefixes(""));
    }

    @Test
    void testSingleChar() {
        Solution s = new Solution();
        // One iteration only
        assertEquals(List.of("a"), s.allPrefixes("a"));
    }

    @Test
    void testMultipleChars() {
        Solution s = new Solution();
        // Multiple iterations → all prefixes
        assertEquals(Arrays.asList("a", "ab", "abc"), s.allPrefixes("abc"));
    }

    @Test
    void testLongerString() {
        Solution s = new Solution();
        // More prefixes
        assertEquals(Arrays.asList("h", "he", "hel", "hell", "hello"), s.allPrefixes("hello"));
    }
}
