package leetcode.top_interview_questions.medium.arraysAndStrings

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LongestSubstringWithoutRepeatingCharacters {

    fun lengthOfLongestSubstring(s: String): Int {
        if(s.isNullOrEmpty()) {return 0}
        var max = 0
        var begin = 0
        val map: HashMap<Char, Int> = HashMap()
        s.forEachIndexed { curIndex, c ->
            map.compute(c) { _, charIndex ->
                if (charIndex != null) {
                    begin = Math.max(begin,charIndex+1)
                }
                max = Math.max(max,curIndex-begin+1)
                curIndex
            }
        }
        return max
    }

    @Test
    fun test() {
        Assertions.assertEquals(2, lengthOfLongestSubstring("12"))
        Assertions.assertEquals(0, lengthOfLongestSubstring(""))
        Assertions.assertEquals(1, lengthOfLongestSubstring("bb"))
        Assertions.assertEquals(1, lengthOfLongestSubstring("bbb"))
        Assertions.assertEquals(1, lengthOfLongestSubstring("bbbb"))
        Assertions.assertEquals(1, lengthOfLongestSubstring("bbbbb"))
        Assertions.assertEquals(3, lengthOfLongestSubstring("abcabcbb"))
        Assertions.assertEquals(3, lengthOfLongestSubstring("pwwkew"))

        Assertions.assertEquals(10, lengthOfLongestSubstring("1234567890"))
        Assertions.assertEquals(4, lengthOfLongestSubstring("12341234"))

        Assertions.assertEquals(2, lengthOfLongestSubstring("abba"))
        Assertions.assertEquals(2, lengthOfLongestSubstring("abab"))
        Assertions.assertEquals(2, lengthOfLongestSubstring("ababa"))
        Assertions.assertEquals(2, lengthOfLongestSubstring("ababab"))
        Assertions.assertEquals(2, lengthOfLongestSubstring("aba"))
        Assertions.assertEquals(2, lengthOfLongestSubstring("aab"))
        Assertions.assertEquals(2, lengthOfLongestSubstring("baa"))

        Assertions.assertEquals(3, lengthOfLongestSubstring("abccba"))
        Assertions.assertEquals(3, lengthOfLongestSubstring("abcccba"))
        Assertions.assertEquals(2, lengthOfLongestSubstring("abbcccbba"))

        Assertions.assertEquals(3, lengthOfLongestSubstring("123212321"))
        Assertions.assertEquals(4, lengthOfLongestSubstring("1234321234321"))
    }
}