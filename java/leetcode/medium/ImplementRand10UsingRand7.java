package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.LongSummaryStatistics;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
    https://leetcode.com/problems/implement-rand10-using-rand7/solution/
*/
public class ImplementRand10UsingRand7 {
    static ThreadLocalRandom r = ThreadLocalRandom.current();

    public static int rand7() {
        return r.nextInt(7) + 1;
    }

    @FunctionalInterface
    interface Rand10 {
        int rand10();
    }

    static class S1 implements Rand10 {
        public int rand10() {
            int row, col, idx;
            do {
                row = rand7();
                col = rand7();
                idx = col + (row - 1) * 7;
            } while (idx > 40);
            return 1 + (idx - 1) % 10;
        }
    }

    static class S2 implements Rand10 {
        public int rand10() {
            int a, b;

            do a = rand7(); while (a == 7);
            do b = rand7(); while (b > 5);

            return (a % 2 == 1) ? b : b + 5;
        }
    }

    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("rand10Stream")
    public void test(Rand10 rand10) {
        HashMap<Integer, Long> frequencies = IntStream.range(0, 1000_000).mapToObj(i -> rand10.rand10())
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        HashMap::new, // can be skipped
                        Collectors.counting()));
        LongSummaryStatistics statistics = frequencies.values().stream().mapToLong(i -> i).summaryStatistics();
        frequencies.replaceAll((k, v) -> v - statistics.getMin());
        frequencies.forEach((k, v) -> {
            System.out.printf("%-2d : %-10d %n", k, v);
        });
    }

    public static Stream<Rand10> rand10Stream() {
        return Stream.of(
                new S1(),
                new S2()
        );
    }

}
