package humaneval.llm2.task26;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    /**
    From a list of integers, remove all elements that occur more than once.
    Keep order of elements left the same as in the input.
    >>> removeDuplicates(Arrays.asList(1, 2, 3, 2, 4))
    [1, 3, 4]
     */
    public List<Integer> removeDuplicates(List<Integer> numbers) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : numbers) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        return numbers.stream().filter(n -> counts.get(n) == 1).collect(Collectors.toList());
    }
}
