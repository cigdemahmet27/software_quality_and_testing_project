package humaneval.improved.llm1.task11;

import humaneval.llm1.task11.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task11ImprovedTest {
    @Test
    void testStringXor() {
        Solution s = new Solution();
        
        // Branch: lenA < lenB
        assertEquals("111", s.stringXor("10", "101"));
        
        // Branch: lenA > lenB
        assertEquals("111", s.stringXor("101", "10"));
        
        // Branch: charA == charB
        assertEquals("000", s.stringXor("101", "101"));
        
        // Branch: charA != charB
        assertEquals("100", s.stringXor("010", "110"));
        
        // Both empty
        assertEquals("", s.stringXor("", ""));
    }
}
