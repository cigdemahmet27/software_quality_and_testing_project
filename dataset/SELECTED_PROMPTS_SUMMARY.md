# Selected Prompts — Summary

## Selection Criteria
- 30 prompts from 164 total in HumanEval-X Java dataset
- Distribution: 15 Easy / 10 Moderate / 5 Hard
- Covers string, math, list, and logic problem domains
- Includes mandatory tasks #18, #23, #27 for Phase 1.2 BookScan class

---

## Easy (15 prompts)

| Task # | Function Name           | Description                                                      |
|--------|------------------------|------------------------------------------------------------------|
| 2      | truncateNumber         | Return the decimal part of a positive float                      |
| 5      | intersperse            | Insert delimiter between every two consecutive list elements     |
| 8      | sumProduct             | Return [sum, product] of all integers in a list                  |
| 9      | rollingMax             | Generate list of rolling maximum values                          |
| 14     | allPrefixes            | Return all prefixes of a string from shortest to longest         |
| 15     | stringSequence         | Return space-delimited numbers from 0 to n                      |
| 16     | countDistinctCharacters| Count distinct characters regardless of case                    |
| 23 ★   | strlen                 | Return length of given string                                    |
| 27 ★   | flipCase               | Flip lowercase↔uppercase for each character                     |
| 28     | concatenate            | Concatenate list of strings into a single string                 |
| 30     | getPositive            | Return only positive numbers from a list                         |
| 34     | unique                 | Return sorted unique elements from a list                        |
| 35     | maxElement             | Return maximum element in a list                                 |
| 42     | incrList               | Increment all list elements by 1                                 |
| 45     | triangleArea           | Calculate triangle area from base and height                     |

## Moderate (10 prompts)

| Task # | Function Name           | Description                                                      |
|--------|------------------------|------------------------------------------------------------------|
| 0      | hasCloseElements       | Check if any two numbers are closer than a threshold             |
| 3      | belowZero              | Detect if account balance falls below zero                       |
| 7      | filterBySubstring      | Filter strings that contain a given substring                    |
| 11     | stringXor              | Perform binary XOR on two binary strings                         |
| 13     | greatestCommonDivisor  | Return GCD of two integers                                       |
| 18 ★   | howManyTimes           | Count overlapping occurrences of substring in string             |
| 26     | removeDuplicates       | Remove elements that occur more than once                        |
| 29     | filterByPrefix         | Filter strings that start with a given prefix                    |
| 31     | isPrime                | Check if a number is prime                                       |
| 43     | pairsSumToZero         | Check if two distinct elements sum to zero                       |

## Hard (5 prompts)

| Task # | Function Name           | Description                                                      |
|--------|------------------------|------------------------------------------------------------------|
| 4      | meanAbsoluteDeviation  | Calculate MAD around the mean of a dataset                       |
| 10     | makePalindrome         | Find shortest palindrome beginning with supplied string          |
| 25     | factorize              | Return prime factors in order from smallest to largest           |
| 39     | primeFib               | Return n-th number that is both Fibonacci and prime              |
| 49     | modp                   | Return 2^n modulo p                                              |

---

★ = Mandatory for Phase 1.2 (BookScan class must use these)

## Full prompt data: `selected_prompts.json`
