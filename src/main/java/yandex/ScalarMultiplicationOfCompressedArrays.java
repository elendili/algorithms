package yandex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScalarMultiplicationOfCompressedArrays {
    int getScalarMultiplication(int[][] a, int[][] b) {
        if (a==null || b==null || a.length == 0 || b.length == 0) {
            return 0;
        }
        int ai = -1, bi = -1;
        int out = 0;
        int countA = 0;
        int countB = 0;
        int va = 0;
        int vb = 0;
        while (ai < a.length && bi < b.length) {
            out += va * vb;
            if (--countA < 1) {
                if (++ai < a.length) {
                    int[] _a = a[ai];
                    assert _a != null && _a.length == 2 : "array has null or non-pair definition";
                    countA = _a[1];
                    va = _a[0];
                }
            }
            if (--countB < 1) {
                if (++bi < b.length) {
                    int[] _b = b[bi];
                    assert _b != null && _b.length == 2 : "array has null or non-pair definition";
                    countB = b[bi][1];
                    vb = b[bi][0];
                }
            }
        }
        assert countA == countB : "uncompressed arrays have diff length";
        return out;
    }

    @Test
    public void test() {
        Assertions.assertEquals(0,
                getScalarMultiplication(new int[][]{},
                        new int[][]{{1, 1}, {1, 1}}));

        Assertions.assertEquals(0,
                getScalarMultiplication(new int[][]{{}},
                        new int[][]{}));

        Assertions.assertEquals(1 + 1,
                getScalarMultiplication(new int[][]{{1, 1}, {1, 1}},
                        new int[][]{{1, 1}, {1, 1}}));
        Assertions.assertEquals(1 + 4,
                getScalarMultiplication(new int[][]{{1, 1}, {2, 1}},
                        new int[][]{{1, 1}, {2, 1}}));

        Assertions.assertEquals(1 * 2 + 1 * 2,
                getScalarMultiplication(new int[][]{{1, 2}},
                        new int[][]{{2, 2}}));

        Assertions.assertEquals(2 * 2 + 2 * 2,
                getScalarMultiplication(new int[][]{{2, 2}},
                        new int[][]{{2, 2}}));
        Assertions.assertEquals(1 * 1 + 1 * 1 + 2 * 1 + 2 * 2 + 2 * 2,
                getScalarMultiplication(new int[][]{{1, 2}, {2, 3}},
                        new int[][]{{1, 3}, {2, 2}}));

        Assertions.assertEquals(1 * 2 + 1 * 3,
                getScalarMultiplication(new int[][]{{1, 2}},
                        new int[][]{{2, 1}, {3, 1}}));

        Assertions.assertEquals(1 * 3 + 2 * 3 + 3 * 3,
                getScalarMultiplication(new int[][]{{1, 1}, {2, 1}, {3, 1}},
                        new int[][]{{3, 3}}));

        Assertions.assertEquals(-1 * 3 + 0 * 3 + 1 * 3 + 2*3,
                getScalarMultiplication(new int[][]{{-1, 1}, {0, 1}, {1, 1}, {2, 1}},
                        new int[][]{{3, 4}}));

    }

    @Test
    public void testException() {
        Throwable t = assertThrows(AssertionError.class,
                () -> getScalarMultiplication(new int[][]{{1, 1}}, new int[][]{{1, 2}}));
        Assertions.assertEquals("uncompressed arrays have diff length", t.getMessage());
        t = assertThrows(AssertionError.class,
                () -> getScalarMultiplication(new int[][]{{}}, new int[][]{{1, 2}}));
        Assertions.assertEquals("array has null or non-pair definition", t.getMessage());
    }
}
