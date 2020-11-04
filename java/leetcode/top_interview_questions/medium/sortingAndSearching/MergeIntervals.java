package leetcode.top_interview_questions.medium.sortingAndSearching;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/110/sorting-and-searching/803/
Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

Constraints:

intervals[i][0] <= intervals[i][1]
 */
public class MergeIntervals {
    public int[][] merge(int[][] a) {
        if (a == null || a.length < 2) {
            return a;
        }
        // sort
        Arrays.sort(a, Comparator.comparingInt(e -> e[0])); // use first element to compare
        //work with sorted
        int prevI = 0; //previous element index
        for (int i=1;i<a.length;i++) {
            int[] c = a[i];
            if(c[0]<=a[prevI][1]) {
                //merge
                a[prevI][1] = Math.max(c[1], a[prevI][1]); // expand previous element border to include current
            } else {
                a[++prevI]=c;
            }
        }
        return Arrays.copyOfRange(a,0,prevI+1);
    }

    @Test
    public void test() {
        Assertions.assertArrayEquals(new int[][]{{1,4},{5,5}}, merge(new int[][]{{1, 4}, {5, 5}}));
        Assertions.assertArrayEquals(new int[][]{{1,5}}, merge(new int[][]{{1, 4}, {4, 5}}));
        Assertions.assertArrayEquals(new int[][]{{1,6},{8,10},{15,18}}, merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}}));
    }

}
