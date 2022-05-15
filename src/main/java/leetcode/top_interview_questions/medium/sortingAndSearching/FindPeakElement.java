package leetcode.top_interview_questions.medium.sortingAndSearching;
/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/110/sorting-and-searching/801/
A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:
Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.

Example 2:
Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5
Explanation: Your function can return either index number 1 where the peak element is 2,
             or index number 5 where the peak element is 6.
Follow up: Your solution should be in logarithmic complexity.
 */

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

public class FindPeakElement {
    public int findPeakElement(int[] a) {
        int l=0,h=a.length-1;
        while(l<h){
            int m = l+(h-l)/2;
            if(a[m]>a[m+1]){
                h=m;
            } else{
                l=m+1;
            }
        }
        return l;
    }

    @Test
    public void test(){
        MatcherAssert.assertThat(findPeakElement(new int[]{1,2,3,1}), is(2));
        MatcherAssert.assertThat(findPeakElement(new int[]{1,2,1,3,5,6,4}), Matchers.either(is(5)).or(is(1)));
    }
}
