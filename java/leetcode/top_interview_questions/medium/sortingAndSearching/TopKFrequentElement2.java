package leetcode.top_interview_questions.medium.sortingAndSearching;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/top-k-frequent-elements/
https://leetcode.com/explore/featured/card/top-interview-questions-medium/110/sorting-and-searching/799/

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
public class TopKFrequentElement2 {

    private int hash(int key, int buckets) {
        return (key & 0x7fffffff) % buckets;
    }

    public int[] topKFrequent(int[] nums, int k) {
        if(nums==null || nums.length==0){
            return new int[]{};
        }

        int n = nums.length;
        int[][] freqs = new int[n][];
        int count=0;
        for (int num : nums) {
            int hash = hash(num, n);
            while (freqs[hash]!=null && freqs[hash][0] != num) {
                hash++;
            }
            if(freqs[hash]==null){
                count++;
                freqs[hash]=new int[]{num,0};
            }
            freqs[hash][1]++;
        }

        k = Math.min(count,k);

        // use kind of bucket sort
        List<Integer>[] sorted = (List<Integer>[]) new List[n+1];
        for(int i=0;i<n;i++){
            if(freqs[i]!=null){
                int f = freqs[i][1];
                if (sorted[f]==null){
                    sorted[f] = new ArrayList<>();
                }
                sorted[f].add(freqs[i][0]);
            }
        }

        // fill out array
        int[]out=new int[k];
        for(int ki=0, i=n; i>-1 && ki<k; i--){
            if(sorted[i]!=null) {
                List<Integer> v = sorted[i];
                for(int z:v){
                    out[ki++]=z;
                    if(ki>=k){
                        return out;
                    }
                }
            }
        }
        return out;
    }

    @Test
    public void test() {
        Assertions.assertEquals("[]", Arrays.toString(topKFrequent(new int[]{},1)));
        Assertions.assertEquals("[1]", Arrays.toString(topKFrequent(new int[]{1},1)));
        Assertions.assertEquals("[0]", Arrays.toString(topKFrequent(new int[]{0},1)));
        Assertions.assertEquals("[1]", Arrays.toString(topKFrequent(new int[]{1},2)));
        Assertions.assertEquals("[1]", Arrays.toString(topKFrequent(new int[]{1,1,1,2,2,3},1)));
        Assertions.assertEquals("[3, 1]", Arrays.toString(topKFrequent(new int[]{1,2,3},2)));
        Assertions.assertEquals("[1, 2]", Arrays.toString(topKFrequent(new int[]{1,1,1,2,2,3},2)));
        Assertions.assertEquals("[1, 2, 3]", Arrays.toString(topKFrequent(new int[]{1,1,1,2,2,3},3)));
        Assertions.assertEquals("[1, 2]", Arrays.toString(topKFrequent(new int[]{1,1,2,2,3,3},2)));
        Assertions.assertEquals("[3, 0]", Arrays.toString(topKFrequent(new int[]{0,0,1,2,3,3,3},2)));
    }

}
