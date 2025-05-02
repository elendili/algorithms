package leetcode.medium;

import org.junit.jupiter.api.Assertions;

/**
 * https://leetcode.com/problems/lonely-pixel-i/description/?envType=study-plan-v2&envId=premium-algo-100
 */
public class LonelyPixel_I_531 {
    public int findLonelyPixel(char[][] picture) {
        int h = picture.length;
        int w = picture[0].length;

        int[] inRows = new int[h];
        int[] inColumns = new int[w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (picture[i][j] == 'B') {
                    inRows[i]++;
                    inColumns[j]++;
                }
            }
        }

        int out = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (picture[i][j] == 'B' && inRows[i] == 1 && inColumns[j] == 1) {
                    out++;
                }
            }
        }
        return out;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        Assertions.assertEquals(3, findLonelyPixel(new char[][]{
                {'W', 'W', 'B'},
                {'W', 'B', 'W'},
                {'B', 'W', 'W'}
        }));
        Assertions.assertEquals(0, findLonelyPixel(new char[][]{{'B', 'B', 'B'}, {'B', 'B', 'W'}, {'B', 'B', 'B'}}));
    }
}
