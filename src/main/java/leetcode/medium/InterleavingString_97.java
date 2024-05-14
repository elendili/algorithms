package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * https://leetcode.com/problems/interleaving-string/description/
 */
public class InterleavingString_97 {
    interface InterleavingString {
        boolean isInterleave(String s1, String s2, String s3);
    }

    static class TopDown implements InterleavingString {
        private String s1;
        private String s2;
        private String s3;
        Boolean[][] memo;

        public boolean isInterleave(String s1, String s2, String s3) {
            this.s1 = s1;
            this.s2 = s2;
            this.s3 = s3;
            if (s3.length() != s1.length() + s2.length()) {
                return false;
            }
            this.memo = new Boolean[s1.length() + 1][s2.length() + 1];
            return recurse(0, 0);
        }

        boolean recurse(int i1, int i2) {
            if (memo[i1][i2] == null) {
                boolean out = false;
                int i3 = (i1 + i2);
                if (i1 == s1.length() && i2 == s2.length() && i3 == s3.length()) {
                    out = true;
                } else if (i3 < s3.length()) {
                    char c3 = s3.charAt(i3);
                    if (i1 < s1.length() && s1.charAt(i1) == c3) {
                        out |= recurse(i1 + 1, i2);
                    }
                    if (i2 < s2.length() && s2.charAt(i2) == c3) {
                        out |= recurse(i1, i2 + 1);
                    }
                }
                memo[i1][i2] = out;
            }
            return memo[i1][i2];
        }
    }

    public static Stream<InterleavingString> implementationsSource() {
        return Stream.of(new TopDown());
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void test(InterleavingString impl) {
        assertTrue(impl.isInterleave("", "", ""));
        assertTrue(impl.isInterleave("aabcc", "", "aabcc"));
        assertTrue(impl.isInterleave("", "aabcc", "aabcc"));
        assertTrue(impl.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        assertFalse(impl.isInterleave("aabcc", "dbbca", "aadbbcbca"));
        assertFalse(impl.isInterleave("aabcc", "dbbca", "aadbbcbcacc"));
        assertFalse(impl.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
    }
}
