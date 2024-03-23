package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/isomorphic-strings/
 */
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        assert s.length() == t.length();
        // replace both strings with list of numbers and compare lists?
        Set<Character> sCharMapped = new HashSet<>();
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if (map.containsKey(tc)) {
                if (!map.get(tc).equals(sc)) {
                    return false;
                }
            } else if (sCharMapped.contains(sc)) {
                return false;
            } else {
                sCharMapped.add(sc);
                map.put(tc, sc);
            }
        }
        return true;
    }

    @Test
    public void test() {
        Assertions.assertTrue(isIsomorphic("egg", "add"));
        Assertions.assertTrue(isIsomorphic("paper", "title"));
    }

    @Test
    public void test2() {
        Assertions.assertFalse(isIsomorphic("foo", "bar"));
        Assertions.assertFalse(isIsomorphic("bar", "foo"));
    }

    @Test
    public void test30() {
        Assertions.assertFalse(isIsomorphic("bab", "bad"));
    }

    @Test
    public void test3() {
        Assertions.assertFalse(isIsomorphic("baba", "badc"));
        Assertions.assertFalse(isIsomorphic("badc", "baba"));
    }
}
