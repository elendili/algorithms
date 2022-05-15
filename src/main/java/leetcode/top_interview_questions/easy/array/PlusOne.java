package leetcode.top_interview_questions.easy.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*

https://leetcode.com/explore/featured/card/top-interview-questions-easy/92/array/559/


Given a non-empty array of digits representing a non-negative integer, increment one to the integer.

The digits are stored such that the most significant digit is at the head of the list, and each element in the array contains a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

Example 1:

Input: [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Example 2:

Input: [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
 */
public class PlusOne {

    public int[] plusOne(int[] ds) {
        for (int i=ds.length-1;i>-1;i--){
            int v = ds[i];
            if(v<9){
                ds[i]=v+1;
                return ds;
            } else {
                ds[i]=0;
            }
        }
        int[]out = new int[ds.length+1];
        out[0]=1;
        return out;
    }


    @Test
    public void test(){
        assertEquals(asList(1,2,4), Arrays.stream(plusOne(new int[]{1,2,3})).boxed().collect(toList()));
        assertEquals(asList(1,3,0), Arrays.stream(plusOne(new int[]{1,2,9})).boxed().collect(toList()));
        assertEquals(asList(1,0,0,0), Arrays.stream(plusOne(new int[]{9,9,9})).boxed().collect(toList()));
    }
}
