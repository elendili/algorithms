package leetcode.top_interview_questions.easy.array;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.*;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/92/array/674/

Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Note:

Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:

What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

 */
public class IntersectionofTwoArraysII {

    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1==null || nums2==null || nums1.length==0 || nums2.length==0){
            return new int[]{};
        }
        int[] b,s;
        if(nums1.length>nums2.length) {
            b=nums1; // biggest array
            s=nums2; // smallest array
        } else {
            b=nums2;
            s=nums1;
        }
        Map<Integer,Integer> map = new HashMap<>();
        for(int n:s){
            map.compute(n,(k,v)-> v==null?1:v+1);
        }

        int i=0;
        for(int n:b){
            Integer res = map.computeIfPresent(n,(k,v)-> v>0?v-1:null);
            if(res!=null){
                s[i++]=n;
                if(i==s.length){
                    break;
                }
            }
        }
        return Arrays.copyOf(s,i);
    }

    @Test
    public void test(){
        assertEquals(asList(2,2), Arrays.stream(intersect(new int[]{1,2,2,1},new int[]{2,2})).sorted().boxed().collect(toList()));
        assertEquals(asList(4,9), Arrays.stream(intersect(new int[]{4,9,5},  new int[]{9,4,9,8,4})).sorted().boxed().collect(toList()));
    }

}
