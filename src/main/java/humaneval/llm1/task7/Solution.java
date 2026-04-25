package humaneval.llm1.task7;

import java.util.*;
import java.lang.*;

public class Solution {
    /**
    Filter an input list of strings only for ones that contain given substring
    >>> filterBySubstring(List.of(), "a")
    []
    >>> filterBySubstring(Arrays.asList("abc", "bacd", "cde", "array"), "a")
    ["abc", "bacd", "array"]
     */
    public List<String> filterBySubstring(List<String> strings, String substring) {
        List<String> result = new ArrayList<>();

        if (strings == null || substring == null) {
            return result; // If input list or substring is null, return empty list
        }

        for (String s : strings) {
            // Only consider non-null strings from the input list
            // and check if they contain the given substring.
            if (s != null && s.contains(substring)) {
                result.add(s);
            }
        }

        return result;
    }
}