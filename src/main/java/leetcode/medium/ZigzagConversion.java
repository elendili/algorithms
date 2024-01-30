package leetcode.medium;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/zigzag-conversion/
 */
public class ZigzagConversion {
    /**
     * for 2 rows:
     * A C E
     * B D F
     * first line:  0,2,4,6...
     * second line: 1,3,5,7...
     * for 3 rows:
     * first line: 0, 4, 8, 12...
     * second line: 1, 3, 5, 7...
     * third line: 2, 6, 10, 14...
     * for 4 rows:
     * 1 line: 0, 6, 12...
     * 2 line: 1, 5, 7, 11, 13...
     * 3 line: 2, 4, 8, 10, 14...
     * 4 line: 3, 9, 15, ...
     * a   g   m
     * b  fh  ln
     * c e i k o r
     * d   j   p
     */
    public String convert(String s, int numRows) {
        if (numRows <= 1 || s.length() <= numRows) {
            return s;
        }
        // array of letter coordinates
        int[][] coords = new int[s.length()][];
        int[] down = new int[]{0, 1}; // x-shift, y-shift
        int[] upRight = new int[]{1, -1};
        int[] direction = down;
        coords[0] = new int[]{0, 0}; // x,y
        for (int i = 1; i < s.length(); i++) {
            int[] prevCoords = coords[i - 1];
            int yCoord = prevCoords[1] + direction[1];
            if (yCoord > numRows - 1) {
                direction = upRight;
            } else if (yCoord < 0) {
                direction = down;
            }
            yCoord = prevCoords[1] + direction[1];
            int xCoord = prevCoords[0] + direction[0];
            coords[i] = new int[]{xCoord, yCoord};
        }
        // first line
        StringBuilder[] sbArray = new StringBuilder[numRows];
        for (int i = 0; i < s.length(); i++) {
            int[] cc = coords[i];
            int y = cc[1];
            if (sbArray[y] == null) {
                sbArray[y] = new StringBuilder();
            }
            sbArray[y].append(s.charAt(i));
        }
        String out = "";
        for (StringBuilder sb : sbArray) {
            out += sb.toString();
        }
        return out;
    }

    @Test
    public void test() {
        assertEquals("A", convert("A", 1));
        assertEquals("ABCDEF", convert("ABCDEF", 1));
        assertEquals("ACEBDF", convert("ABCDEF", 2));
        assertEquals("PAHNAPLSIIGYIR", convert("PAYPALISHIRING", 3));
        assertEquals("PINALSIGYAHRPI", convert("PAYPALISHIRING", 4));
        assertEquals("AGMBFHLNCEIKORDJP", convert("ABCDEFGHIJKLMNOPR", 4));
        assertEquals("AB", convert("AB", 4));
        assertEquals("ABCD", convert("ABCD", 4));
        assertEquals("ABCED", convert("ABCDE", 4));
    }
}
