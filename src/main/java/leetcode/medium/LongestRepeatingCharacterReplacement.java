package leetcode.medium;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestRepeatingCharacterReplacement {

    static class Freqs {
        private final int[] freqs = new int[26];
        private int maxEverSymbolCount = 0;

        public void addChar(char c) {
            int ci = c - 'A';
            freqs[ci]++;
            maxEverSymbolCount = Math.max(maxEverSymbolCount,  freqs[ci]);
        }

        public void removeChar(char c) {
            int ci = c - 'A';
            freqs[ci]--;
        }

        int maxEverSymbolCount() {
            return maxEverSymbolCount;
        }
    }

    /**
     * use sliding window and max frequent character count (maxSymbolCount),
     * window increased to the left when window size <= maxSymbolCount+k
     */
    public int characterReplacement(String s, int k) {
        int n = s.length();
        if (n <= k) {
            return n;
        }
        int maxValidLength = 0;
        Freqs freqs = new Freqs();
        for (int end = 0, start=0; end < n; end++) {  // ABABB , 1
            freqs.addChar(s.charAt(end));
            // window is valid when its size <=
            // <= replacements + max symbol count ever found in window
            // the assumption that window can't decrease in size
            // from its previously valid length
            // if valid, increase window by default
            // if not valid, move window to the right
            boolean valid = (end - start + 1) <= k + freqs.maxEverSymbolCount();
            if(!valid){
                freqs.removeChar(s.charAt(start));
                start++;
            }
            maxValidLength = end - start + 1; // keep valid window
        }

        return maxValidLength;
    }

    @Test
    public void test() {
        assertEquals(1, characterReplacement("ABAB", 0));
        assertEquals(2, characterReplacement("ABB", 0));
        assertEquals(1, characterReplacement("ABC", 0));
        assertEquals(0, characterReplacement("", 0));
        assertEquals(4, characterReplacement("ABAB", 2));
        assertEquals(4, characterReplacement("AABABBA", 1));
        assertEquals(5, characterReplacement("AABABBA", 2));
        assertEquals(5, characterReplacement("ABABAB", 2));
        assertEquals(4, characterReplacement("ABCABC", 2));
        assertEquals(3, characterReplacement("ABCDABCD", 2));
    }
}
