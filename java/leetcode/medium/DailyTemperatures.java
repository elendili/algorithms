package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import static java.util.Arrays.asList;

// https://leetcode.com/problems/daily-temperatures/
public class DailyTemperatures {

    public int[] dailyTemperatures(int[] T) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] out = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            int v = T[i];
            while (!stack.isEmpty() && T[stack.peek()] < v) {
                int indexOfColderTemperature = stack.pop();
                out[indexOfColderTemperature] = i - indexOfColderTemperature; //set days count
            }
            stack.push(i);
        }
        return out;
    }

    @Test
    public void test() {
        Assertions.assertEquals(asList(0, 0, 0).toString(),
                Arrays.toString(dailyTemperatures(new int[]{1, 1, 1})));
        Assertions.assertEquals(asList(0, 0, 0).toString(),
                Arrays.toString(dailyTemperatures(new int[]{3, 2, 1})));
        Assertions.assertEquals(asList(1, 1, 0).toString(),
                Arrays.toString(dailyTemperatures(new int[]{1, 2, 3})));
        Assertions.assertEquals(asList(1, 1, 4, 2, 1, 1, 0, 0).toString(),
                Arrays.toString(dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
    }
}
