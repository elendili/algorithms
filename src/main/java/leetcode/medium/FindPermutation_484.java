package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindPermutation_484 {
    public int[] findPermutation(String s) {
        int n = s.length() + 1;
        int[] out = new int[n];
        int oi = 0;
        Stack<Integer> stack = new Stack<>();
        int ni = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            stack.push(ni++);
            if (c == 'I') {
                while (!stack.isEmpty()) {
                    int v = stack.pop();
                    out[oi++] = v;
                }
            } else {
                // D -> do nothing, wait for I or end
            }
        }
        stack.push(ni);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            out[oi++] = v;
        }
        return out;
    }


    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "I   | [1,2]",
            "DI  | [2,1,3]",
            "ID  | [1,3,2]",
            "IDI | [1,3,2,4]",
            "DID | [2,1,4,3]",
            "IIDD | [1,2,5,4,3]",
            "DDII | [3,2,1,4,5]",
            "DDDD | [5,4,3,2,1]",
    })
    // on start I get smallest from existing
    // on end I get biggest from existing
    public void test(String input, String expected) {
        assertEquals(expected, Arrays.toString(findPermutation(input)).replaceAll(" ", ""));
    }
}
