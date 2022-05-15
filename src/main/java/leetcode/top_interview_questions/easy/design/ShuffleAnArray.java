package leetcode.top_interview_questions.easy.design;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/98/design/670/
Shuffle a set of numbers without duplicates.

The solution expects that we always use the original array
to shuffle() else some of the test cases fail. (Credits; @snehasingh31)

 */
public class ShuffleAnArray {
    class Solution {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        final int[] a;
        final int[] original;

        public Solution(int[] nums) {
            this.original = Arrays.copyOf(nums, nums.length);
            this.a = nums;
        }

        /**
         * Resets the array to its original configuration and return it.
         */
        public int[] reset() {
            System.arraycopy(original, 0, a, 0, a.length);
            return a;
        }

        /**
         * Returns a random shuffling of the array.
         */
        public int[] shuffle() {
            for (int i = 0; i < a.length; i++) {
                int j = random.nextInt(i, a.length);
                swap(a, i, j);
            }
            return a;
        }

        private void swap(int[] ar, int i, int j) {
            int tmp = ar[i];
            ar[i] = ar[j];
            ar[j] = tmp;
        }
    }
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */

}
