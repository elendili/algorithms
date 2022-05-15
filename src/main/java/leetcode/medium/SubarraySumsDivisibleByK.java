package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/problems/subarray-sums-divisible-by-k/

Solution adopted from
https://www.geeksforgeeks.org/count-sub-arrays-sum-divisible-k/
 */
public class SubarraySumsDivisibleByK {
    public int subarraysDivByK(int[] a, int k) {
        // specific reminders count
        int[] remCount = new int[k];
        // get cumulative sum, take reminder of this cumulative sum
        // and increment correspondent mod[] cell
        int cumSumReminder = 0;
        int out = 0;
        for (int i : a) {
            cumSumReminder += i;
            cumSumReminder %= k; // convert sum to reminder
            if (cumSumReminder < 0) { // tackle negative sum
                cumSumReminder += k;
            }
            // sum can be negative resulting in negative reminder
            // here I learnt that reminder can't be negative and
            // what java gives me is nonsense, so need to shift
            // reminder by dividend and get reminder again.
            // short: add k to negative reminder and get reminder again
//            int modIndex = (cumSum % k + k) % k;
            out += remCount[cumSumReminder]; // add all previous arrays
            remCount[cumSumReminder]++;
        }
        out += remCount[0];
        return out;
    }

    @Test
    public void notdivisible() {
        Assertions.assertEquals(0,
                subarraysDivByK(new int[]{4, 2}, 5));
        Assertions.assertEquals(0,
                subarraysDivByK(new int[]{}, 5));
    }

    @Test
    public void divisible() {
        Assertions.assertEquals(7,
                subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5));
    }

    @Test
    public void fromsite() {
        Assertions.assertEquals(2,
                subarraysDivByK(new int[]{2, -2, 2, -4}, 6));
    }
}
