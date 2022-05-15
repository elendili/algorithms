package leetcode.top_interview_questions.medium.sortingAndSearching;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/top-k-frequent-elements/
Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
You can return the answer in any order.
 */
public class TopKFrequentElement {


    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private int partition(int[] a, final int left, final int right, final int pivot_index, final Map<Integer, Integer> freqs) {
        int pivot_frequency = freqs.get(a[pivot_index]);
        // 1. move pivot to end
        swap(a, pivot_index, right);
        int store_index = left;
        // 2. move all less frequent elements to the left
        for (int i = left; i <= right; i++) {
            if (freqs.get(a[i]) < pivot_frequency) {
                swap(a, store_index, i);
                store_index++;
            }
        }
        // 3. move pivot to its final place
        swap(a, store_index, right);
        return store_index;
    }

    private void quickselect(int[] unique, int left, int right, int k_smallest, Map<Integer, Integer> freqs) {
        if (left < right) {
            int pivot_index = left + (right - left)/2;
            pivot_index = partition(unique, left, right, pivot_index, freqs);
            if (k_smallest != pivot_index) { // if the pivot is not in its final sorted position
                if (k_smallest < pivot_index) {
                    quickselect(unique, left, pivot_index - 1, k_smallest, freqs); // go left
                } else {
                    quickselect(unique, pivot_index + 1, right, k_smallest, freqs); // go right
                }
            }
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        // build hash map : character and how often it appears
        Map<Integer, Integer> freqs = new HashMap<>();
        for (int num : nums) {
            freqs.compute(num, (_k,v)->v==null?1:v+1);
        }
        int[] a = freqs.keySet().stream().mapToInt(i->i).toArray();
        quickselect(a, 0, a.length - 1, a.length - k, freqs);
        return Arrays.copyOfRange(a, a.length - k, a.length);
    }

    @Test
    public void test() {
        Assertions.assertArrayEquals(new int[]{1}, topKFrequent(new int[]{1},1));
        Assertions.assertArrayEquals(new int[]{1}, topKFrequent(new int[]{1,1,1,2,2,3},1));
        Assertions.assertArrayEquals(new int[]{3,1}, topKFrequent(new int[]{1,2,3},2));
        Assertions.assertArrayEquals(new int[]{2,1}, topKFrequent(new int[]{1,1,1,2,2,3},2));
        Assertions.assertArrayEquals(new int[]{3,2,1}, topKFrequent(new int[]{1,1,1,2,2,3},3));
        Assertions.assertArrayEquals(new int[]{3,1}, topKFrequent(new int[]{1,1,2,2,3,3},2));
    }
}
