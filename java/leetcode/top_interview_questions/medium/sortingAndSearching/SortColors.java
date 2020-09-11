package leetcode.top_interview_questions.medium.sortingAndSearching;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/110/sorting-and-searching/798/
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Follow up:

Could you solve this problem without using the library's sort function?
Could you come up with a one-pass algorithm using only O(1) constant space?


Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Example 2:

Input: nums = [2,0,1]
Output: [0,1,2]
Example 3:

Input: nums = [0]
Output: [0]
Example 4:

Input: nums = [1]
Output: [1]


Constraints:

n == nums.length
1 <= n <= 300
nums[i] is 0, 1, or 2.
 */
public class SortColors {
    // 1 pass solution
    public void sortColors(int[] a) {
        if(a.length>1){
            int lo = 0, mid = 0, hi = a.length - 1;

            while (mid <= hi) {
                switch (a[mid]) {
                    case 0: {
                        a[mid] = a[lo];
                        a[lo] = 0;
                        lo++;
                        mid++;
                        break;
                    }
                    case 1:
                        mid++;
                        break;
                    case 2: {
                        a[mid] = a[hi];
                        a[hi] = 2;
                        hi--;
                        break;
                    }
                }
            }
        }
    }

    @Test
    public void test1(){
        check(new int[]{},new int[]{});
        check(new int[]{0},new int[]{0});
        check(new int[]{1},new int[]{1});
        check(new int[]{2},new int[]{2});
    }

    @Test
    public void test2(){
        check(new int[]{0,2},new int[]{2,0});
        check(new int[]{0,1},new int[]{1,0});
    }

    @Test
    public void test2_12(){
        check(new int[]{1,2},new int[]{2,1});
    }

    @Test
    public void test3(){
        check(new int[]{0,1,2},new int[]{2,0,1});
    }

    @Test
    public void test4() {
        check(new int[]{0, 1, 2, 2}, new int[]{2, 0, 2, 1});
        check(new int[]{0, 0, 1, 2}, new int[]{0, 0, 2, 1});

        check(new int[]{0, 0, 0, 0}, new int[]{0, 0, 0, 0});
        check(new int[]{1, 1, 1, 1}, new int[]{1, 1, 1, 1});
        check(new int[]{2, 2, 2, 2}, new int[]{2, 2, 2, 2});
    }

    @Test
    public void test4_21() {
        check(new int[]{1,1,2,2},new int[]{1,2,1,2});
        check(new int[]{1,1,2,2},new int[]{2,1,2,1});
        check(new int[]{0,1,2,2},new int[]{2,1,2,0});
    }

    @Test
    public void test5(){
        check(new int[]{0,0,1,2,2},new int[]{2,2,0,1,0});
        check(new int[]{0,0,1,2,2},new int[]{2,0,2,1,0});
        check(new int[]{0,0,1,2,2},new int[]{2,1,2,0,0});
        check(new int[]{0,0,1,2,2},new int[]{1,0,2,2,0});
    }

    @Test
    public void test6(){
        check(new int[]{0,0,1,1,2,2},new int[]{2,0,2,1,1,0});
    }

    void check(int[] expected,int[] input){
        sortColors(input);
        Assertions.assertArrayEquals(expected,input);
    }
}
