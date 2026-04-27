package humaneval.llm1.task4;

import java.util.*;

public class Solution {
    /**
    For a given list of input numbers, calculate Mean Absolute Deviation
    around the mean of this dataset.
    Mean Absolute Deviation is the average absolute difference between each
    element and a centerpoint (mean in this case):
    MAD = average | x - x_mean |
    >>> meanAbsoluteDeviation(Arrays.asList(1.0, 2.0, 3.0, 4.0))
    1.0
     */
    public double meanAbsoluteDeviation(List<Double> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0.0; // Mean Absolute Deviation is 0 for an empty or null list
        }

        // Step 1: Calculate the mean of the dataset
        double sum = 0.0;
        for (double number : numbers) {
            sum += number;
        }
        double mean = sum / numbers.size();

        // Step 2: Calculate the sum of absolute differences from the mean
        double absoluteDifferencesSum = 0.0;
        for (double number : numbers) {
            absoluteDifferencesSum += Math.abs(number - mean);
        }

        // Step 3: Calculate the Mean Absolute Deviation
        return absoluteDifferencesSum / numbers.size();
    }
}