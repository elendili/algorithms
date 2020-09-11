package leetcode.top_interview_questions.medium.backtracking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/109/backtracking/795/
Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> out = new ArrayList<>();
        recurse(nums,0,out);
        return out;
    }

    void recurse(int[] nums, int currentIndex, List<List<Integer>> out){
        if(currentIndex>=nums.length){
            out.add(arrayToList(nums));
        } else {
            for(int i=currentIndex; i<nums.length;i++){
                swap(nums,currentIndex,i);
                recurse(nums, currentIndex+1, out);
                swap(nums,i,currentIndex);
            }
        }
    }
    void swap(int[] a, int i, int j){
        int tmp = a[i];
        a[i]=a[j];
        a[j]=tmp;
    }

    private List<Integer> arrayToList(int[] nums){
        List<Integer> result = new ArrayList<>(nums.length);
        for(int n: nums){
            result.add(n);
        }
        return result;
    }

    @Test
    public void test(){
        Assertions.assertEquals( Collections.singletonList(Collections.emptyList()).toString(), permute(new int[]{}).toString());
        List<List<Integer>> exp = Arrays.asList(
                Arrays.asList(1,2),
                Arrays.asList(2,1)
        );
        Assertions.assertEquals( exp, permute(new int[]{1,2}));
        exp = Arrays.asList(
                Arrays.asList(1,2,3),
                Arrays.asList(1,3,2),
                Arrays.asList(2,1,3),
                Arrays.asList(2,3,1),
                Arrays.asList(3,1,2),
                Arrays.asList(3,2,1)
        );
        Assertions.assertEquals( exp.stream().map(String::valueOf).sorted().collect(Collectors.toList()),
                permute(new int[]{1,2,3}).stream().map(String::valueOf).sorted().collect(Collectors.toList()));
    }
}
