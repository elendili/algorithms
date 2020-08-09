package leetcode.top_interview_questions.easy.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/92/array/578/
Given an array of integers, find if the array contains any duplicates.

Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
 */
public class ContainDuplicates {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int n:nums){
            boolean wasItNew = set.add(n);
            if(!wasItNew){
                return true;
            }
        }
        return false;
    }
}
