package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/combinations/description/?envType=study-plan-v2&envId=top-interview-150
 */
public class Combinations_77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> out = new ArrayList<>();
        recur(n, k, 0, 1, new ArrayList<>(), out);
        return out;
    }

    void recur(int n, int k, int curIndex, int curRangeStart, List<Integer> curArray, List<List<Integer>> out) {
        if (curIndex == k) {
            out.add(new ArrayList<>(curArray));
        } else {
            for (int j = curRangeStart; j <= n; j++) {
                curArray.add(j);
                recur(n, k, curIndex + 1, j+1, curArray, out);
                curArray.remove(curArray.size()-1);
            }
        }
    }

    @org.junit.jupiter.api.Test
    public void test_4_2() {
        assertEquals("[[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]", combine(4, 2).toString().replaceAll(" ", ""));
    }

    @org.junit.jupiter.api.Test
    public void test1() {
        assertEquals("[[1]]", combine(1, 1).toString());
    }
    @org.junit.jupiter.api.Test
    public void test3() {
        assertEquals("[[1, 2, 3]]", combine(3, 3).toString());
        assertEquals("[[1, 2], [1, 3], [2, 3]]", combine(3, 2).toString());
        assertEquals("[[1], [2], [3]]", combine(3, 1).toString());
    }
}
