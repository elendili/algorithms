package leetcode.top_interview_questions.medium.arraysAndStrings

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*
import java.util.stream.Collectors

/*
https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/778/
Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

Example 1:
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Example 2:
Input: strs = [""]
Output: [[""]]

Example 3:
Input: strs = ["a"]
Output: [["a"]]

Constraints:
1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lower-case English letters.

 */

class GroupAnagrams {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val freqs = HashMap<Int, ArrayList<String>>()
        // iterate strs get frequencies in array
        // search similar freq
        val freq = IntArray(26)
        strs.forEach { s->
            s.forEach { c->
                freq[c-'a']+=1
            }
            freqs.compute(freq.contentHashCode()) { _, list ->
                val listOut: ArrayList<String> = list ?: ArrayList()
                listOut.add(s)
                listOut
            }
            Arrays.fill(freq,0)
        }
        return freqs.values.toList()
    }

    @Test
    fun test() {
        check("ate,eat,tea\nbat\nnat,tan", arrayOf("eat","tea","tan","ate","nat","bat"));
        check("\nae,ea,ea\nbater\nnat,tan", arrayOf("ea","ea","","ae","nat","bater","tan"));
        check(",", arrayOf("",""));
        check("z", arrayOf("z"));
    }

    fun check(expected: String?, a: Array<String>) {
        val aa = groupAnagrams(a)
        val actual:String = aa.stream().map { ss->ss.sorted().joinToString(",") }.sorted().collect(Collectors.joining("\n"))
        assertEquals(expected, actual)
    }
}