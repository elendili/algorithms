package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/description/?envType=study-plan-v2&envId=premium-algo-100
 * 4
 * 2       6
 * 1    3   5   7
 * <p>
 * 4 2 1 3 6 5 7
 * <p>
 * 4 2 1 <-3
 * 4
 * 4 <- 6
 * 6 5
 * 6 5 <- 7
 * 7
 */
public class VerifyPreorderSequenceInBinarySearchTree_255 {
    public boolean verifyPreorder(int[] preorder) {
        Deque<Integer> stack = new ArrayDeque<>();
        int minBoundary = Integer.MIN_VALUE;
        for (int i = 0; i < preorder.length; i++) {
            int v = preorder[i];
            while (!stack.isEmpty() && stack.peek() < v) {
                minBoundary = stack.pop();
            }
            if (v<=minBoundary){
                return false;
            }
            stack.push(v);
        }

        return true;
    }

    @ParameterizedTest
    @CsvSource(delimiter = '|', useHeadersInDisplayName = true, value = {
            "id | inputString                 | expected",
            "1  | 2,1,3                       | true",
            "2  | 2,3,1                       | false",
            "3  | 2,1                         | true",
            "4  | 2,3                         | true",
            "5  | 1,2,3                       | true",
            "6  | 3,2,1                       | true",
            "7  | 5, 2, 1, 3, 6               | true",
            "8  | 5, 2, 6, 1, 3               | false",
            "9  | 4, 2, 1, 3, 6, 5, 7         | true",
            "10 | 4, 2, 1, 3, 6, 5            | true",
    })
    public void test(String id, String inputString, boolean expected) {
        int[] input = Arrays.asList(inputString.split(",")).stream().map(String::trim).mapToInt(Integer::parseInt).toArray();
        assertEquals(expected, verifyPreorder(input));
    }
}
