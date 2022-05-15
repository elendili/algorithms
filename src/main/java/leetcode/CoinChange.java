package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;

// https://leetcode.com/problems/coin-change/
public class CoinChange {
    // when coins are like in reality, but in unpredicted order
    public int normalCoinChange(int[] coins, int amount) {
        int out = 0;
        int left = amount;
        Arrays.sort(coins);
        for (int i = coins.length - 1; i > -1; i--) {
            int c = coins[i];
            out += (left / c);
            left = left % c;
        }
        return left > 0 ? -1 : out;
    }

    public int coinChange(int[] coins, int amount) {
        return _coinChange(coins, amount, new int[amount + 1]);
    }

    public int _coinChange(int[] coins, int amount, int[] memorized) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (memorized[amount] != 0) {
            return memorized[amount];
        }
        int out = Integer.MAX_VALUE;
        for (int i = coins.length - 1; i > -1; i--) {
            final int c = coins[i];
            final int left = amount - c;
            int r = _coinChange(coins, left, memorized);
            if (r >= 0 && r < out) {
                out = r + 1;
            }
        }
        if(out == Integer.MAX_VALUE){
            memorized[amount]=-1;
        } else {
            memorized[amount]=out;
        }
        return memorized[amount];
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    List<int[]> heapPermutation(int[] a) {
        List<int[]> l = new LinkedList<>();
        heapPermutation(a, a.length, l);
        return l;
    }

    void heapPermutation(int[] a, int size, List<int[]> outList) {
        if (size == 1) {
            int[] b = new int[a.length];
            System.arraycopy(a, 0, b, 0, a.length);
            outList.add(b);
        } else {
            final int last = size - 1;
            heapPermutation(a, last, outList);
            for (int i = 0; i < last; i++) {
                if (size % 2 == 1) {
                    swap(a, 0, last);
                } else {
                    swap(a, i, last);
                }
                heapPermutation(a, last, outList);
            }
        }
    }

    @Test
    public void test() {
        Assertions.assertEquals(1, coinChange(new int[]{3}, 3));
        Assertions.assertEquals(-1, coinChange(new int[]{2}, 3));
        Assertions.assertEquals(3, coinChange(new int[]{1, 2, 5}, 11));
        Assertions.assertEquals(2, coinChange(new int[]{10, 2, 1}, 12));
        Assertions.assertEquals(-1, coinChange(new int[]{10, 7, 3}, 11));
        Assertions.assertEquals(10, coinChange(new int[]{10, 7, 3}, 100));
        Assertions.assertEquals(20, coinChange(new int[]{186, 419, 83, 408}, 6249));
    }


    @Test
    public void testPermutation() {
        List<int[]> l = heapPermutation(new int[]{1, 2, 3});
        l.stream().map(ints ->
                Arrays.stream(ints).boxed().collect(toList())
        ).forEach(System.out::println);
    }
}
