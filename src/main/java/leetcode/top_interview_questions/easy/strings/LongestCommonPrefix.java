package leetcode.top_interview_questions.easy.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/127/strings/887/
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
Note:

All given inputs are in lowercase letters a-z.

 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0){
            return "";
        }
        if(strs.length==1){
            return strs[0];
        }

        StringBuilder out=new StringBuilder();
        char c;
        label:for (int i=0;i<strs[0].length();i++){
            c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() <= i || strs[j].charAt(i) != c) {
                    break label;
                }
            }
            out.append(c);
        }
        return out.toString();
    }
    @Test
    public void test(){
        Assertions.assertEquals("fl",longestCommonPrefix(new String[]{"flower","flow","flight"}));
        Assertions.assertEquals("",longestCommonPrefix(new String[]{"flower","x","flight"}));
        Assertions.assertEquals("ba",longestCommonPrefix(new String[]{"ba","ba","ba"}));
        Assertions.assertEquals("",longestCommonPrefix(new String[]{"","ba","ba"}));
        Assertions.assertEquals("",longestCommonPrefix(new String[]{"ba","ba",""}));
        Assertions.assertEquals("ba",longestCommonPrefix(new String[]{"baaaaaaaa","baaaaa","ba"}));
    }
}
