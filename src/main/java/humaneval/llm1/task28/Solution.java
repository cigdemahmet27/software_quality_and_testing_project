package humaneval.llm1.task28;

import java.util.*;
import java.lang.*;

public class Solution {
    /**
    Concatenate list of strings into a single string
    >>> concatenate(List.of())
    ""
    >>> concatenate(Arrays.asList("a", "b", "c"))
    "abc"
     */
    public String concatenate(List<String> strings) {
        if (strings == null || strings.isEmpty()) {
            return "";
        }

        StringBuilder resultBuilder = new StringBuilder();
        for (String s : strings) {
            resultBuilder.append(s);
        }
        return resultBuilder.toString();
    }
}