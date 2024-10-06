package leetcode.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/ipo/?envType=study-plan-v2&envId=top-interview-150
 */
public class IPO_502 {

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        if (k > n) {
            k = n;
        }
        // project[profit, capital]
        // projects sorted by profit, max profit at the beginning
        PriorityQueue<int[]> profitPQ = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        // projects sorted by capital, min profit at the beginning
        PriorityQueue<int[]> capitalPQ = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        for (int i = 0; i < n; i++) {
            int p = profits[i];
            int c = capital[i];
            int[] project = new int[]{p, c};
            if (c > w) {
                capitalPQ.add(project);
            } else {
                profitPQ.add(project);
            }
        }

        while (k > 0 && !profitPQ.isEmpty()) {
            // execute best project
            w += profitPQ.poll()[0];
            // move affordable projects to queue where projects sorted by decreased profit
            while(!capitalPQ.isEmpty() && capitalPQ.peek()[1]<=w){
                int[] p = capitalPQ.poll();
                profitPQ.add(p);
            }
            k--;
        }

        return w;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals(6, findMaximizedCapital(3, 0, new int[]{1, 2, 3}, new int[]{0, 1, 2}));
        assertEquals(10, findMaximizedCapital(3, 1, new int[]{1, 2, 3, 4}, new int[]{1, 1, 2, 4}));
        assertEquals(10, findMaximizedCapital(3, 1, new int[]{1, 2, 3, 4}, new int[]{1, 1, 2, 2}));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        assertEquals(6, findMaximizedCapital(3, 0, new int[]{1, 2, 3}, new int[]{0, 1, 2}));
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        assertEquals(0, findMaximizedCapital(3, 0, new int[]{1, 2, 3}, new int[]{1, 2, 3}));
    }

    @org.junit.jupiter.api.Test
    public void test4() {
        assertEquals(0, findMaximizedCapital(3, 0, new int[]{}, new int[]{}));
        assertEquals(0, findMaximizedCapital(3, 0, new int[]{0}, new int[]{1}));
        assertEquals(1, findMaximizedCapital(3, 0, new int[]{1}, new int[]{0}));
        assertEquals(0, findMaximizedCapital(3, 0, new int[]{1}, new int[]{1}));
        assertEquals(0, findMaximizedCapital(3, 0, new int[]{2}, new int[]{1}));
        assertEquals(3, findMaximizedCapital(3, 1, new int[]{2}, new int[]{1}));
    }

    @org.junit.jupiter.api.Test
    public void test5() {
        assertEquals(17, findMaximizedCapital(11, 11, new int[]{1, 2, 3}, new int[]{11, 12, 13}));
    }

    @org.junit.jupiter.api.Test
    public void test6() {
        assertEquals(60, findMaximizedCapital(1000, 10, new int[]{10, 10, 10, 10, 10}, new int[]{8, 9, 10, 11, 12}));
    }

}
