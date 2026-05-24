/* @Authors
 * Student Names: Ahmet Enes Çiğdem
 * Student IDs: 150220079
 *
 * Integration tests for BookScan (LLM2: Claude — Unmodified Prompt Version)
 */
package phase_2.llm2.unmodified;

import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BookScanTest {

    // ==========================================
    // 1. & 2. Integration & Word Counts
    // ==========================================
    @Test
    void testScan_standardMultiLineText() {
        BookScan scanner = new BookScan();
        String text = "Hello world\nThis is a test\nHello there";
        
        // Target length 5: "Hello", "world", "there"
        Map<String, BookScan.WordResult> results = scanner.scan(text, 5);
        
        assertEquals(3, results.size(), "Should find exactly 3 unique words of length 5. Keys: " + results.keySet());

        // Verify 'hello'
        assertTrue(results.containsKey("hello"), "Map keys: " + results.keySet());
        BookScan.WordResult helloResult = results.get("hello");
        assertEquals("hello", helloResult.word);
        assertEquals(2, helloResult.count, "Word 'Hello' appears twice in the text");
        assertEquals(Arrays.asList(1, 3), helloResult.lineNumbers, "Appears on lines 1 and 3");

        // Verify 'world'
        assertTrue(results.containsKey("world"));
        assertEquals("world", results.get("world").word);
        assertEquals(1, results.get("world").count);
        
        // Verify 'there'
        assertTrue(results.containsKey("there"), "Map keys: " + results.keySet());
        assertEquals("there", results.get("there").word);
    }

    // ==========================================
    // 3. Line Number Tracking
    // ==========================================
    @Test
    void testScan_accurateLineNumbers() {
        BookScan scanner = new BookScan();
        // Since split("\n") is used, let's create a specific structure
        String text = "\n\nJava\n\n\nJava\n";
        
        Map<String, BookScan.WordResult> results = scanner.scan(text, 4);
        
        assertTrue(results.containsKey("java"));
        BookScan.WordResult javaResult = results.get("java");
        assertEquals(2, javaResult.count);
        
        // Lines:
        // 0: ""
        // 1: ""
        // 2: "Java"
        // 3: ""
        // 4: ""
        // 5: "Java"
        // 1-indexed lines: 3 and 6
        assertEquals(Arrays.asList(3, 6), javaResult.lineNumbers);
    }

    // ==========================================
    // 4. Case-Sensitivity & FlipCase
    // ==========================================
    @Test
    void testScan_caseSensitivityAndFlipCase() {
        BookScan scanner = new BookScan();
        String text = "Cat cat CAT";
        
        // The implementation normalizes keys to lowercase
        Map<String, BookScan.WordResult> results = scanner.scan(text, 3);
        
        assertEquals(1, results.size());
        
        // Verify case-insensitive aggregation works correctly
        assertTrue(results.containsKey("cat"));
        BookScan.WordResult catResult = results.get("cat");
        
        // Each exact case match contributes to the count. Since howManyTimes looks at lowercase line, "cat" is in "cat cat cat" 3 times
        // The outer loop runs 3 times (once for each token), so 3 + 3 + 3 = 9
        assertEquals(9, catResult.count, "Because of a logical error in LLM2 unmodified code, it counts occurrences repeatedly for each matching token");
    }

    // ==========================================
    // 5. Edge Cases
    // ==========================================
    @Test
    void testScan_emptyAndNullText() {
        BookScan scanner = new BookScan();
        
        Map<String, BookScan.WordResult> nullResult = scanner.scan(null, 5);
        assertTrue(nullResult.isEmpty(), "Null text should return an empty map");
        
        Map<String, BookScan.WordResult> emptyResult = scanner.scan("", 5);
        assertTrue(emptyResult.isEmpty(), "Empty text should return an empty map");
    }

    @Test
    void testScan_noMatches() {
        BookScan scanner = new BookScan();
        String text = "One two four";
        
        // Target length 10 (no words of this length)
        Map<String, BookScan.WordResult> results = scanner.scan(text, 10);
        assertTrue(results.isEmpty(), "Should return empty map if no words match target length");
    }

    @Test
    void testScan_specialCharactersAndSingleWordLines() {
        BookScan scanner = new BookScan();
        String text = "Wow!\nIt's a test.\n1234\n$Word";
        
        // Since LLM2 unmodified splits by \\s+ and doesn't strip punctuation:
        // "Wow!" length is 4.
        // "It's" length is 4.
        // "a" length is 1.
        // "test." length is 5.
        // "1234" length is 4.
        // "$Word" length is 5.
        Map<String, BookScan.WordResult> results = scanner.scan(text, 4);
        
        assertEquals(3, results.size(), "Should find 'wow!', 'it's', and '1234'");
        
        assertTrue(results.containsKey("wow!"));
        assertTrue(results.containsKey("it's"));
        assertTrue(results.containsKey("1234"));
    }

    @Test
    void testScan_overlappingSubstrings() {
        BookScan scanner = new BookScan();
        // The word "ana" has length 3.
        // In "banana", it appears twice: b(ana)na and ban(ana)
        // Also appears as a standalone word "ana".
        String text = "ana loves banana";
        
        Map<String, BookScan.WordResult> results = scanner.scan(text, 3);
        
        assertTrue(results.containsKey("ana"));
        BookScan.WordResult anaResult = results.get("ana");
        
        // "ana" stands alone (count +1).
        // Wait, LLM2 splits by \s+, so it evaluates "ana" (length 3), then evaluates "loves" (length 5), then "banana" (length 6).
        // Since "banana" has length 6, it won't be processed for targetLength = 3!
        // This is a flaw/design of LLM2 unmodified. It only calls howManyTimes for words that exactly match target length.
        // But the occurrence count is done on the entire line via howManyTimes("ana loves banana", "ana"), which yields 3.
        assertEquals(3, anaResult.count, "Because 'ana' is length 3, it triggers howManyTimes, which counts 3 occurrences in the line.");
        assertEquals(Arrays.asList(1), anaResult.lineNumbers);
    }
}
