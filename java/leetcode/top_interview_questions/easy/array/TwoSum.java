package leetcode.top_interview_questions.easy.array;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/92/array/546/

Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

 */
public class TwoSum {
    public int[] twoSum(int[] a, int target) {
        Map<Integer,Integer> map = new HashMap<>();//value to index
        for (int i=0;i<a.length;i++) {
            int n = a[i];
            Integer indexOfDiff = map.get(target-n);
            if (indexOfDiff!=null){
                return new int[]{indexOfDiff,i};
            }
            map.put(n,i);
        }
        return new int[]{};
    }

    @Test
    public void test(){
        assertEquals(asList(0,1), Arrays.stream(twoSum(new int[]{2, 7, 11, 15},9)).boxed().collect(Collectors.toList()));
        assertEquals(asList(2,3), Arrays.stream(twoSum(new int[]{2, 7, 11, 15},26)).boxed().collect(Collectors.toList()));
    }
}
