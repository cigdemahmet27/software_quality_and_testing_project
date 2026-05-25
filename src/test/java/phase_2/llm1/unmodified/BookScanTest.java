/* @Authors
 * Student Names: Taha Çali, Ahmet Enes Çiğdem, Ali Eren Çiftçi
 * Student IDs: 150220050, 150220079, 150220022
 *
 * Integration tests for BookScan (LLM1: Gemini — Unmodified Prompt Version)
 */
package phase_2.llm1.unmodified;

import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BookScanTest {

    // ==========================================
    // 1. & 2. Integration & Word Counts
    // ==========================================
    @Test
    void testAnalyzeText_standardMultiLineText() {
        BookScan scanner = new BookScan();
        String text = "Hello world\nThis is a test\nHello again";
        
        // Target length 5: "Hello", "world", "again"
        Map<String, BookScan.WordStats> results = scanner.analyzeText(text, 5);
        
        assertEquals(3, results.size(), "Should find exactly 3 unique words of length 5");

        // Verify 'Hello'
        assertTrue(results.containsKey("Hello"));
        BookScan.WordStats helloStats = results.get("Hello");
        assertEquals("hELLO", helloStats.flippedWord);
        assertEquals(2, helloStats.totalOccurrences, "Substring 'Hello' appears twice in the text");
        assertEquals(Set.of(1, 3), helloStats.linesAppeared, "Appears on lines 1 and 3");

        // Verify 'world'
        assertTrue(results.containsKey("world"));
        assertEquals("WORLD", results.get("world").flippedWord);
        assertEquals(1, results.get("world").totalOccurrences);
        
        // Verify 'again'
        assertTrue(results.containsKey("again"));
        assertEquals("AGAIN", results.get("again").flippedWord);
    }

    // ==========================================
    // 3. Line Number Tracking
    // ==========================================
    @Test
    void testAnalyzeText_accurateLineNumbers() {
        BookScan scanner = new BookScan();
        String text = "\n\nJava\n\n\nJava\n";
        
        Map<String, BookScan.WordStats> results = scanner.analyzeText(text, 4);
        
        assertTrue(results.containsKey("Java"));
        BookScan.WordStats javaStats = results.get("Java");
        assertEquals(2, javaStats.totalOccurrences);
        
        // Due to regex \r?\n split, lines are indexed logically based on split output
        // Splitting "\n\nJava\n\n\nJava\n" -> ["", "", "Java", "", "", "Java"]
        // So "Java" is at indices 2 and 5 -> Lines 3 and 6
        assertEquals(Set.of(3, 6), javaStats.linesAppeared);
    }

    // ==========================================
    // 4. Case-Sensitivity & FlipCase
    // ==========================================
    @Test
    void testAnalyzeText_caseSensitivityAndFlipCase() {
        BookScan scanner = new BookScan();
        String text = "Cat cat CAT";
        
        // The implementation's howManyTimes is case-sensitive, so we should get 3 distinct keys
        Map<String, BookScan.WordStats> results = scanner.analyzeText(text, 3);
        
        assertEquals(3, results.size());
        
        // Verify flipCase correctly flipped each variation
        assertEquals("cAT", results.get("Cat").flippedWord);
        assertEquals("CAT", results.get("cat").flippedWord);
        assertEquals("cat", results.get("CAT").flippedWord);
        
        // Each exact case match appears exactly 1 time in the whole string
        assertEquals(1, results.get("Cat").totalOccurrences);
        assertEquals(1, results.get("cat").totalOccurrences);
        assertEquals(1, results.get("CAT").totalOccurrences);
    }

    // ==========================================
    // 5. Edge Cases
    // ==========================================
    @Test
    void testAnalyzeText_emptyAndNullText() {
        BookScan scanner = new BookScan();
        
        Map<String, BookScan.WordStats> nullResult = scanner.analyzeText(null, 5);
        assertTrue(nullResult.isEmpty(), "Null text should return an empty map");
        
        Map<String, BookScan.WordStats> emptyResult = scanner.analyzeText("", 5);
        assertTrue(emptyResult.isEmpty(), "Empty text should return an empty map");
    }

    @Test
    void testAnalyzeText_noMatches() {
        BookScan scanner = new BookScan();
        String text = "One two four";
        
        // Target length 10 (no words of this length)
        Map<String, BookScan.WordStats> results = scanner.analyzeText(text, 10);
        assertTrue(results.isEmpty(), "Should return empty map if no words match target length");
    }

    @Test
    void testAnalyzeText_specialCharactersAndSingleWordLines() {
        BookScan scanner = new BookScan();
        String text = "Wow!\nIt's a test.\n1234\n$Word";
        
        // Split by \W+ breaks words on punctuation.
        // "Wow!" -> "Wow" (3), "It's" -> "It" (2), "s" (1)
        // "$Word" -> "" (0), "Word" (4)
        Map<String, BookScan.WordStats> results = scanner.analyzeText(text, 4);
        
        assertEquals(3, results.size(), "Should find 'test', '1234', and 'Word'");
        
        assertTrue(results.containsKey("test"));
        assertEquals("TEST", results.get("test").flippedWord);
        
        assertTrue(results.containsKey("1234"));
        assertEquals("1234", results.get("1234").flippedWord, "Non-alphabetic chars stay unchanged");
        
        assertTrue(results.containsKey("Word"));
        assertEquals("wORD", results.get("Word").flippedWord);
    }

    @Test
    void testAnalyzeText_overlappingSubstrings() {
        BookScan scanner = new BookScan();
        // The word "ana" has length 3.
        // In "banana", it appears twice: b(ana)na and ban(ana)
        // Also appears as a standalone word "ana".
        String text = "ana loves banana";
        
        Map<String, BookScan.WordStats> results = scanner.analyzeText(text, 3);
        
        assertTrue(results.containsKey("ana"));
        BookScan.WordStats anaStats = results.get("ana");
        
        assertEquals("ANA", anaStats.flippedWord);
        // 1 standalone + 2 overlapping inside "banana" = 3 total occurrences
        assertEquals(3, anaStats.totalOccurrences, "howManyTimes should catch overlapping matches");
        assertEquals(Set.of(1), anaStats.linesAppeared);
    }
}