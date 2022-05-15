package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
https://leetcode.com/problems/reverse-vowels-of-a-string/
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:

Input: "hello"
Output: "holle"
Example 2:

Input: "leetcode"
Output: "leotcede"
Note:
The vowels does not include the letter "y".
 */
public class ReverseVowelsOfaString {
    // vowels: aeuio  A, E, I, O, U
    final static HashSet<Character> vowels = new HashSet<>(Arrays.asList('A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u'));

    public String reverseVowels(String s) {
        char[] a = s.toCharArray();
        int l = 0, r = a.length - 1;
        while (l < r) {
            if (vowels.contains(a[l])) {
                while (!vowels.contains(a[r])) {
                    r--;
                }
                char tmp = a[l];
                a[l] = a[r];
                a[r] = tmp;
                r--;
            }
            l++;
        }
        return new String(a);
    }

    @Test
    public void test2() {
        Assertions.assertEquals("loe", reverseVowels("leo"));
    }

    @Test
    public void test3() {
        Assertions.assertEquals("lote", reverseVowels("leto"));
        Assertions.assertEquals("loet", reverseVowels("leot"));
    }

    @Test
    public void test4() {
        Assertions.assertEquals("aueo", reverseVowels("oeua"));
    }

    @Test
    public void test5() {
        Assertions.assertEquals("auo", reverseVowels("oua"));
    }

    @Test
    public void test() {
        assertEquals("oe", reverseVowels("eo"));
        assertEquals("ole", reverseVowels("elo"));
        assertEquals("oel", reverseVowels("eol"));
        assertEquals("holle", reverseVowels("hello"));
    }

    @Test
    public void testfromtask() {
        assertEquals("leotcede", reverseVowels("java/leetcode"));
    }

}
