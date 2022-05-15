package leetcode.top_interview_questions.easy.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/92/array/567/
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.

 */
public class MoveZeroes {
    public void moveZeroes(int[] a) {
        int z=-1; // zero index
        for(int i=0;i<a.length;i++){
            int v = a[i];
            if(v==0){
                if(z==-1) {
                    z = i;
                }
            } else {
                if(z>-1){
                    a[z++]=v;
                    a[i]=0;
                }
            }
        }
    }
    /*
    i=0: 0,1,0,3,12  z=0
    i=1: 1,0,0,3,12  z=1
    i=2: 1,0,0,3,12  z=1
     */

    @Test
    public void test() {
        int[] actual;
        actual=new int[]{0,1,0,3,12}; moveZeroes(actual);
        assertEquals(asList(1,3,12,0,0), Arrays.stream(actual).boxed().collect(toList()));
        actual=new int[]{0,0,0,0,12}; moveZeroes(actual);
        assertEquals(asList(12,0,0,0,0), Arrays.stream(actual).boxed().collect(toList()));
        actual=new int[]{0,0,-12,0,12}; moveZeroes(actual);
        assertEquals(asList(-12,12,0,0,0), Arrays.stream(actual).boxed().collect(toList()));
    }
}
