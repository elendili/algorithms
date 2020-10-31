package leetcode.top_interview_questions.hard.arrayAndStrings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/explore/interview/card/top-interview-questions-hard/116/array-and-strings/829/
Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.

To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500.
All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.

Example:

Input:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

Output:
2

Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
public class _4SumII {
    /*
    1,-1 vs -(5,-5)
    1,5  vs -(-5,-1)
    1,3  vs -2,-2 => 0
    1,-2  vs 3,-2 => 0
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if (A.length < 1 || B.length < 1 || C.length < 1 || D.length < 1) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>(A.length * A.length * 2);
        for (int a : A) {
            for (int b : B) {
                map.compute(a + b, (k, v) -> v == null ? 1 : v + 1);
            }
        }
        int out = 0;
        for (int c : C) {
            for (int d : D) {
                out += map.getOrDefault(-(c + d), 0);
            }
        }
        return out;
    }

    @Test
    public void test() {
        Assertions.assertEquals(1, fourSumCount(new int[]{1}, new int[]{-5}, new int[]{5}, new int[]{-1}));
        Assertions.assertEquals(1, fourSumCount(new int[]{-5}, new int[]{5}, new int[]{1}, new int[]{-1}));
        Assertions.assertEquals(1, fourSumCount(new int[]{1, -2}, new int[]{3, 4}, new int[]{-1, 9}, new int[]{-1, -8}));
        Assertions.assertEquals(2, fourSumCount(new int[]{1, 2}, new int[]{-2, -1}, new int[]{-1, 2}, new int[]{0, 2}));
    }
}
