package leetcode.hard;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/description/
 */
public class MinimumDifficultyOfJobScheduleOfficial {
    // official best solution 

    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d || d<1) {
            return -1;
        }

        // min_diff_curr_day and min_diff_prev_day record the minimum total job difficulty
        // for the current day and previous day, respectively
        int[] minDiffPrevDay = new int[n];
        int[] minDiffCurrDay = new int[n];
        int[] tmp;

        Arrays.fill(minDiffPrevDay, 1000);
        Deque<Integer> stack = new ArrayDeque<>();

        for (int day = 0; day < d; ++day) {
            // Use a monotonically decreasing stack to record job difficulties
            stack.clear();
            // The number of jobs needs to be no less than number of days passed.
            for (int i = day; i < n; i++) {
                // We initialize min_diff_curr_day[i] as only performing 1 job at the i-th index.
                // At day 0, the minimum total job difficulty is to complete the 0th job only.
                // Otherwise, we increment min_diff_prev_day[i - 1] by the i-th job difficulty
                minDiffCurrDay[i] = i > 0 ? minDiffPrevDay[i - 1] + jobDifficulty[i] : jobDifficulty[i];

                // When we find the last element in the stack is smaller than or equal to current job,
                // we need to pop out the element to maintain a monotonic decreasing stack.
                while (!stack.isEmpty() && jobDifficulty[stack.peek()] <= jobDifficulty[i]) {
                    // If we include all jobs with index j+1 to i to the current d,
                    // total job difficulty of the current d will be increased
                    // by the amount of jobDifficulty[i] - jobDifficulty[j]
                    int j = stack.pop();
                    int diffIncr = jobDifficulty[i] - jobDifficulty[j];
                    minDiffCurrDay[i] = Math.min(minDiffCurrDay[i], minDiffCurrDay[j] + diffIncr);
                }

                // When the last element in the stack is larger than current element,
                // if we include all jobs with index j+1 to i to the current d
                // the overall job difficulty will not change
                if (!stack.isEmpty()) {
                    minDiffCurrDay[i] = Math.min(minDiffCurrDay[i], minDiffCurrDay[stack.peek()]);
                }

                // Update the monotonic stack by adding in the current index
                stack.push(i);
            }
            tmp = minDiffPrevDay;
            minDiffPrevDay = minDiffCurrDay;
            minDiffCurrDay = tmp;
        }
        return minDiffPrevDay[n - 1];
    }

    @Test
    public void test1() {
        int[] a = new int[]{1};
        assertEquals(-1, minDifficulty(a, 0));
        assertEquals(1, minDifficulty(a, 1));
        assertEquals(-1, minDifficulty(a, 2));
    }

    @Test
    public void test() {
        int[] a = new int[]{1, 2, 3, 4, 5};
        assertEquals(5, minDifficulty(a, 1));
        assertEquals(6, minDifficulty(a, 2));
        assertEquals(8, minDifficulty(a, 3));
        assertEquals(11, minDifficulty(a, 4));
        assertEquals(15, minDifficulty(a, 5));
        assertEquals(-1, minDifficulty(a, 6));
    }

    @Test
    public void test10() {
        int[] a = new int[]{0, 1, 0, 1, 0};
        assertEquals(1, minDifficulty(a, 1));
        assertEquals(1, minDifficulty(a, 2));
        assertEquals(1, minDifficulty(a, 3));
        assertEquals(2, minDifficulty(a, 4));
        assertEquals(2, minDifficulty(a, 5));
        assertEquals(-1, minDifficulty(a, 6));
    }

    @Test
    public void test321321() {
        int[] a = new int[]{3, 2, 1, 3, 2, 1};
        assertEquals(3, minDifficulty(a, 1));
        assertEquals(4, minDifficulty(a, 2));
        assertEquals(6, minDifficulty(a, 3));
        assertEquals(8, minDifficulty(a, 4));
    }

    @Test
    public void test123123() {
        int[] a = new int[]{1, 2, 3, 1, 2, 3};
        assertEquals(3, minDifficulty(a, 1));
        assertEquals(4, minDifficulty(a, 2));
        assertEquals(6, minDifficulty(a, 3));
        assertEquals(8, minDifficulty(a, 4));
    }

    @Test
    public void test123123123123() {
        int[] a = new int[]{1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3};
        assertEquals(3, minDifficulty(a, 1));
        assertEquals(4, minDifficulty(a, 2));
        assertEquals(6, minDifficulty(a, 3));
        assertEquals(8, minDifficulty(a, 4));
    }
}
