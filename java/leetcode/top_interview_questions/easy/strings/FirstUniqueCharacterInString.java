package leetcode.top_interview_questions.easy.strings;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/127/strings/881/
Given a string, find the first non-repeating character in it and return its index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode"
return 2.

Note: You may assume the string contains only lowercase English letters.
 */
public class FirstUniqueCharacterInString {
    static final byte alphabetSize = 26;

    public int firstUniqChar(String s) {
        int[] indexes = new int[alphabetSize];
        Arrays.fill(indexes,-1);
        int ii;
        for(int i=0;i<s.length();i++){
            ii = s.charAt(i) - 97;
            if(indexes[ii]>-1){
                // mark as a duplicate
                indexes[ii]=-indexes[ii]-2;
            } else {
                if(indexes[ii]==-1){
                    indexes[ii]=i; // first index stored
                }
            }
        }
        int out=Integer.MAX_VALUE;
        for(int i=0;i<alphabetSize;i++){
            if(indexes[i]>-1){
                out = Math.min(out,indexes[i]);
            }
        }
        return out==Integer.MAX_VALUE?-1:out;
    }

    @Test
    public void test() {
        assertEquals(-1, firstUniqChar("aaadd"));
        assertEquals(0, firstUniqChar("ab"));
        assertEquals(1, firstUniqChar("aba"));
        assertEquals(-1, firstUniqChar("abab"));
        assertEquals(11, firstUniqChar("zposgszposgds"));
    }

}
