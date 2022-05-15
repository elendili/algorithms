package leetcode.top_interview_questions.medium.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/interview/card/top-interview-questions-medium/114/others/824/
Majority Element

Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2

 */
public class MajorityElement {
    // personal note: can e solved via HashMap, but who cares
    // so let's try Booyce-Moore algorithm without extra memory
    public int majorityElement(int[] a) {
        int count=0;
        int out=a[0];
        for(int n:a){
            if(count==0){
                out=n;
            }
            count += (out==n)?1:-1;
        }
        return out;
    }
    @Test
    public void test(){
        Assertions.assertEquals(3, majorityElement(new int[]{1,3,4,1,3,3}));
        Assertions.assertEquals(3, majorityElement(new int[]{1,3,3,3,3,1,1}));
        Assertions.assertEquals(1, majorityElement(new int[]{1,3,4,1,1,3}));
        Assertions.assertEquals(2, majorityElement(new int[]{1,2,2}));
    }
}
