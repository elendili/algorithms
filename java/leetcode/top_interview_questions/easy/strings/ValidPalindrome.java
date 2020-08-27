package leetcode.top_interview_questions.easy.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/127/strings/883/
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        s = s.trim();
        char[] ca = s.toCharArray();
        if(ca.length<2){
            return true;
        }
        int last = ca.length-1;
        int i=0,j=last;
        char leftC,rightC;
        while(i<=j){
            leftC = Character.toLowerCase(ca[i]);
            if (Character.isLetterOrDigit(leftC)){
                rightC = Character.toLowerCase(ca[j]);
                if (Character.isLetterOrDigit(rightC)){
                    if(leftC!=rightC){
                        return false;
                    }
                    i++;
                    j--;
                } else {
                    j--;
                }
            } else {
                i++;
            }
        }
        return true;
    }

    @Test
    public void test(){
        Assertions.assertEquals(true,isPalindrome(""));
        Assertions.assertEquals(true,isPalindrome(".,"));
        Assertions.assertEquals(true,isPalindrome("a."));
        Assertions.assertEquals(true,isPalindrome("A man, a plan, a canal: Panama"));
        Assertions.assertEquals(true,isPalindrome("AmanaplanacanalPanama"));
        Assertions.assertEquals(true,isPalindrome("A mana plan, a canal: Panama"));
        Assertions.assertEquals(true,isPalindrome("00"));
        Assertions.assertEquals(false,isPalindrome("0P"));
        Assertions.assertEquals(false,isPalindrome("ab"));
        Assertions.assertEquals(false,isPalindrome("race a car"));
    }
}
