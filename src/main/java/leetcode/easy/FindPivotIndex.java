package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
 https://leetcode.com/problems/find-pivot-index/
 Given an array of integers nums, write a method that returns the "pivot" index of this array.

We define the pivot index as the index where the sum of all the numbers to the left of the index is equal to the sum of all the numbers to the right of the index.

If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the left-most pivot index.



Example 1:

Input: nums = [1,7,3,6,5,6]
Output: 3
Explanation:
The sum of the numbers to the left of index 3 (nums[3] = 6) is equal to the sum of numbers to the right of index 3.
Also, 3 is the first index where this occurs.
Example 2:

Input: nums = [1,2,3]
Output: -1
Explanation:
There is no index that satisfies the conditions in the problem statement.


Constraints:

The length of nums will be in the range [0, 10000].
Each element nums[i] will be an integer in the range [-1000, 1000].

 */
public class FindPivotIndex {

  public int pivotIndex(int[] nums) {
    if (nums.length > 2) {
      int sum = 0;
      for (int i : nums) {
        sum += i;
      }
      int leftSum = 0;
      for (int i = 0; i < nums.length; i++) {
        int rightSum = sum - leftSum - nums[i];
        if (leftSum == rightSum) {
          return i;
        }
        leftSum += nums[i];
      }
    }
    return -1;
  }

  @Test
  public void test() {
    Assertions.assertEquals(1, pivotIndex(new int[]{1, 2, 1}));
    Assertions.assertEquals(-1, pivotIndex(new int[]{0, 1}));
    Assertions.assertEquals(2, pivotIndex(new int[]{0, 0, 1}));
    Assertions.assertEquals(3, pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));
    Assertions.assertEquals(-1, pivotIndex(new int[]{1, 2, 3}));
  }

  @Test
  public void negTest() {
    Assertions.assertEquals(0, pivotIndex(new int[]{-1, -1, -1, 0, 1, 1}));
  }
}
