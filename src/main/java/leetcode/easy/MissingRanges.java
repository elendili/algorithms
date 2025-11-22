package leetcode.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/missing-ranges/?envType=study-plan-v2&envId=premium-algo-100
 */
public class MissingRanges {
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        if (nums.length == 0 || lower > nums[nums.length - 1] || upper < nums[0]) {
            return List.of(List.of(lower, upper));
        }
        int first = nums[0];
        List<List<Integer>> out = new ArrayList<>();
        if (lower < first) {
            out.add(List.of(lower, first - 1));
        }
        int prev = first;
        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i];
            if (cur > upper) {
                break;
            }
            if (cur == prev + 1) {
                // consecutive
                prev = cur;
            } else {
                out.add(List.of(prev + 1, cur - 1));
                prev = cur;
            }
        }
        int last = nums[nums.length - 1];
        if (last < upper) {
            out.add(List.of(last + 1, upper));
        }
        return out;
    }

    public static List<Arguments> samplesProvider() {
        return List.of(
                Arguments.of("[[1,1]]", new int[]{2}, 1, 2),
                Arguments.of("[[2,2]]", new int[]{1}, 1, 2),
                Arguments.of("[[-2,-2]]", new int[]{-1}, -2, -1),
                Arguments.of("[[1,1]]", new int[]{}, 1, 1),
                Arguments.of("[[1,3]]", new int[]{}, 1, 3),
                Arguments.of("[[2,2]]", new int[]{-1, 0, 1}, 0, 2),
                Arguments.of("[]", new int[]{-1}, -1, -1),
                Arguments.of("[]", new int[]{-1, 0, 1}, -1, 1),
                Arguments.of("[[-2,-2],[2,2]]", new int[]{-1, 0, 1}, -2, 2),
                Arguments.of("[[1,1],[3,3]]", new int[]{0, 2, 4}, 0, 4),
                Arguments.of("[[2,2],[4,49],[51,74],[76,99]]", new int[]{0, 1, 3, 50, 75}, 0, 99)
        );
    }

    @ParameterizedTest
    @MethodSource("samplesProvider")
    public void test(String expected,
                     int[] nums,
                     int lower,
                     int upper) {
        assertEquals(expected,
                findMissingRanges(nums, lower, upper)
                        .toString().replaceAll(" ", ""));
    }
}
