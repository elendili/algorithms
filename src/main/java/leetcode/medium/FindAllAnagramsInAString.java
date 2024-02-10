package leetcode.medium;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 */
public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> out = new ArrayList<>();
        int n = p.length();
        int[] freqs = getFreqs(p);
        int[] window = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            window[c - 'a']++; // sliding window
            if (i >= n - 1) {
                int begin = i - n + 1;
                if (freqs[s.charAt(begin) - 'a'] > 0) {
                    if (Arrays.equals(freqs, window)) {
                        out.add(begin);
                    }
                }
                window[s.charAt(begin) - 'a']--;
            }

        }
        return out;
    }

    int[] getFreqs(String s) {
        int[] f = new int[26];
        for (char c : s.toCharArray()) {
            f[c - 'a'] += 1;
        }
        return f;
    }

    @Test
    public void test() {
        assertEquals("[]", findAnagrams("cbaebabacd", "qw").toString());
        assertEquals("[0, 6]", findAnagrams("cbaebabacd", "abc").toString());
        assertEquals("[0, 1, 2]", findAnagrams("abab", "ab").toString());
    }

}
