package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters/?envType=study-plan-v2&envId=premium-algo-100
 */
public class FindKLengthSubstringsWithNoRepeatedCharacters_1100 {
    static class StringWindow {
        final int requiredSize;
        int count = 0;
        int uniqLettersCount = 0;
        int[] freqs = new int[26]; // alphabet size

        StringWindow(int requiredSize) {
            this.requiredSize = requiredSize;
        }

        void add(char c) {
            count++;
            int ci = c - 'a';
            freqs[ci]++;
            if (freqs[ci] == 1) {
                uniqLettersCount++;
            }
        }

        void remove(char c) {
            count--;
            int ci = c - 'a';
            freqs[ci]--;
            if (freqs[ci] == 0) {
                uniqLettersCount--;
            }
        }

        boolean isGood() {
            return count == requiredSize && uniqLettersCount == count;
        }

        @Override
        public String toString() {
            return "{" +
                    "uniqCount=" + uniqLettersCount +
                    ", count=" + count +
                    '}';
        }
    }

    public int numKLenSubstrNoRepeats(String s, int k) {
        int n = s.length();
        if (k > n) return 0;

        StringWindow stringWindow = new StringWindow(k);
        int res = 0, left = 0;
        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);
            stringWindow.add(c);
            // shrink window if duplicate or window too big
            while (left < right && ((right - left + 1) > k || stringWindow.uniqLettersCount < (right - left + 1))) {
                stringWindow.remove(s.charAt(left));
                left++;
            }
            if (stringWindow.isGood()) {
                res++;
            }
        }
        return res;
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            textBlock = "s,   k, expected\n"+
                    "aaa,  1, 3\n"+
                    "aaa,  2, 0\n"+
                    "aba,  2, 2\n"+
                    "abab, 2, 3\n"+
                    "havefunonleetcode, 5, 6\n"+
                    "home, 5, 0\n"
    )
    public void test(String s, int k, int expected) {
        assertEquals(expected, numKLenSubstrNoRepeats(s, k));
    }
}
