package leetcode.top_interview_questions.easy.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/882/
Valid Anagram

Solution
Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        int[] freq = new int[26]; // alphabet size
        char c;
        for(int i=0;i<s.length();i++){
            c=s.charAt(i);
            freq[c-97]++;
            c=t.charAt(i);
            freq[c-97]--;
        }
        for(int i:freq){
            if(i!=0){
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        assertTrue(isAnagram("ad","da"));
        assertTrue(isAnagram("qwe","ewq"));
        assertTrue(isAnagram("abc","bca"));
        assertFalse(isAnagram("q","ewq"));
        assertFalse(isAnagram("abc","bcd"));
    }
}
