package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static hackerrank.TestHelper.twoDArrayToString;

public class IntervalListIntersections {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int n1 = firstList.length;
        int n2 = secondList.length;
        int i1 = 0, i2 = 0;
        List<int[]> out = new ArrayList<>();
        while (i1 < n1 && i2 < n2) {
            int[] a1 = firstList[i1];
            int start1 = a1[0];
            int end1 = a1[1];
            int[] a2 = secondList[i2];
            int start2 = a2[0];
            int end2 = a2[1];


            // | |
            //     | |
            if (start2 > end1) {
                i1++;
                continue;
            }
            //     | |
            // | |
            if (start1 > end2) {
                i2++;
                continue;
            }
            //   | |
            // |     |
            if (start1 >= start2 && end2 >= end1) {
                out.add(new int[]{start1, end1});
                i1++;
                continue;
            }
            // |     |
            //   | |
            if (start2 >= start1 && end1 >= end2) {
                out.add(new int[]{start2, end2});
                i2++;
                continue;
            }

            // |   |
            //   |   |
            if (start1 <= start2 && end1 >= start2) {
                out.add(new int[]{start2, end1});
                i1++;
                continue;
            }
            //   |   |
            // |   |
            if (start2 <= start1 && end2 >= start1) {
                out.add(new int[]{start1, end2});
                i2++;
                continue;
            }

        }
        int[][] outA = new int[out.size()][];
        out.toArray(outA);
        return outA;
    }

    @Test
    public void test() {
        Assertions.assertEquals(
                ""
                , twoDArrayToString(intervalIntersection(
                        new int[][]{{1, 2}, {5, 9}},
                        new int[][]{}
                )));
        Assertions.assertEquals(
                ""
                , twoDArrayToString(intervalIntersection(
                        new int[][]{},
                        new int[][]{{1, 2}, {5, 9}}
                )));
        Assertions.assertEquals(
                ""
                , twoDArrayToString(intervalIntersection(
                        new int[][]{{3, 4}},
                        new int[][]{{1, 2}}
                )));
        Assertions.assertEquals(
                ""
                , twoDArrayToString(intervalIntersection(
                        new int[][]{{1, 2}},
                        new int[][]{{3, 4}}
                )));
    }

    @Test
    public void test2() {
        Assertions.assertEquals(
                "[2, 2]\n[5, 5]"
                , twoDArrayToString(intervalIntersection(
                        new int[][]{{1, 2}, {5, 9}},
                        new int[][]{{2, 5}}
                )));
        Assertions.assertEquals(
                "[2, 2]\n[5, 5]"
                , twoDArrayToString(intervalIntersection(
                        new int[][]{{2, 5}},
                        new int[][]{{1, 2}, {5, 9}}
                )));
    }

    @Test
    public void test3() {
        Assertions.assertEquals(
                "[2, 3]"
                , twoDArrayToString(intervalIntersection(
                        new int[][]{{2, 3}},
                        new int[][]{{1, 4}, {5, 9}}
                )));
    }

    @Test
    public void testComplex() {
        Assertions.assertEquals(
                twoDArrayToString(new int[][]{{1, 2}, {5, 5}, {8, 10}, {15, 23}, {24, 24}, {25, 25}})
                , twoDArrayToString(intervalIntersection(
                        new int[][]{{0, 2}, {5, 10}, {13, 23}, {24, 25}},
                        new int[][]{{1, 5}, {8, 12}, {15, 24}, {25, 26}}
                )));
    }
}
