package leetcode.easy;

import java.util.Arrays;
import java.util.Comparator;

import static helpers.TestHelper.twoDArrayToString;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/high-five/description/?envType=study-plan-v2&envId=premium-algo-100
 */
public class HighFive_1086 {
    public int[][] highFive(int[][] items) {
        if (items.length <= 1) {
            return items;
        }

        Comparator<int[]> c = (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        };
        Arrays.sort(items, c);

        int prevId = 0;
        int counter = 1;
        for (int i = 1; i < items.length; i++) {
            int[] cur = items[i];
            if (items[prevId][0] == cur[0]) {
                counter += 1;
                if (counter > 5) {
                    continue;
                }
                items[prevId][1] += cur[1];
            } else {
                items[prevId][1] = items[prevId][1]/5;
                prevId++;
                items[prevId] = cur;
                counter=1;
            }
        }
        items[prevId][1] = items[prevId][1]/5;
        int[][] out = Arrays.copyOf(items, prevId+1);
        return out;
    }

    @org.junit.jupiter.api.Test
    public void test(){
        assertEquals(twoDArrayToString(new int[][]{{1,87}}),
                twoDArrayToString(highFive(new int[][] {{1,91},{1,92},{1,60},{1,65},{1,87},{1,100}})));
        
        assertEquals(twoDArrayToString(new int[][]{{1,87},{2,88}}),
                twoDArrayToString(highFive(new int[][] {{1,91},{1,92},{2,93},{2,97},{1,60},{2,77},{1,65},{1,87},{1,100},{2,100},{2,76}})));

        assertEquals(twoDArrayToString(new int[][] {{1,100},{7,100}} ),
                twoDArrayToString(highFive(new int[][] {{1,100},{7,100},{1,100},{7,100},{1,100},{7,100},{1,100},{7,100},{1,100},{7,100}} )));
    }
}
