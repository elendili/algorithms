package hackerrank.medium;

import org.junit.Test;

import static java.lang.Math.abs;
import static org.junit.Assert.assertEquals;

// https://www.hackerrank.com/challenges/magic-square-forming
public class FormingAMagicSquare {
    static int formingMagicSquare(int[][] s) {
        // center of magic square should be 5
        int delta = abs(s[1][1] - 5);
        s[1][1] = 5;
        // for 3x3 magic square all sums should = 15
        int column1 = s[0][0] + s[0][1] + s[0][2];
        int column2 = s[1][0] + s[1][1] + s[1][2];
        int column3 = s[2][0] + s[2][1] + s[2][2];

        int row1 = s[0][0] + s[1][0] + s[2][0];
        int row2 = s[0][1] + s[1][1] + s[2][1];
        int row3 = s[0][2] + s[1][2] + s[2][2];

        int sum7 = s[0][0] + s[1][1] + s[2][2];
        int sum8 = s[0][2] + s[1][1] + s[2][0];

        return delta;

    }


    @Test
    public void test() {
        int[][] square = {{4, 9, 2}, {3, 5, 7}, {8, 1, 5}};
        assertEquals(1, formingMagicSquare(square));
    }

    @Test
    public void test2() {
        int[][] square = {{4, 8, 2}, {4, 5, 7}, {6, 1, 6}};
        assertEquals(4, formingMagicSquare(square));
    }

}
