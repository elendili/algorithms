package hackerrank.medium;

import org.junit.Test;

import java.util.List;
import java.util.TreeMap;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;
import static java.util.stream.Stream.of;
import static org.junit.Assert.assertEquals;

// https://www.hackerrank.com/challenges/stockmax/problem
public class Stockmax {

    private static int findEarliestPointLessThanLimit(List<Integer> arr, int fromIndex, int limit) {
        int i;
        for (i = fromIndex;
             i > 0 && arr.get(i - 1) < limit;
             i--)
            ;
        return i;
    }

    // price of point to indexes
    private static TreeMap<Integer, List<Integer>> findSellPoints(List<Integer> arr) {
        TreeMap<Integer, List<Integer>> out = new TreeMap<>();
        int prev = arr.get(0);
        for (int i = 0; i < arr.size() - 1; i++) {
            int cur = arr.get(i);
            int next = arr.get(i + 1);
            int delta1 = prev - cur;
            int delta2 = cur - next;
            if (delta1 < 0 && delta2 > 0) {
                int finalI = i;
                out.compute(cur, (key, oldV) -> oldV == null ?
                        asList(finalI) :
                        concat(oldV.stream(), of(finalI)).collect(toList()));
            }
            prev = cur;
        }
        return out;
    }


    public static long stockmax(List<Integer> prices) {
        long cumProfit = 0;
        TreeMap<Integer, List<Integer>> sellPointsMap = findSellPoints(prices);
        if (sellPointsMap.isEmpty()) {
            return 0;
        }
        List<Integer> sellPoints = sellPointsMap.descendingMap().values().stream().map(v -> v.get(v.size() - 1)).collect(toList());
        for (int i = 0; i < sellPoints.size(); i++) {
            int sellPointIndex = sellPoints.get(i);
            int prevSellPointIndex = i>0?sellPoints.get(i-1):-1;
            for (int j = prevSellPointIndex+1; j < sellPointIndex; j++) {
                int sellPrice = prices.get(sellPointIndex);
                int curProfit = sellPrice - prices.get(j);
                if (prices.get(j) < sellPrice) {
                    cumProfit += curProfit;
//                        System.out.printf("buy on %d for %d, sell on %d for %d. Profit: %d. Total Profit: %d\n",
//                                i, prices.get(i), tippingPoint, prices.get(tippingPoint), curProfit, cumProfit);
                }
            }
        }
        return cumProfit;
    }

    @Test
    public void test() {
//        assertEquals(0L, stockmax(asList(5, 3, 2)));
        assertEquals(3L, stockmax(asList(1, 3, 1, 2)));
//        assertEquals(197L, stockmax(asList(1, 2, 100)));
//        assertEquals(4L, stockmax(asList(5, 4, 3, 4, 5)));
//        assertEquals(4L, stockmax(asList(5, 4, 3, 4, 5, 3)));
//        assertEquals(3L, stockmax(asList(0, 1, 0, 1, 0, 1)));
//        assertEquals(19L, stockmax(asList(2, 1, 2, 1, 10)));
//2 8
//1 9
//2 8
//1 9
    }
}
