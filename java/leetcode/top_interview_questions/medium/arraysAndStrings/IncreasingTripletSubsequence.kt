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
class IncreasingTripletSubsequence {

    fun increasingTriplet(a: IntArray): Boolean {
        if (a.size < 3) {
            return false
        }
        var first = Integer.MAX_VALUE
        var second = Integer.MAX_VALUE
        for (n in a) {
            if (n <= first) {
                first = n
            } else if (n <= second) {
                second = n
            } else {
                return true // first < second < n. OMG, we found third value!
            }
        }
        return false;
    }

    @Test
    fun test() {
        Assertions.assertTrue(increasingTriplet(intArrayOf(1, 2, 1, 3)))
        Assertions.assertTrue(increasingTriplet(intArrayOf(1, 9, 2, 3, -9)))
        Assertions.assertTrue(increasingTriplet(intArrayOf(9, 1, 2, 3)))
        Assertions.assertFalse(increasingTriplet(intArrayOf(1)))
        Assertions.assertFalse(increasingTriplet(intArrayOf()))
        Assertions.assertFalse(increasingTriplet(intArrayOf(3,2,1)))
        Assertions.assertFalse(increasingTriplet(intArrayOf(3, 4, 3, 2, 1)))
        Assertions.assertFalse(increasingTriplet(intArrayOf(5,4,3,2)))
        Assertions.assertFalse(increasingTriplet(intArrayOf(1,2,1,2,1)))
        Assertions.assertFalse(increasingTriplet(intArrayOf(1,1,1,1,1)))
        Assertions.assertTrue(increasingTriplet(intArrayOf(1, 2, 1, 3)))
        Assertions.assertTrue(increasingTriplet(intArrayOf(4, 1, 4, 2, 3)))
    }
}