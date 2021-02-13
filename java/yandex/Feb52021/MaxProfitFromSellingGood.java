package yandex.Feb52021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// array of prices for good
// we produce 1 good every day
public class MaxProfitFromSellingGood {

    int maxProfitFromSellingGood(int[] prices) {
        if (prices == null || prices.length < 1) {
            return 0;
        } else if (prices.length == 1) {
            return prices[0];
        }
        int curP, maxP = Integer.MIN_VALUE;
        int out = 0;
        for (int i = prices.length - 1; i > -1; i--) {
            curP = prices[i];
            if (curP > maxP) {
                maxP = curP;
            }
            out += maxP;
        }
        return out;
    }

    @Test
    public void test() {
        Assertions.assertEquals(0, maxProfitFromSellingGood(new int[]{}));
        Assertions.assertEquals(1, maxProfitFromSellingGood(new int[]{1}));
        Assertions.assertEquals(6, maxProfitFromSellingGood(new int[]{1, 3}));
        Assertions.assertEquals(10, maxProfitFromSellingGood(new int[]{1, 3, 1, 2}));
        Assertions.assertEquals(9, maxProfitFromSellingGood(new int[]{1, 2, 3}));
        Assertions.assertEquals(6, maxProfitFromSellingGood(new int[]{3, 2, 1}));
    }
}
/*
1, 3, 1, 2 ->

index*price

 */