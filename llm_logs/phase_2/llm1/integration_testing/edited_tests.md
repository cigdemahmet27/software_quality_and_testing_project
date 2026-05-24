# Integration Test Generation Prompt — LLM1: Gemini (Edited BookScan)

## Prompt Sent to LLM

Generate JUnit 5 integration tests for the following `BookScan` class in package `phase_2.llm1.edited`.

The integration tests should verify:
1. The three utility methods (`howManyTimes`, `strlen`, `flipCase`) work correctly together
2. The main scanning method produces correct word counts by length
3. Line number tracking is accurate across multi-line text
4. Case-insensitive matching works properly (using `flipCase`)
5. Edge cases: empty text, no matches, single-word lines, special characters, overlapping substrings

Here is the BookScan class to test:

```java
// TODO: Paste the actual BookScan code here before sending to LLM
```

Use `org.junit.jupiter.api.Test` and `org.junit.jupiter.api.Assertions.*`.
Add the following header:
```java
/* @Authors
 * Student Names: Ahmet Enes Çiğdem, Ali Eren Çiftçi, Taha Çali
 * Student IDs: 150220079, 150220022, 150220050
 */
```

---

## LLM Response

> **[PASTE GEMINI'S RESPONSE HERE]**

---

## Usage Note

> Integration tests for the edited prompt version of BookScan (LLM1).
