package humaneval.llm1.task29;

import java.util.*;
import java.lang.*;
import java.util.stream.Collectors;

public class Solution {
    /**
    Filter an input list of strings only for ones that start with a given prefix.
    >>> filterByPrefix(List.of(), "a")
    []
    >>> filterByPrefix(Arrays.asList("abc", "bcd", "cde", "array"), "a")
    ["abc", "array"]
     */
    public List<String> filterByPrefix(List<String> strings, String prefix) {
        // Use Java Streams to filter the list.
        // For each string 's' in the list, check if it's not null and starts with the given prefix.
        // Collect the matching strings into a new List.
        return strings.stream()
                      .filter(s -> s != null && s.startsWith(prefix))
                      .collect(Collectors.toList());
    }
}