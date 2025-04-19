package leetcode.easy;

import java.util.HashMap;
import java.util.Map;

public class PalindromePermutation_266 {
    public boolean canPermutePalindrome2(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.compute(c, (k,v)->v==null?1:v+1);
        }
        // palindrome is when all symbols meet even amount of times and maybe one character which meet once
        int oddCount = 0 ;
        for (Map.Entry<Character, Integer> e:freq.entrySet()){
            int count = e.getValue();
            if (count % 2 ==1 ){ // count is odd
                oddCount++;
                if (oddCount>1) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean canPermutePalindrome(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c-'a'] +=1;
        }
        // palindrome is when all symbols meet even amount of times and maybe one character which meet once
        int oddCount = 0 ;
        for (int count: freq){
            if (count % 2 ==1 ){ // count is odd
                oddCount++;
                if (oddCount>1) {
                    return false;
                }
            }
        }
        return true;
    }
}
