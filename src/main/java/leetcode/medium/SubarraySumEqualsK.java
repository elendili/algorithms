package leetcode.medium;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/problems/subarray-sum-equals-k/

Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.
Example 1:
Input: nums = [1,1,1], k = 2
Output: 2

Example 2:
Input: nums = [1,2,3], k = 3
Output: 2

Constraints:

1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107
 */
public class SubarraySumEqualsK {

  public int subarraySumSlow(int[] a, int k) {
    if (a == null || a.length < 1) {
      return -1;
    }
    int out = 0;
    // from left
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j <= i; j++) {
        if (i > 0 && j < i) {
          a[j] += a[i];
        }
        if (a[j] == k) {
          out++;
        }
      }
    }
    return out;
  }

  public int subarraySum(int[] a, int k) {
    if (a == null || a.length < 1) {
      return -1;
    }
    int out = 0;
    Map<Integer, Integer> sumFreq = new HashMap<>();
    sumFreq.put(0, 1);
    int sum = 0;
    for (int i : a) {
      sum += i;
      int diff = sum - k;
      out += sumFreq.getOrDefault(diff, 0);
      sumFreq.compute(sum, (z, v) -> v == null ? 1 : v + 1);
    }
    return out;
  }

  /*

  1, 2, 3, -1,-2,-3
  1
  3, 2
  6, 5, 3,
  5, 4, 2, -1

   */
  @Test
  public void test1neg1() {
    Assertions.assertEquals(1, subarraySum(new int[]{1, -1}, 0));
    Assertions.assertEquals(1, subarraySum(new int[]{1, -1}, 1));
    Assertions.assertEquals(0, subarraySum(new int[]{1, -1}, 2));
  }

  @Test
  public void test1() {
    Assertions.assertEquals(2, subarraySum(new int[]{1, 1, 1}, 2));
  }

  @Test
  public void test3_1() {
    Assertions.assertEquals(3, subarraySum(new int[]{1, 1, 1}, 1));
  }

  @Test
  public void test123() {
    Assertions.assertEquals(1, subarraySum(new int[]{1, 2, 3}, 1));
    Assertions.assertEquals(2, subarraySum(new int[]{1, 2, 3}, 3));
    Assertions.assertEquals(0, subarraySum(new int[]{1, 2, 3}, 4));
    Assertions.assertEquals(1, subarraySum(new int[]{1, 2, 3}, 5));
  }

  @Test
  public void test123neg0() {
    Assertions.assertEquals(1, subarraySum(new int[]{1, 2, -3}, 0));
  }

  @Test
  public void test12neg21() {
    Assertions.assertEquals(2, subarraySum(new int[]{1, 2, -2, -1}, 0));
    Assertions.assertEquals(2, subarraySum(new int[]{1, 2, -2, -1}, 1));
    Assertions.assertEquals(1, subarraySum(new int[]{1, 2, -2, -1}, 2));
    Assertions.assertEquals(1, subarraySum(new int[]{1, 2, -2, -1}, 3));
  }

  @Test
  public void test123neg() {
    Assertions.assertEquals(1, subarraySum(new int[]{1, 2, -3}, 3));
    Assertions.assertEquals(1, subarraySum(new int[]{1, 2, -3}, -3));
    Assertions.assertEquals(1, subarraySum(new int[]{1, 2, -3}, -1));
  }

  @Test
  public void test123_2() {
    Assertions.assertEquals(1, subarraySum(new int[]{1, 2, 3}, 2));
  }
}
