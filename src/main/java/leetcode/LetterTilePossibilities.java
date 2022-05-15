package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/letter-tile-possibilities/
/*
PERMUTATION WITH REPETITION
P(n,r)/rep1!*rep2!, where rep1,rep2,repN -- are amount of repetition of diff elements

n!/(n-r+1)!*(rep1!*rep2!*rep3!)

AAB
ABA
BAA

AA
BA
AB

AAB :
n=3,r=2,i=1 => 2
n=3,r=2,i=2 => 3
n=3,r=2,i=3 => 3

ABC
n=3,r=1,i=1 => 3
n=3,r=1,i=2 => 6
n=3,r=1,i=3 => 6

AAA
n=3,r=3,i=1 => 1
n=3,r=1,i=2 => 1
n=3,r=1,i=3 => 1

AAABBC
n=6,r=3x2,i=1 => 3
n=6,r=3x2,i=2 => 8
n=6,r=3x2,i=3 => 19

AAA -> 1
BAA -> 3
ABB -> 3
CAA -> 3
ABC -> 6
BBC -> 3

 */
public class LetterTilePossibilities {
    class Solution2 {
        public int numTilePossibilities(String tiles) {
            char[] s = tiles.toCharArray();
            return numSeq(s, 0);
        }

        private int numSeq(char[] s, int pos) {
            if (pos == s.length - 1) {
                return 1;
            }
            int visited = (1 << (s[pos] - 'A'));
            int res = 1 + numSeq(s, pos + 1);
            for (int i = pos + 1; i < s.length; ++i) {
                if ((visited & (1 << (s[i] - 'A'))) == 0) {
                    visited |= (1 << (s[i] - 'A'));
                    char ch = s[pos];
                    s[pos] = s[i];
                    s[i] = ch;
                    res += 1;
                    res += numSeq(s, pos + 1);
                    ch = s[pos];
                    s[pos] = s[i];
                    s[i] = ch;
                }
            }
            return res;
        }
    }

    static class Solution3 {
        static final byte alphabetSize = 26;

        public int numTilePossibilities(String tiles) {
            int n = tiles.length();
            if (n < 2) {
                return 1;
            }
            final int[] lettersCount = new int[alphabetSize];
            int uniqCount = 0;
            for (int i = 0; i < n; i++) {
                int ci = tiles.charAt(i) - 65;
                uniqCount += lettersCount[ci] == 0 ? 1 : 0;
                lettersCount[ci] = lettersCount[ci] + 1;
            }
            // prepare array
            int[] a = new int[uniqCount];
            for (int i = 0, j = 0; i < alphabetSize; i++) {
                int v = lettersCount[i];
                if (v != 0) {
                    a[j++] = v;
                }
            }
            // calc count
            int out = bfs(a);
            return out;
        }

        int bfs(int[] freqs) {
            int n = freqs.length;
            if (n == 1) {
                return freqs[0];
            } else {
                int out = n;
                for (; n > 0; n--) {
                    int[] z = calcArray(n - 1, freqs);
                    out += bfs(z);
                }
                return out;
            }
        }

        static int[] calcArray(int i, int[] a) {
            int v = a[i] - 1;
            int n = a.length;
            int nn = v == 0 ? n - 1 : n;
            int[] o = new int[nn];
            System.arraycopy(a, 0, o, 0, nn);
            if (v == 0) {
                if (i != n - 1) {
                    o[i] = a[n - 1];
                }
            } else {
                o[i] = v;
            }
            return o;
        }


        @Test
        public void test() {
            Assertions.assertEquals(8, bfs(new int[]{2, 1}));
            Assertions.assertEquals(1, bfs(new int[]{1}));
            Assertions.assertEquals(3, bfs(new int[]{3}));
            Assertions.assertEquals(30 + 30 + (7 + 4 + 7) + 8 + 3, bfs(new int[]{2, 1, 2}));
            Assertions.assertEquals(188, bfs(new int[]{3, 2, 1}));

            Assertions.assertEquals(4, numTilePossibilities("HT"));
            Assertions.assertEquals(1, numTilePossibilities("A"));
            Assertions.assertEquals(4, numTilePossibilities("AB"));
            Assertions.assertEquals(2, numTilePossibilities("AA"));
            Assertions.assertEquals(3, numTilePossibilities("AAA"));
            Assertions.assertEquals(8, numTilePossibilities("AAB"));
            Assertions.assertEquals(188, numTilePossibilities("AAABBC"));
            Assertions.assertEquals(188, numTilePossibilities("AAAZZC"));
        }
    }

    static class Solution {
        static final byte alphabetSize = 26;
        static Map<Integer, Integer> memo = new HashMap<>();

        public int numTilePossibilities(String tiles) {
            int n = tiles.length();
            if (n < 2) {
                return 1;
            }
            final int[] lettersCount = new int[alphabetSize];
            for (int i = 0; i < n; i++) {
                int ci = tiles.charAt(i) - 65;
                lettersCount[ci] = lettersCount[ci] + 1;
            }
            // prepare number
            int number = 0;
            for (int i = 0, pow = 1; i < alphabetSize; i++) {
                int v = lettersCount[i];
                if (v > 0) {
                    number += v * pow;
                    pow *= 10;
                }
            }
            // calc count
            int out = bfs(number);
            return out;
        }

        int bfs(final int freqs) {
            if (freqs < 10) {
                return freqs;
            } else if (memo.containsKey(freqs)) {
                return memo.get(freqs);
            } else {
                int size = (int) Math.log10(freqs) + 1;
                int out = size;
                for (int i = 0; i < size; i++) {
                    int z = calcArray(freqs, size, i);
                    out += bfs(z);
                }
                memo.put(freqs, out);
                return out;
            }
        }

        int calcArray(final int freq, final int size, final int i) {
            int[] a = new int[size];
            int ceiling = (int) Math.pow(10, size);
            for (int pow = 1, j = 0; pow < ceiling; pow *= 10, j++) {
                int curDigit = (freq / pow) % 10;
                a[j] = curDigit;
            }
            a[i]--;
            Arrays.sort(a);
            int j = a[0] < 1 ? 1 : 0;
            int out = 0;
            for (int pow = 1; j < a.length; pow *= 10, j++) {
                int curDigit = a[j] * pow;
                out += curDigit;
            }
            return out;
        }

        @Test
        public void test() {
            Assertions.assertEquals(8, bfs(21));
            Assertions.assertEquals(1, bfs(1));
            Assertions.assertEquals(3, bfs(3));
            Assertions.assertEquals(30 + 30 + (7 + 4 + 7) + 8 + 3, bfs(212));
            Assertions.assertEquals(188, bfs(321));

            Assertions.assertEquals(4, numTilePossibilities("HT"));
            Assertions.assertEquals(1, numTilePossibilities("A"));
            Assertions.assertEquals(4, numTilePossibilities("AB"));
            Assertions.assertEquals(2, numTilePossibilities("AA"));
            Assertions.assertEquals(3, numTilePossibilities("AAA"));
            Assertions.assertEquals(8, numTilePossibilities("AAB"));
            Assertions.assertEquals(188, numTilePossibilities("AAABBC"));
            Assertions.assertEquals(188, numTilePossibilities("AAAZZC"));
        }
    }

}
