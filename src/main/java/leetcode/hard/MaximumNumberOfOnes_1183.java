package leetcode.hard;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaximumNumberOfOnes_1183 {
    int calcCountOfCells(int totalLength, int sectionLength, int zeroBasedIndexInSection) {
        return 1 + (totalLength - zeroBasedIndexInSection - 1) / sectionLength;
    }

    public int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        // calc count of cells of top-left square which can be found on surface with given width and height
        int cellsCountInWindow = sideLength * sideLength;
        int[] a = new int[cellsCountInWindow];
        int i = 0;
        for (int x = 0; x < sideLength; x++) {
            for (int y = 0; y < sideLength; y++) {
                int countByX = calcCountOfCells(width, sideLength, x);
                int countByY = calcCountOfCells(height, sideLength, y);
                int xyCellCount = countByX * countByY;
                a[i++] = xyCellCount;
            }
        }
        Arrays.sort(a);

        int out = 0;
        i = cellsCountInWindow - 1;
        for (; maxOnes > 0 && i > -1; maxOnes--, i--) {
            out += a[i];
        }

        return out;
    }

    @ParameterizedTest(name = "[{index}] {0}, {1}, {2}, {3} => {4}")
    @CsvSource(
            value = {
                    "width, height, sideLength, maxOnes, expected",
                    "3,  3,  2,  1,  4",
                    "3,  3,  2,  2,  6",
                    "94, 86, 1,  1,  8084"
            },
            useHeadersInDisplayName = true
    )
    void testMaximumNumberOfOnes(int width, int height, int sideLength, int maxOnes, int expected) {
        assertEquals(expected, maximumNumberOfOnes(width, height, sideLength, maxOnes));
    }
}
