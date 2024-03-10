package leetcode.medium;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/gas-station/description/
 */
public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        // iterate over till switch from negative diff to positive
        int fullTotal = 0;
        int localTotal = 0;
        int out = 0;
        for (int i = 0; i < n; i++) {
            int d = gas[i] - cost[i];
            fullTotal += d;
            localTotal += d;
            if (localTotal < 0) {
                localTotal = 0;
                out = i + 1;
            }
        }
        if (fullTotal < 0) return -1;
        return out;
    }

    @Test
    public void test() {
        assertEquals(3,
                canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
        assertEquals(-1,
                canCompleteCircuit(new int[]{2, 3, 4}, new int[]{3, 4, 3}));
    }

    @Test
    public void test2() {
        assertEquals(-1, canCompleteCircuit(new int[]{1}, new int[]{2}));
        assertEquals(0, canCompleteCircuit(new int[]{1}, new int[]{1}));
        assertEquals(0, canCompleteCircuit(new int[]{1}, new int[]{0}));
    }

    @Test
    public void test3() {
        assertEquals(0, canCompleteCircuit(new int[]{2}, new int[]{1}));
        assertEquals(0, canCompleteCircuit(new int[]{2, 1}, new int[]{1, 2}));
        assertEquals(0, canCompleteCircuit(new int[]{1, 2}, new int[]{1, 2}));
        assertEquals(1, canCompleteCircuit(new int[]{1, 2}, new int[]{2, 1}));
        assertEquals(3, canCompleteCircuit(new int[]{1, 2, 2, 4}, new int[]{3, 3, 3, 0}));
    }

    @Test
    public void test4() {
        assertEquals(3, canCompleteCircuit(new int[]{0, 0, 0, 2}, new int[]{0, 0, 2, 0}));
    }

    @Test
    public void test5() {
        assertEquals(2, canCompleteCircuit(new int[]{0, 0, 0, 2}, new int[]{0, 2, 0, 0}));
        assertEquals(1, canCompleteCircuit(new int[]{0, 0, 0, 2}, new int[]{2, 0, 0, 0}));
    }

    @Test
    public void test6() {
        assertEquals(-1, canCompleteCircuit(new int[]{1, 1, 1, 1}, new int[]{1, 1, 2, 1}));
        assertEquals(0, canCompleteCircuit(new int[]{1, 1, 2, 1}, new int[]{1, 1, 1, 1}));
    }


}
