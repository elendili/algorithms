package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/problems/reverse-only-letters/
Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place, and all letters reverse their positions.

Example 1:
Input: "ab-cd"
Output: "dc-ba"

Example 2:
Input: "a-bC-dEf-ghIj"
Output: "j-Ih-gfE-dCba"

Example 3:
Input: "Test1ng-Leet=code-Q!"
Output: "Qedo1ct-eeLg=ntse-T!"

Note:
S.length <= 100
33 <= S[i].ASCIIcode <= 122
S doesn't contain \ or "
 */
public class ReverseOnlyLetters {
    private static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public String reverseOnlyLetters(String S) {
        char[] a = S.toCharArray();
        int l = 0, r = a.length - 1;
        while (l < r) {
            while (l < r && !isLetter(a[l])) {
                l++;
            }
            while (l < r && !isLetter(a[r])) {
                r--;
            }
            if (l < r) {
                char tmp = a[l];
                a[l++] = a[r];
                a[r--] = tmp;
            }
        }
        return new String(a);
    }

    @Test
    public void test() {
        Assertions.assertEquals("dc-ba", reverseOnlyLetters("ab-cd"));
    }

    @Test
    public void test2() {
        Assertions.assertEquals("j-Ih-gfE-dCba", reverseOnlyLetters("a-bC-dEf-ghIj"));
    }

    @Test
    public void test3() {
        Assertions.assertEquals("Qedo1ct-eeLg=ntse-T!", reverseOnlyLetters("Test1ng-Leet=code-Q!"));
    }

    @Test
    public void test4() {
        Assertions.assertEquals("1234!@a#$", reverseOnlyLetters("1234!@a#$"));
    }

    @Test
    public void test5() {
        Assertions.assertEquals("b1234!@a#$", reverseOnlyLetters("a1234!@b#$"));
    }

    @Test
    public void test6() {
        Assertions.assertEquals("azAZ", reverseOnlyLetters("ZAza"));
        Assertions.assertEquals("az9AZ", reverseOnlyLetters("ZA9za"));
    }

    @Test
    public void test7() {
        Assertions.assertEquals("1234", reverseOnlyLetters("1234"));
    }
}
