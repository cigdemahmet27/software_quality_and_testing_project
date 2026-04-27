package humaneval.llm1.task26;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Function;

public class Solution {
    /**
    From a list of integers, remove all elements that occur more than once.
    Keep order of elements left the same as in the input.
    >>> removeDuplicates(Array.asList(1, 2, 3, 2, 4))
    [1, 3, 4]
     */
    public List<Integer> removeDuplicates(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return new ArrayList<>();
        }

        // Step 1: Count the frequency of each number in the input list.
        // We use a Map<Integer, Long> where the key is the number and the value is its count.
        Map<Integer, Long> frequencies = numbers.stream()
                                               .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Step 2: Filter the original list, keeping only elements that appeared exactly once.
        // The order of elements is preserved because we are streaming the original 'numbers' list
        // and filtering it in the order they appear.
        return numbers.stream()
                      .filter(number -> frequencies.get(number) == 1)
                      .collect(Collectors.toList());
    }
}