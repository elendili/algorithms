package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/factor-combinations/?envType=study-plan-v2&envId=premium-algo-100
 */
public class FactorCombinations_254 {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> getFactors(int n) {
        dfs(n, 2, new ArrayList<>());
        return res;
    }

    private void dfs(int n, int start, List<Integer> curr) {
        // try all possible next factors
        for (int i = start; i * i <= n; i++) {
            if (n % i == 0) {
                curr.add(i);
                curr.add(n / i);
                res.add(new ArrayList<>(curr));   // valid combination
                curr.removeLast();
                dfs(n / i, i, curr);              // continue factoring
                curr.removeLast();
            }
        }
    }


    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """
                    n        | expected
                    1        | []
                    12       | [[2,6],[3,4],[2,2,3]]
                    37       | []
                    """
    )
    public void test(int n, String expected) {
        Assertions.assertEquals(expected, getFactors(n).toString().replaceAll(" ", ""));
    }
}
