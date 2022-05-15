package leetcode.top_interview_questions.easy.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/127/strings/879/

Write a function that reverses a string. The input string is given as an array of characters char[].

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

You may assume all the characters consist of printable ascii characters.

Example 1:

Input: ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
Example 2:

Input: ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]


 */
public class ReverseString {
    public void reverseString(char[] s) {
        int n = s.length;
        char tmp;
        int z;
        for(int i=0;i<n/2;i++){
            z=n-i-1;
            tmp = s[i];
            s[i]=s[z];
            s[z]=tmp;
        }
    }
    @Test
    public void testInValid() {
        char[] actual=new char[]{'1','a'};reverseString(actual);
        assertArrayEquals(new char[]{'a','1'}, actual);
        actual=new char[]{'1','a','b'};reverseString(actual);
        assertArrayEquals(new char[]{'b','a','1'}, actual);
    }
}
