package humaneval.llm1.task16;

import java.util.*;
import java.lang.*;

public class Solution {
    /**
    Given a string, find out how many distinct characters (regardless of case) does it consist of
    >>> countDistinctCharacters("xyzXYZ")
    3
    >>> countDistinctCharacters("Jerry")
    4
     */
    public int countDistinctCharacters(String string) {
        if (string == null || string.isEmpty()) {
            return 0;
        }

        Set<Character> distinctChars = new HashSet<>();

        for (char c : string.toCharArray()) {
            // Convert character to lowercase before adding to the set
            distinctChars.add(Character.toLowerCase(c));
        }

        return distinctChars.size();
    }
}