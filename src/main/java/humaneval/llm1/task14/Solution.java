package humaneval.llm1.task14;

import java.util.*;

public class Solution {
    /**
    Return list of all prefixes from shortest to longest of the input string
    >>> allPrefixes("abc")
    ["a", "ab", "abc"]
     */
    public List<String> allPrefixes(String string) {
        List<String> prefixes = new ArrayList<>();
        if (string == null || string.isEmpty()) {
            return prefixes;
        }

        for (int i = 1; i <= string.length(); i++) {
            prefixes.add(string.substring(0, i));
        }
        return prefixes;
    }
}