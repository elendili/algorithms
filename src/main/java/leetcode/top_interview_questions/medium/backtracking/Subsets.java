package leetcode.top_interview_questions.medium.backtracking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/109/backtracking/796/
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> out = new ArrayList<>();
        final int count = (int)Math.pow(2,nums.length);
        for(int i=0; i<count; i++) {
            // iterate over bits of i
            List<Integer> list = new ArrayList<>();
            for(int b=0;b<=nums.length;b++){
                if(((i>>b)&1) == 1){
                    list.add(nums[b]);
                }
            }
            out.add(list);
        }
        return out;
    }

    @Test
    public void test1(){
        check(asList(emptyList(), asList(1)),
                new int[]{1});
    }

    @Test
    public void test2(){
        check(asList(emptyList(), asList(1),asList(2),asList(1,2) ),
                new int[]{1,2});
    }

    @Test
    public void test3(){
        check(asList(emptyList(),
                asList(1), asList(2), asList(3),
                asList(1,2), asList(1,3), asList(2,3),
                asList(1,2,3)),
                new int[]{1,2,3});
    }

    @Test
    public void test4(){
        check(asList(emptyList(),
                asList(1), asList(2), asList(3), asList(4),
                asList(1,2), asList(1,3), asList(1,4), asList(2,3), asList(2,4), asList(3,4),
                asList(1,2,3), asList(2,3,4), asList(1,3,4), asList(1,2,4),
                asList(1,2,3,4)
                ),
                new int[]{1,2,3,4});
    }

    public void check(List<List<Integer>> expected, int[] input){
        List<List<Integer>> actual = subsets(input);
        List<String> ac = actual.stream().map(l -> l.stream().sorted().collect(Collectors.toList()).toString()).sorted().collect(Collectors.toList());
        List<String> ex = expected.stream().map(l -> l.stream().sorted().collect(Collectors.toList()).toString()).sorted().collect(Collectors.toList());
        Assertions.assertEquals(ex,ac);
    }

}
