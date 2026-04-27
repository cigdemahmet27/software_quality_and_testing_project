package humaneval.llm2.task29;

import java.util.*;
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
        return strings.stream().filter(s -> s.startsWith(prefix)).collect(Collectors.toList());
    }
}
