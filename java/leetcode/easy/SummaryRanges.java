package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

/*
https://leetcode.com/problems/summary-ranges
 */
public class SummaryRanges {
    public List<String> summaryRanges(int[] a) {
        if (a == null || a.length == 0) {
            return Collections.emptyList();
        }
        List<String> out = new ArrayList<>();
        int startIndex = -1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            if (startIndex == -1) {
                startIndex = i;
            }
            if (i == a.length - 1 || a[i] + 1 != a[i + 1]) {
                sb.append(a[startIndex]);
                if (a[i] != a[startIndex]) {
                    sb.append("->").append(a[i]);
                }
                out.add(sb.toString());
                startIndex = -1;
                sb.setLength(0);
            }
        }
        return out;
    }

    @Test
    public void test() {
        Assertions.assertEquals(asList("0->2", "4->5", "7"), summaryRanges(new int[]{0, 1, 2, 4, 5, 7}));
        Assertions.assertEquals(asList("0", "2->4", "6", "8->9"), summaryRanges(new int[]{0, 2, 3, 4, 6, 8, 9}));
        Assertions.assertEquals(asList("" + Integer.MIN_VALUE, "" + Integer.MAX_VALUE),
                summaryRanges(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}));
        Assertions.assertEquals(asList(), summaryRanges(new int[]{}));
        Assertions.assertEquals(asList("-1"), summaryRanges(new int[]{-1}));
        Assertions.assertEquals(asList("0"), summaryRanges(new int[]{0}));
    }
}
