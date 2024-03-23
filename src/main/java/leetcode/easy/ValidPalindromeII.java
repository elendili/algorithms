package leetcode.easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * https://leetcode.com/problems/valid-palindrome-ii/description/
 */
public class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                String subWithLeftRemoved = s.substring(left + 1, right + 1);
                String subWithRightRemoved = s.substring(left, right);
                return validPalindromeNoDeletion(subWithLeftRemoved) ||
                        validPalindromeNoDeletion(subWithRightRemoved);
            }
            left++;
            right--;
        }
        return true;
    }

    public boolean validPalindromeNoDeletion(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    @Test
    public void test() {
        assertTrue(validPalindrome("aba"));
        assertTrue(validPalindrome("abca"));
        assertTrue(validPalindrome("acba"));
    }

    @Test
    public void test0() {
        assertFalse(validPalindrome("abc"));
    }

    @Test
    public void test2() {
        assertFalse(validPalindrome("eeccccbebaeeabebccceea"));
    }

    @Test
    public void test3() {
//        assertTrue(validPalindrome("lcvhmvffcnqxjjxqncffvmhvcul"));

//        assertTrue(validPalindrome("aguokepatgbnvfqmgmlcculmgmqfvnbgtapekouga"));

        assertTrue(validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
    }
}
