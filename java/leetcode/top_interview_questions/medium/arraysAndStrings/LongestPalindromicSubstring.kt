package leetcode.top_interview_questions.medium.arraysAndStrings

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*

https://leetcode.com/problems/longest-palindromic-substring/

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
 */
class LongestPalindromicSubstring {

    fun longestPalindrome(s: String): String {
        if(s==null || s.length<2){
            return s
        }
        var start=0
        var end=0;
        for (i in 0 until s.length-1){
            // check around cell
            var li=i-1
            var ri=i+1
            while (li>-1 && ri<s.length && s[li]==s[ri]){
                if(ri-li>end-start){
                    start=li
                    end=ri
                }
                li--
                ri++
            }
            li=i
            ri=i+1
            while (li>-1 && ri<s.length && s[li]==s[ri]){
                if(ri-li>end-start){
                    start=li
                    end=ri
                }
                li--
                ri++
            }

        }
        return s.substring(start,end+1)
    }

    @Test
    fun test() {
        check(listOf(""), "")
        check(listOf("t"), "t")
        check(listOf("aba","bab"), "babad")
        check(listOf("bb"), "cbbd")
        check(listOf("bbb"), "bbb")
        check(listOf("bbbb"), "bbbb")
        check(listOf("aca"), "cbbdaca")
        check(listOf("3234323"), "1323432321")
        check(listOf("1221"), "12216")
        check(listOf("1221"), "21221")
    }

    fun check(expected:Collection<String>,input:String){
        val actual = longestPalindrome(input)
        println(actual)
        Assertions.assertTrue(expected.contains(actual));
    }
}