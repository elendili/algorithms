package leetcode.medium;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
* https://leetcode.com/problems/reverse-words-in-a-string-ii/description/?envType=study-plan-v2&envId=premium-algo-100
 */
public class ReverseWordsInAStringII {
    public void reverseWords(char[] s) {
        reverse(s,0,s.length-1);
        int start=0, end=0;
        while(end<s.length){
            while(end<s.length && s[end]!=' ') {
                end++;
            }
            reverse(s,start,end-1);
            start = end+1;
            end+=1;
        }
    }

    void reverse(char[] a, int left, int right) {
        while(left<right){
            char t = a[left];
            a[left] = a[right];
            a[right] = t;
            left++; right--;
        }
    }

    @org.junit.jupiter.api.Test
    public void test1(){
        char[] ar = new char[]{'t', 'h', 'e', ' ', 's', 'k', 'y', ' ', 'i', 's', ' ', 'b', 'l', 'u', 'e'};
        String expected = Arrays.toString(new char[]{'b','l','u','e',' ','i','s',' ','s','k','y',' ','t','h','e'});
        reverseWords(ar);
        assertEquals(expected, Arrays.toString(ar));
    }
    
    @org.junit.jupiter.api.Test
    public void test2(){
        char[] ar = new char[]{'a'};
        String expected = Arrays.toString(new char[]{'a'});
        reverseWords(ar);
        assertEquals(expected, Arrays.toString(ar));
    }
}
