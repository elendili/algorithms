package leetcode.easy;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/moving-average-from-data-stream/description/?envType=study-plan-v2&envId=premium-algo-100
 */
public class MovingAverageFromDataStream_346 {
    class MovingAverage {
        final int[] window;
        int count = 0;
        double sum = 0;

        public MovingAverage(int size) {
            this.window = new int[size];
        }

        public double next(int val) {
            sum += val;  // add last value of window
            int divider;
            if (count < window.length) {
                window[count] = val;
                divider = count + 1;
            } else {
                int idx = count % window.length;
                sum -= window[idx]; // subtract first value of window
                window[idx] = val;
                divider = window.length;
            }
            count++;
            return sum / divider;
        }
    }

    @org.junit.jupiter.api.Test
    public void test() {
        MovingAverage movingAverage = new MovingAverage(3);
        assertEquals(1.0, movingAverage.next(1), 0.01);      // return 1.0 = 1 / 1
        assertEquals(5.5, movingAverage.next(10), 0.01);     // return 5.5 = (1 + 10) / 2
        assertEquals(4.66667, movingAverage.next(3), 0.01);  // return 4.66667 = (1 + 10 + 3) / 3
        assertEquals(6.0, movingAverage.next(5), 0.01);      // return 6.0 = (10 + 3 + 5) / 3
    }
}
