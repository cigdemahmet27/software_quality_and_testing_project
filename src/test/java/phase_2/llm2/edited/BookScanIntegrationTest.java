/* @Authors
 * Student Names: Ali Eren Çiftçi
 * Student IDs: 150220022
 *
 * Integration tests for BookScan (LLM2: Claude — Edited Prompt Version)
 */
package phase_2.llm2.edited;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Set;

public class BookScanIntegrationTest {

    private BookScan scanner;

    @BeforeEach
    public void setUp() {
        scanner = new BookScan();
    }

    // ==========================================
    // 1. Unit-level: howManyTimes
    // ==========================================
    @Test
    public void testHowManyTimes() {
        assertEquals(3, scanner.howManyTimes("aaa", "a"));
        assertEquals(3, scanner.howManyTimes("aaaa", "aa"));
        assertEquals(0, scanner.howManyTimes("", "a"));
        assertEquals(0, scanner.howManyTimes(null, "a"));
        assertEquals(0, scanner.howManyTimes("aaa", null));
        assertEquals(0, scanner.howManyTimes("aaa", ""));
    }

    // ==========================================
    // 2. Unit-level: strlen
    // ==========================================
    @Test
    public void testStrlen() {
        assertEquals(0, scanner.strlen(""));
        assertEquals(0, scanner.strlen(null));
        assertEquals(5, scanner.strlen("hello"));
    }

    // ==========================================
    // 3. Unit-level: flipCase
    // ==========================================
    @Test
    public void testFlipCase() {
        assertNull(scanner.flipCase(null));
        assertEquals("", scanner.flipCase(""));
        assertEquals("hELLO", scanner.flipCase("Hello"));
        assertEquals("123!@#aBc", scanner.flipCase("123!@#AbC"));
    }

    // ==========================================
    // 4. Integration: basic multi-line scan
    // ==========================================
    @Test
    public void testScanByWordLengthBasic() {
        String text = "Java is fun\nFun is java";
        // Words of length 4: "Java" (line 1), "java" (line 2) → normalized key "java"
        Map<String, Map<String, Object>> results = scanner.scanByWordLength(text, 4);

        assertNotNull(results);
        assertTrue(results.containsKey("java"));

        Map<String, Object> stats = results.get("java");
        assertEquals(2, stats.get("count"));

        @SuppressWarnings("unchecked")
        Set<Integer> lines = (Set<Integer>) stats.get("lines");
        assertEquals(2, lines.size());
        assertTrue(lines.contains(1));
        assertTrue(lines.contains(2));
    }

    // ==========================================
    // 5. Edge cases
    // ==========================================
    @Test
    public void testScanByWordLengthEdgeCases() {
        // Empty text
        assertTrue(scanner.scanByWordLength("", 3).isEmpty());

        // Null text
        assertTrue(scanner.scanByWordLength(null, 3).isEmpty());

        // Zero target length
        assertTrue(scanner.scanByWordLength("Hello world", 0).isEmpty());

        // Negative target length
        assertTrue(scanner.scanByWordLength("Hello world", -1).isEmpty());
    }

    // ==========================================
    // 6. Punctuation stripping & case-insensitive grouping
    // ==========================================
    @Test
    public void testScanByWordLengthPunctuationAndCaseInsensitivity() {
        String text = "Hello! hello, HELLO...";
        // Tokens: "Hello!", "hello,", "HELLO..." → cleaned: "Hello", "hello", "HELLO" (all length 5)
        Map<String, Map<String, Object>> results = scanner.scanByWordLength(text, 5);

        assertTrue(results.containsKey("hello"));
        Map<String, Object> stats = results.get("hello");

        // normalizedLine = "hello! hello, hello..."
        // howManyTimes counts "hello" 3 times in that line
        assertEquals(3, stats.get("count"));

        @SuppressWarnings("unchecked")
        Set<Integer> lines = (Set<Integer>) stats.get("lines");
        assertEquals(1, lines.size());
        assertTrue(lines.contains(1));
    }

    // ==========================================
    // 7. Duplicate tokens on same line (deduplication check)
    // ==========================================
    @Test
    public void testScanByWordLengthDuplicateOnSameLineFixed() {
        // In the edited version, processedOnLine prevents double-counting
        String text = "Java Java";
        Map<String, Map<String, Object>> results = scanner.scanByWordLength(text, 4);

        assertTrue(results.containsKey("java"));
        Map<String, Object> stats = results.get("java");

        // "java java" → howManyTimes finds "java" 2 times, counted only once
        assertEquals(2, stats.get("count"));

        @SuppressWarnings("unchecked")
        Set<Integer> lines = (Set<Integer>) stats.get("lines");
        assertEquals(1, lines.size());
        assertTrue(lines.contains(1));
    }
}
