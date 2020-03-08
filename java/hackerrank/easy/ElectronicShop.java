package hackerrank.easy;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toCollection;
import static org.junit.jupiter.api.Assertions.assertEquals;

// https://www.hackerrank.com/challenges/electronics-shop/problem
public class ElectronicShop {

    static int getMoneySpent(int[] keyboards, int[] drives, final int b) {
        TreeSet<Integer> kSet = stream(keyboards)
                .filter(i -> i < b)
                .boxed().collect(toCollection(TreeSet::new));
        TreeSet<Integer> dTreeSet = stream(drives)
                .filter(i -> i < b)
                .boxed().collect(toCollection(TreeSet::new));
        if (kSet.isEmpty() || dTreeSet.isEmpty() || (kSet.first() + dTreeSet.first() > b)) {
            return -1;
        }
        Set<Integer> dHashSet = new HashSet<>(dTreeSet);
        for (int i = b; i > 0; i--) {
            NavigableSet<Integer> kDescSet = ((TreeSet<Integer>) kSet.headSet(i)).descendingSet();
            for (int k : kDescSet) {
                int expectedD = i - k;
                if (dHashSet.contains(expectedD)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Test
    public void test() {
        assertEquals(9, getMoneySpent(new int[]{3, 1}, new int[]{5, 2, 8}, 10));
        assertEquals(-1, getMoneySpent(new int[]{4}, new int[]{5}, 5));
    }

}
