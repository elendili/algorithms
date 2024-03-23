package leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/problems/minimum-window-substring/description/
 */
public class MinimumWindowSubstring {
    static class Window {
        private final int[] freq = new int[58]; // A - 65, Z=90, a=97, z=122. 122-65=57
        private int totalCount;
        private int uniqCount;

        public boolean includes(Window w) {
            if (totalCount >= w.totalCount
                    && uniqCount >= w.uniqCount) {
                for (int i = 0; i < w.freq.length; i++) {
                    if (freq[i] < w.freq[i]) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }

        public void add(char c) {
            int i = c - 'A';
            if (freq[i] == 0) {
                uniqCount++;
            }
            freq[i] += 1;
            totalCount++;
        }

        public void remove(char c) {
            int i = c - 'A';
            freq[i] -= 1;
            if (freq[i] == 0) {
                uniqCount--;
            }
            totalCount--;
        }

        static Window fromString(String s) {
            Window out = new Window();
            for (char c : s.toCharArray()) {
                out.add(c);
            }
            return out;
        }
    }

    public String minWindow(String s, String t) {
        Window tw = Window.fromString(t);
        Window sw = new Window();
        int min = Integer.MAX_VALUE;
        int minStart = -1, minEnd = -1;
        for (int start = 0, end = 0; end < s.length(); end++) {
            sw.add(s.charAt(end));
            while (start <= end && sw.includes(tw)) {
                final int l = end - start + 1;
                if (l < min) {
                    min = l;
                    minStart = start;
                    minEnd = end;
                }
                sw.remove(s.charAt(start));
                start++;
            }
        }

        if (min == Integer.MAX_VALUE) {
            return "";
        }
        if (minStart > -1) {
            return s.substring(minStart, minEnd + 1);
        }
        return s;
    }

    @Test
    public void test() {
        Assertions.assertEquals("BANC", minWindow("ADOBECODEBANC", "ABC"));
        Assertions.assertEquals("ADOBECODEBANC", minWindow("ADOBECODEBANC", "AODBECODEBANC"));
        Assertions.assertEquals("", minWindow("A", "a"));
        Assertions.assertEquals("", minWindow("a", "A"));
        Assertions.assertEquals("", minWindow("a", "Z"));
        Assertions.assertEquals("", minWindow("Z", "a"));
        Assertions.assertEquals("a", minWindow("a", "a"));
        Assertions.assertEquals("", minWindow("a", "aa"));
    }
    @Test
    public void test2() {
        Assertions.assertEquals("fsrvmrnczjzjevkdvroiluthhpqtff", minWindow("cgklivwehljxrdzpfdqsapogwvjtvbzahjnsejwnuhmomlfsrvmrnczjzjevkdvroiluthhpqtffhlzyglrvorgnalk", "mqfff"));
    }
}
