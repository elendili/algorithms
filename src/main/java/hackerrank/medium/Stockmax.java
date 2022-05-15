package hackerrank.medium;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

// https://www.hackerrank.com/challenges/stockmax/problem
public class Stockmax {

    public static long stockmax(List<Integer> prices) {
        long cumProfit = 0;
        int sellPrice = prices.get(prices.size() - 1);
        for (int i = prices.size() - 1; i > -1; i--) {
            int curPrice = prices.get(i);
            if (curPrice >= sellPrice) {
                sellPrice = curPrice;
            } else {
                cumProfit += sellPrice - curPrice;
            }
        }
        return cumProfit;
    }

    @Test
    public void test() {
        assertEquals(0L, stockmax(asList(5, 3, 2)));
        assertEquals(3L, stockmax(asList(1, 3, 1, 2)));
        assertEquals(197L, stockmax(asList(1, 2, 100)));
        assertEquals(4L, stockmax(asList(5, 4, 3, 4, 5)));
        assertEquals(4L, stockmax(asList(5, 4, 3, 4, 5, 3)));
        assertEquals(3L, stockmax(asList(0, 1, 0, 1, 0, 1)));
        assertEquals(12L, stockmax(asList(0, 1, 0, 2, 0, 3)));
        assertEquals(6L, stockmax(asList(0, 3, 0, 2, 0, 1)));
        assertEquals(34L, stockmax(asList(2, 1, 2, 1, 10)));
        assertEquals(36L, stockmax(asList(2, 1, 2, 1, 10, 1, 1, 2)));
    }

}
