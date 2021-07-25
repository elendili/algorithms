package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

// https://leetcode.com/problems/palindrome-number/
public class PalindromeNumber {

    public boolean isPalindrome(int x) {
        String s = Integer.toString(x);
        int n = s.length();
        boolean out = true;
        for (int i = 0; i < n / 2; i++) {
            out &= s.charAt(i) == s.charAt(n - i - 1);
        }
        return out;
    }


    public boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }
        int n = (int) Math.log10(x) + 1;
        for (int i = 0; i < n / 2; i++) {
            int left = getDigit(x, n - i - 1);
            int right = getDigit(x, i);
            if (right != left) {
                return false;
            }
        }
        return true;
    }

    int getDigit(int x, int i) {
        int big = (int) Math.pow(10, i + 1);
        int small = (int) Math.pow(10, i);
        return (x % big) / small;
    }

    public boolean isPalindrome3(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber / 10;
    }

    @Test
    public void test() {
        check(this::isPalindrome);
    }

    @Test
    public void test2() {
        check(this::isPalindrome2);
    }

    @Test
    public void test3() {
        check(this::isPalindrome3);
    }

    void check(Predicate<Integer> predicate) {
        Assertions.assertTrue(predicate.test(121));
        Assertions.assertTrue(predicate.test(101));
        Assertions.assertTrue(predicate.test(99));

        Assertions.assertFalse(predicate.test(10));
        Assertions.assertFalse(predicate.test(100));
        Assertions.assertFalse(predicate.test(12));
        Assertions.assertFalse(predicate.test(-101));
        Assertions.assertFalse(predicate.test(-12));
        Assertions.assertFalse(predicate.test(123));
    }
}
