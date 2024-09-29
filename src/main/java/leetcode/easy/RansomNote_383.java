package leetcode.easy;

/**
 * https://leetcode.com/problems/ransom-note/?envType=study-plan-v2&envId=top-interview-150
 */
public class RansomNote_383 {

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] freq = new int[26];
        for (char c:magazine.toCharArray()){
            freq[c-'a']+=1;
        }
        for (char c:ransomNote.toCharArray()){
            if(freq[c-'a']<1){
                return false;
            } else {
                freq[c-'a']-=1;
            }
        }
        return true;
    }

}
