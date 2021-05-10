package yandex.contest.May2_20201_shad;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class G_ProbabilityOfDistinctNumber {
    long countDistinct(int size) {
        if (size == 1) {
            return 10;
        }
        return IntStream.range((int) Math.pow(10, size - 1), (int) Math.pow(10, size))
                .filter(n -> String.valueOf(n).chars().distinct().count() == size)
                .count();
    }

    long countAllForSize(int size) {
        if (size == 1) {
            return 10;
        }
        return IntStream.range((int) Math.pow(10, size - 1), (int) Math.pow(10, size))
                .count();
    }

    @Test
    public void test() {
        Assertions.assertEquals(10, countDistinct(1));
        Assertions.assertEquals(9 * 9, countDistinct(2));
        Assertions.assertEquals(9 * 9 * 8, countDistinct(3));
        Assertions.assertEquals(9 * 9 * 8 * 7, countDistinct(4));
        Assertions.assertEquals(10_000 - 1000, countAllForSize(4));

        // Correct answer, I think, is
        // countDistinct/countAllForSize = 9 * 9 * 8 * 7/(10_000 - 1000) = 0,504
    }
}
