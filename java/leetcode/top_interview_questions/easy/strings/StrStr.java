package leetcode.top_interview_questions.easy.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/127/strings/885/
Return the index of the first occurrence of needle in haystack,
or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
Clarification:

What should we return when needle is an empty string?
This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string.
This is consistent to C's strstr() and Java's indexOf().

Constraints:

haystack and needle consist only of lowercase English characters.
 */
public class StrStr {

    // use Knuth–Morris–Pratt algorithm
    // https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm
    public int strStr(String text, String word) {
        if (word == null || word.isEmpty()) {
            return 0;
        } else if (word.length() > text.length()) {
            return -1;
        }

        final char[] ta = text.toCharArray();
        final char[] wa = word.toCharArray();
        final int[] jump_indexes = kmpTable(wa);

        for (int it = 0, // index in text
             iw = 0 // index in word
             ; it < ta.length;
        ) {
            if (ta[it] == wa[iw]) {
                it++;
                iw++;
                if (iw == wa.length) {
                    return it - iw;
                }
            } else {
                iw = jump_indexes[iw];
                if (iw < 0) {
                    it++;
                    iw++;
                }
            }
        }
        return -1;
    }

    // Partial match table  == failure function
    public int[] kmpTable(char[] wa) {
        int[] out = new int[wa.length];
        out[0] = -1;
        for (int i = 1,
             cndi = 0; // index_of_next_character_of_the_current_candidate_substring
             i < wa.length;
             i++, cndi++
        ) {
            if (wa[i] == wa[cndi]) {
                out[i] = out[cndi];
            } else {
                out[i] = cndi;
                while (cndi >= 0 && wa[i] != wa[cndi]) {
                    cndi = out[cndi];
                }
            }
        }
        return out;
    }

    @Test
    public void test() {
        assertEquals(4, strStr("hellohellohello", "ohellohe"));
        assertEquals(2, strStr("hello", "ll"));
        assertEquals(0, strStr("hello", ""));
        assertEquals(-1, strStr("hello", "le"));
        assertEquals(-1, strStr("hello", "z"));
        assertEquals(-1, strStr("hello", "hello2"));
    }

}


