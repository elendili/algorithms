package leetcode.top_interview_questions.medium.sortingAndSearching;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/110/sorting-and-searching/800/

Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.

 */
public class KthLargestElementInArray {
    public int findKthLargest(int[] nums, int k) {
        //note nums.length-k since kth largest. not kth smallest
        sortFoKthLargest(nums, 0, nums.length-1, nums.length-k);
        return nums[nums.length - k];
    }

    private void sortFoKthLargest(int[] nums, int start, int end, int k){
        if (start <= end) {
            int left = start;
            int right = end;
            int mid = (left + right)/2;
            int pivot = nums[mid];
            while(left <= right){
                while(nums[left] < pivot){ //*
                    left++;
                }
                while(nums[right] > pivot){ //**
                    right--;
                }
                if(left <= right){
                    swap(nums, left++, right--);
                }
            }
            //if k in left half, sort only left half
            if (start <= k && k <= right ) {
                sortFoKthLargest(nums, start, right, k);
                //else sort right half
            } else if (left <= k && k <= end) {
                sortFoKthLargest(nums, left, end, k);
            }
        }
    }

    void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    @Test
    public void shortTest() {
        Assertions.assertEquals(1, findKthLargest(new int[]{1}, 1));
        Assertions.assertEquals(4, findKthLargest(new int[]{4, 2}, 1));
        Assertions.assertEquals(2, findKthLargest(new int[]{4, 2}, 2));
    }

    @Test
    public void test() {
        Assertions.assertEquals(5, findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        Assertions.assertEquals(4, findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }

    @Test
    public void descSort() {
        Assertions.assertEquals(7, findKthLargest(new int[]{7, 6, 5, 4, 3, 2, 1}, 1));
        Assertions.assertEquals(1, findKthLargest(new int[]{7, 6, 5, 4, 3, 2, 1}, 7));
    }

    @Test
    public void ascSort() {
        Assertions.assertEquals(7, findKthLargest(new int[]{1, 2, 3, 4, 5, 6, 7}, 1));
        Assertions.assertEquals(6, findKthLargest(new int[]{1, 2, 3, 4, 5, 6, 7}, 2));
        Assertions.assertEquals(4, findKthLargest(new int[]{1, 2, 3, 4, 5, 6, 7}, 4));
        Assertions.assertEquals(1, findKthLargest(new int[]{1, 2, 3, 4, 5, 6, 7}, 7));
    }

}
