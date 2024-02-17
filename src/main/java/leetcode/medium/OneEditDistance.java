package leetcode.medium;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * https://leetcode.com/problems/one-edit-distance/
 */
public class OneEditDistance {
    public boolean isOneEditDistance(String s, String t) {
        int ns = s.length();
        int nt = t.length();
        // t length should be bigger
        if (ns>nt){
            return isOneEditDistance(t,s);
        }
        if(nt-ns>1){
            return false;
        }

        for (int i = 0; i < ns; i++) {
            if(s.charAt(i)!=t.charAt(i)){
                if(ns==nt){
                    // check that only 1 replacement is required
                    return s.substring(i+1).equals(t.substring(i+1));
                } else {
                    // check that only one add is required in s, thus skip 1 char in t
                    return s.substring(i).equals(t.substring(i+1));
                }
            }
        }
        // at this point s.substring(0,ns-1) == t
        return  ns+1==nt; // check that only 1 end removal is required for equality to t
    }

    @Test
    public void test() {
        assertTrue(isOneEditDistance("ab", "acb"));
        assertTrue(isOneEditDistance("acb", "ab"));
        assertTrue(isOneEditDistance("abb", "acb"));
        assertTrue(isOneEditDistance("abcd", "abc"));
        assertTrue(isOneEditDistance("abc", "abcd"));

        assertFalse(isOneEditDistance("acb", "abc"));
        assertFalse(isOneEditDistance("a", "aaa"));
        assertFalse(isOneEditDistance("", ""));
    }
}
