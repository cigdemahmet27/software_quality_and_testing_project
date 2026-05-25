/* @Authors
 * Student Names: Taha Çali, Ahmet Enes Çiğdem, Ali Eren Çiftçi
 * Student IDs: 150220050, 150220079, 150220022
 *
 * Integration tests for BookScan (LLM1: Gemini — Edited Prompt Version)
 */
package phase_2.llm1.edited;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Set;

public class BookScanIntegrationTest {

    @Test
    public void testHowManyTimes() {
        assertEquals(3, BookScan.howManyTimes("aaa", "a"));
        assertEquals(3, BookScan.howManyTimes("aaaa", "aa"));
        assertEquals(0, BookScan.howManyTimes("", "a"));
        assertEquals(0, BookScan.howManyTimes(null, "a"));
        assertEquals(0, BookScan.howManyTimes("aaa", null));
        assertEquals(0, BookScan.howManyTimes("aaa", ""));
    }

    @Test
    public void testStrlen() {
        assertEquals(0, BookScan.strlen(""));
        assertEquals(0, BookScan.strlen(null));
        assertEquals(5, BookScan.strlen("hello"));
    }

    @Test
    public void testFlipCase() {
        assertNull(BookScan.flipCase(null));
        assertEquals("", BookScan.flipCase(""));
        assertEquals("hELLO", BookScan.flipCase("Hello"));
        assertEquals("123!@#aBc", BookScan.flipCase("123!@#AbC"));
    }

    @Test
    public void testScanByWordLengthBasic() {
        String text = "Java is fun\nFun is java";
        // Words of length 4: "Java", "java" -> normalized to "java"
        
        Map<String, Map<String, Object>> results = BookScan.scanByWordLength(text, 4);
        
        assertNotNull(results);
        assertTrue(results.containsKey("java"));
        
        Map<String, Object> stats = results.get("java");
        assertNotNull(stats);
        
        // Count should be 2 because "java" appears on line 1 (Java) and line 2 (java)
        assertEquals(2, stats.get("count"));
        
        @SuppressWarnings("unchecked")
        Set<Integer> lines = (Set<Integer>) stats.get("lines");
        assertEquals(2, lines.size());
        assertTrue(lines.contains(1));
        assertTrue(lines.contains(2));
    }

    @Test
    public void testScanByWordLengthEdgeCases() {
        // Empty text
        Map<String, Map<String, Object>> emptyResults = BookScan.scanByWordLength("", 3);
        assertTrue(emptyResults.isEmpty());

        // Null text
        Map<String, Map<String, Object>> nullResults = BookScan.scanByWordLength(null, 3);
        assertTrue(nullResults.isEmpty());

        // Zero or negative target length
        Map<String, Map<String, Object>> zeroLenResults = BookScan.scanByWordLength("Hello world", 0);
        assertTrue(zeroLenResults.isEmpty());
        
        Map<String, Map<String, Object>> negLenResults = BookScan.scanByWordLength("Hello world", -1);
        assertTrue(negLenResults.isEmpty());
    }

    @Test
    public void testScanByWordLengthPunctuationAndCaseInsensitivity() {
        String text = "Hello! hello, HELLO...";
        // Length 5: "Hello", "hello", "HELLO"
        Map<String, Map<String, Object>> results = BookScan.scanByWordLength(text, 5);

        assertTrue(results.containsKey("hello"));
        Map<String, Object> stats = results.get("hello");

        // The split on "\\s+" produces "Hello!", "hello,", "HELLO...".
        // Clean words: "Hello", "hello", "HELLO". All length 5.
        // On line 1, they normalize to "hello".
        // When checking howManyTimes: "hello hello hello" has 3 occurrences.
        assertEquals(3, stats.get("count"));

        @SuppressWarnings("unchecked")
        Set<Integer> lines = (Set<Integer>) stats.get("lines");
        assertEquals(1, lines.size());
        assertTrue(lines.contains(1));
    }

    @Test
    public void testScanByWordLengthOverlappingAndSubstringIssues() {
        // Demonstrating how BookScan counts substring matches like "java" inside "javascript"
        String text = "Java is inside Javascript";
        Map<String, Map<String, Object>> results = BookScan.scanByWordLength(text, 4);

        assertTrue(results.containsKey("java"));
        Map<String, Object> stats = results.get("java");

        // "Java" has length 4, so it triggers scanning.
        // howManyTimes("java is inside javascript", "java") returns 2
        // because "java" is a substring of "javascript".
        // So count is 2.
        assertEquals(2, stats.get("count"));
    }
}
