package leetcode.top_interview_questions.easy.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/92/array/727/
Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 */
public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] a) {
        int out=a.length;
        if (out < 2) {
            return out;
        }
        // si - search index
        // ii - insert index
        int ii=1;
        for (int si = 1; si < a.length; si++) {
            if (a[si] == a[si - 1]) {
                out--;
            } else {
                // condition to swap?
                if(ii!=si){
                    a[ii]=a[si];
                }
                ii++;
            }
        }
        return out;
    }
    @Test
    public void test(){
        int[] a;
        a = new int[]{2};
        assertEquals(1,removeDuplicates(a));
        assertEquals(asList(2).toString(), Arrays.toString(a));

        a = new int[]{1,2};
        assertEquals(2,removeDuplicates(a));
        assertEquals(asList(1,2).toString(), Arrays.toString(a));

        a = new int[]{1,1,2};
        assertEquals(2,removeDuplicates(a));
        assertEquals(asList(1,2,2).toString(), Arrays.toString(a));

        a = new int[]{0,0,1,1,1,2,2,3,3,4};
        assertEquals(5, removeDuplicates(a));
        assertEquals(asList(0,1,2,3,4,2,2,3,3,4).toString(),
                Arrays.toString(a));

        a = new int[]{9,9,9,9,9,9};
        assertEquals(1, removeDuplicates(a));
        assertEquals(asList(9,9,9,9,9,9).toString(),
                Arrays.toString(a));
    }
}
