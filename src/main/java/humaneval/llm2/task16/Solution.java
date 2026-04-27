package humaneval.llm2.task16;

import java.util.*;

public class Solution {
    /**
    Given a string, find out how many distinct characters (regardless of case) does it consist of
    >>> countDistinctCharacters("xyzXYZ")
    3
    >>> countDistinctCharacters("Jerry")
    4
     */
    public int countDistinctCharacters(String string) {
        Set<Character> seen = new HashSet<>();
        for (char c : string.toLowerCase().toCharArray()) {
            seen.add(c);
        }
        return seen.size();
    }
}
