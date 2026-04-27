package humaneval.llm2.task9;

import java.util.*;

public class Solution {
    /**
    From a given list of integers, generate a list of rolling maximum element found until given moment
    in the sequence.
    >>> rollingMax(Arrays.asList(1, 2, 3, 2, 3, 4, 2))
    [1, 2, 3, 3, 3, 4, 4]
     */
    public List<Integer> rollingMax(List<Integer> numbers) {
        List<Integer> result = new ArrayList<>();
        if (numbers.size() == 0) {
            return result;
        }
        int currentMax = numbers.get(0);
        result.add(currentMax);
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) > currentMax) {
                currentMax = numbers.get(i);
            }
            result.add(currentMax);
        }
        return result;
    }
}
