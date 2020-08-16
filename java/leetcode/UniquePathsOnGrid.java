package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// https://leetcode.com/problems/unique-paths/
public class UniquePathsOnGrid {
    public int uniquePaths(int m, int n) {
        int decisionsToRight = (m - 1);
        int decisionsToDown = (n - 1);
        int countOfDecisions = decisionsToDown + decisionsToRight;
        // combination calculation
        int biggest = Math.max(decisionsToRight, decisionsToDown);
        int smallest = Math.min(decisionsToRight, decisionsToDown);
        // C(n,k) = n!/(k!*(n-k)!) was shortened:
        // (n!/z!)/u!, where z = max(decisionsToDown,decisionsToRight)!
        // where u = min(decisionsToDown,decisionsToRight)!
        // z calculated as product of [max+1, max+2, max+3, .. , n]
        // u is usual factorial
        long denumerator = factorial(countOfDecisions, biggest);
        long divisor = factorial(smallest, 1);
        long out = denumerator/divisor;
        return (int) out;
    }

    long factorial(int top, int bottomNotInclusive) {
        long out = 1;
        for (int i = bottomNotInclusive+1; i <= top; i++) {
            out *= i;
        }
        return out;
    }

    @Test
    public void test() {
        Assertions.assertEquals(3, uniquePaths(2, 3));
        Assertions.assertEquals(6, uniquePaths(3,3));
        Assertions.assertEquals(20, uniquePaths(4,4));
        Assertions.assertEquals(120, uniquePaths(4,8));
        Assertions.assertEquals(193536720, uniquePaths(23, 12));
    }
}
