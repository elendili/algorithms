package leetcode.top_interview_questions.easy.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KnuthMorrisPratt1 {
    private final int[] failureTable;
    private final String pattern;

    KnuthMorrisPratt1(String pattern) {
        this.pattern = pattern;
        this.failureTable = failureTable(pattern);
    }

    private int[] failureTable(String pattern) {
        int[] out = new int[pattern.length()];
        if (pattern.length() > 0) {
            out[0] = -1;
            for (int i = 1,
                 cnd = 0; // candidate
                 i < pattern.length(); i++, cnd++) {
                if (pattern.charAt(i) == pattern.charAt(cnd)) {
                    out[i] = out[cnd];
                } else {
                    out[i] = cnd;
                    while (cnd >= 0 && pattern.charAt(i) != pattern.charAt(cnd)) {
                        cnd = out[cnd];
                    }
                }
            }
        }
        return out;
    }

    public int first(String text) {
        if (pattern.length() > 0) {
            for (int j = 0, k = 0; j < text.length(); ) {
                if (text.charAt(j) == pattern.charAt(k)) {
                    j += 1;
                    k += 1;
                    if (k == pattern.length()) {
                        return j - k;
                    }
                } else {
                    k = failureTable[k];
                    if (k < 0) {
                        k += 1;
                        j += 1;
                    }
                }
            }
        }
        return -1;
    }
}


class KnuthMorrisPratt2 {
    private final int[] failureTable;
    private final String pattern;

    KnuthMorrisPratt2(String pattern) {
        this.pattern = pattern;
        this.failureTable = failureTable(pattern);
    }

    int[] failureTable(String s) {
        int[] out = new int[s.length()];
        if (s.length() > 0) {
            out[0] = -1;
            for (int i = 1,
                 cnd = 0;
                 i < s.length(); i++, cnd++) {
                if (s.charAt(i) == s.charAt(cnd)) {
                    out[i] = out[cnd];
                } else {
                    out[i] = cnd;
                    while (cnd > -1 && s.charAt(i) != s.charAt(cnd)) {
                        cnd = out[cnd];
                    }
                }
            }
        }
        return out;
    }

    int first(String text) {
        if (pattern.length() > 0) {
            for (int ti = 0, pi = 0; ti < text.length(); ) {
                if (text.charAt(ti) == pattern.charAt(pi)) {
                    pi += 1;
                    ti += 1;
                    if (pi == pattern.length()) {
                        return ti - pi;
                    }
                } else {
                    pi = failureTable[pi];
                    if (pi < 0) {
                        pi += 1;
                        ti += 1;
                    }
                }
            }
        }
        return -1;
    }
}

class Tester {
    @Test
    public void text1() {
        Assertions.assertEquals(0,
                new KnuthMorrisPratt1("aabaab")
                        .first("aabaab"));
        Assertions.assertEquals(1,
                new KnuthMorrisPratt1("lololo")
                        .first("olololo"));
        Assertions.assertEquals(2,
                new KnuthMorrisPratt1("ll")
                        .first("hello"));
        Assertions.assertEquals(4,
                new KnuthMorrisPratt1("ohellohe")
                        .first("hellohellohello"));
        Assertions.assertEquals(-1,
                new KnuthMorrisPratt1("")
                        .first("hello"));
        Assertions.assertEquals(-1,
                new KnuthMorrisPratt1("z")
                        .first("dfasdf"));
        Assertions.assertEquals(1,
                new KnuthMorrisPratt1("aaa aaa aaa")
                        .first("aaaa aaa aaa aaa"));
    }

    @Test
    public void text2() {
        Assertions.assertEquals(0,
                new KnuthMorrisPratt2("aabaab")
                        .first("aabaab"));
        Assertions.assertEquals(1,
                new KnuthMorrisPratt2("lololo")
                        .first("olololo"));
        Assertions.assertEquals(2,
                new KnuthMorrisPratt2("ll")
                        .first("hello"));
        Assertions.assertEquals(4,
                new KnuthMorrisPratt2("ohellohe")
                        .first("hellohellohello"));
        Assertions.assertEquals(-1,
                new KnuthMorrisPratt2("")
                        .first("hello"));
        Assertions.assertEquals(-1,
                new KnuthMorrisPratt2("z")
                        .first("dfasdf"));
        Assertions.assertEquals(1,
                new KnuthMorrisPratt2("aaa aaa aaa")
                        .first("aaaa aaa aaa aaa"));
    }
}

class KnuthMorrisPratt_NoNegative {
    public int findPatternInText(String pattern, String text) {
        int[] prefix_suffix_array = failure_table(pattern);
        for (int i = 0, j = 0;
             i < text.length();
        ) {
            if (text.charAt(i) == pattern.charAt(j)) {
                j += 1;
                i += 1;
                if (j == pattern.length()) {
                    return i - j;
                }
            } else {
                int newTextIndex = i - j + 1;
                j -= 1;
                if (j < 0) {
                    j = 0;
                }
                j = prefix_suffix_array[j];
                if (j == 0) {
                    i = newTextIndex;
                } else {
                    i += 1;
                }
            }
        }
        return -1;
    }

    int[] failure_table(String s) {
        int[] out = new int[s.length()];
        for (int i = 1, j = 0; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = out[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            out[i] = j;
        }
        return out;
    }

    @Test
    public void prefix_functionTest() {
        assertEquals(Arrays.toString(new int[]{0, 1, 0, 1, 2, 3}),
                Arrays.toString(failure_table("aabaab")));
        assertEquals(Arrays.toString(new int[]{0, 1, 0, 1, 2, 3, 4, 5, 6}),
                Arrays.toString(failure_table("aabaabaab")));
        assertEquals(Arrays.toString(new int[]{0, 1, 0, 1, 2, 3, 4, 5, 6}),
                Arrays.toString(failure_table("aabaabaa2")));
    }

    @Test
    public void findPatternInTextTest() {
        assertEquals(1,
                findPatternInText("b", "ab"));
        assertEquals(1,
                findPatternInText("ab", "aabaab"));
        assertEquals(0,
                findPatternInText("aabaab", "aabaab"));
        assertEquals(2,
                findPatternInText("baab", "aabaab"));
        assertEquals(-1,
                findPatternInText("baaba", "aabaab"));
    }
}