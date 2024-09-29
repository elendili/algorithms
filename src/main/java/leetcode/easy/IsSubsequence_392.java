package leetcode.easy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * https://leetcode.com/problems/is-subsequence/?envType=study-plan-v2&envId=top-interview-150
 */
public class IsSubsequence_392 {
    public boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) {
            return true;
        }
        int tn = t.length();
        int sn = s.length();
        if (tn < sn) {
            return false;
        }
        int is = 0;
        char[] sa = s.toCharArray();
        char[] ta = t.toCharArray();
        for (int it = 0; it < tn && is < sn; it++) {
            if (sa[is] == ta[it]) {
                is++;
            }
        }
        return is == sn;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertTrue(isSubsequence("", "ahbgdc"));
        assertTrue(isSubsequence("b", "abc"));
        assertTrue(isSubsequence("abc", "abc"));
        assertTrue(isSubsequence("abc", "ahbgdc"));
        assertFalse(isSubsequence("abc", "ahbgdd"));
        assertFalse(isSubsequence("axc", "ahbgdc"));
        assertTrue(isSubsequence("axc", "ahbgxc"));
    }
}
