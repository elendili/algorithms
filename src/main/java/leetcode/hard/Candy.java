package leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Candy {

    public int candy(int[] ratings) {
        int n = ratings.length;
        // form left to right
        int[] candies = new int[ratings.length];
        for (int i = 0; i < n; i++) {
            int prev = i > 0 ? ratings[i - 1] : ratings[i];
            if (ratings[i] > prev) {
                candies[i] = candies[i - 1] + 1;
            } else {
                candies[i] = 1;
            }
        }
        // form right to left
        for (int i = n - 1; i > -1; i--) {
            int prev = i < n - 1 ? ratings[i + 1] : ratings[i];
            if (ratings[i] > prev) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            } 
        }
        // reduce
        int out = 0;
        for (int i = 0; i < n; i++) {
            out += candies[i];
        }
        return out;
    }

    @Test
    public void test() {
        Assertions.assertEquals(5, candy(new int[]{1, 0, 2}));
        Assertions.assertEquals(4, candy(new int[]{1, 2, 2}));
        Assertions.assertEquals(6, candy(new int[]{1, 2, 3}));
        Assertions.assertEquals(6, candy(new int[]{3, 2, 1}));
        Assertions.assertEquals(3, candy(new int[]{2, 2, 2}));
        Assertions.assertEquals(4, candy(new int[]{0, 2, 0}));
    }
}
