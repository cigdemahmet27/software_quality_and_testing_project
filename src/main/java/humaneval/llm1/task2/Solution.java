package humaneval.llm1.task2;


public class Solution {
    /**
    Given a positive floating point number, it can be decomposed into
    and integer part (largest integer smaller than given number) and decimals
    (leftover part always smaller than 1).

    Return the decimal part of the number.
    >>> truncateNumber(3.5)
    0.5
     */
    public double truncateNumber(double number) {
        // The integer part of a positive number can be found using Math.floor()
        // which returns the largest (closest to positive infinity) double value that is less than or equal to the argument
        // and is equal to a mathematical integer.
        double integerPart = Math.floor(number);

        // The decimal part is the difference between the original number and its integer part.
        return number - integerPart;
    }
}