package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CombinationSum_39 {
    int[] candidates;
    int target;
    List<List<Integer>> out;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.candidates = candidates;
        this.target = target;
        this.out = new ArrayList<>();
        recur(new ArrayList<>(), 0, 0);
        return out;
    }

    void recur(final List<Integer> curList, final int rangeStart, final int curSum) {
//        System.out.println("rangeStart=" + rangeStart + ", curSum=" + curSum + ", curList=" + curList);
        if (curSum == target) {
            out.add(new ArrayList<>(curList));
        } else if (curSum < target && rangeStart<candidates.length) {
            int num = candidates[rangeStart];
            curList.add(num);
            recur(curList, rangeStart, curSum+num);
            curList.remove(curList.size() - 1);
            recur(curList, rangeStart+1, curSum);
        }
    }

    @org.junit.jupiter.api.Test
    public void test1() {
        assertEquals("[[2,2,3],[7]]", combinationSum(new int[]{2, 3, 6, 7}, 7).toString().replaceAll(" ", ""));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        assertEquals("[[2,2,2,2],[2,3,3],[3,5]]", combinationSum(new int[]{2, 3, 5}, 8).toString().replaceAll(" ", ""));
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        assertEquals("[]", combinationSum(new int[]{2}, 1).toString());
    }
}
