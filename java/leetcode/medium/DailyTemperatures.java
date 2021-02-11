package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

// https://leetcode.com/problems/daily-temperatures/
public class DailyTemperatures {

    public static List<Integer> dailyTemperatures(List<Integer> temps) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[temps.size()];

        for (int i = 0; i < temps.size(); i++) {
            while (!stack.isEmpty() && temps.get(i) > temps.get(stack.peek())) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }

        return Arrays.stream(result).boxed().collect(Collectors.toList());
    }

    @Test
    public void test() {
        Assertions.assertEquals(asList(1, 1, 4, 2, 1, 1, 0, 0),
                dailyTemperatures(asList(73, 74, 75, 71, 69, 72, 76, 73)));
    }
}
