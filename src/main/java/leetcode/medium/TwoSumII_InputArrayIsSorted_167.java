package leetcode.medium;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwoSumII_InputArrayIsSorted_167 {
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int left=0, right=1;
        for (; left < n-1; left++) {
            int desiredRightV = target - numbers[left];
            right = Arrays.binarySearch(numbers, left+1, n, desiredRightV);
            if(right>0){
                break;
            }
        }
        return new int[]{left + 1, right + 1};
    }


    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals("[1, 2]", Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
        assertEquals("[1, 3]", Arrays.toString(twoSum(new int[]{2, 3, 4}, 6)));
        assertEquals("[1, 2]", Arrays.toString(twoSum(new int[]{-1, 0}, -1)));
        assertEquals("[2, 3]", Arrays.toString(twoSum(new int[]{-1, 2, 2}, 4)));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        assertEquals("[3, 5]", Arrays.toString(twoSum(new int[]{-10, -8, -2, 1, 2, 5, 6}, 0)));
    }
}
