package leetcode.medium;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
https://leetcode.com/problems/random-pick-with-weight/
 */
public class RandomPickWithWeight {
    static class Solution {
        private final int[] presum;
        final ThreadLocalRandom tlr = ThreadLocalRandom.current();

        public Solution(int[] w) {
            int prev = 0;
            for (int i = 0; i < w.length; i++) {
                w[i] += prev;
                prev = w[i];
            }
            this.presum = w;
        }

        public int pickIndex() {
            int sum = presum[presum.length - 1];
            int dice = tlr.nextInt(sum);
            int index = Arrays.binarySearch(presum, dice);
            int out;
            if (index < 0) {
                out = Math.abs(index) - 1;
            } else {
                out = index + 1;
            }
            return out;
        }
    }

    public void testBody(int[] weights) {
        System.out.println("-----------");
        Solution solution = new Solution(weights);
        HashMap<Integer, Long> frequencies = IntStream.range(0, 100_000)
                .mapToObj(i -> solution.pickIndex())
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        HashMap::new,
                        Collectors.counting()));
        frequencies.forEach((k, v) -> {
            System.out.printf("%-2d : %-10d %n", k, v);
        });
        LongSummaryStatistics statistics = frequencies.values().stream().mapToLong(i -> i).summaryStatistics();
        List<Double> l = frequencies.values().stream()
                .map(aLong -> ((double) aLong / statistics.getSum()) * 100)
                .collect(Collectors.toList());
        l.forEach(e -> {
            System.out.printf("%3.2f %% %n", e);
        });
    }

    @Test
    public void test() {
        testBody(new int[]{1});
        testBody(new int[]{10});
        testBody(new int[]{1, 3});
        testBody(new int[]{99, 1});
    }

}
