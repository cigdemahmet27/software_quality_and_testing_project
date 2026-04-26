package humaneval.improved.llm1.task18;

import humaneval.llm1.task18.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task18ImprovedTest {
    @Test
    void testHowManyTimes() {
        Solution s = new Solution();
        
        // Branch: string == null
        assertEquals(0, s.howManyTimes(null, "a"));
        
        // Branch: substring == null
        assertEquals(0, s.howManyTimes("a", null));
        
        // Branch: string.isEmpty() && !substring.isEmpty()
        assertEquals(0, s.howManyTimes("", "a"));
        
        // Branch: string.isEmpty() && substring.isEmpty()
        assertEquals(0, s.howManyTimes("", ""));
        
        // Branch: substring.isEmpty()
        assertEquals(0, s.howManyTimes("abc", ""));
        
        // Branch: substring.length() > string.length()
        assertEquals(0, s.howManyTimes("a", "abc"));
        
        // Loop and lastIndex branch
        assertEquals(0, s.howManyTimes("abc", "d"));
        assertEquals(3, s.howManyTimes("aaa", "a"));
        assertEquals(3, s.howManyTimes("aaaa", "aa"));
    }
}
