package leetcode.easy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountingElements_1426 {
    public int countElements(int[] arr) {
        int[] counts = new int[1001];
        for(int n: arr) {
            counts[n]++;
        }
        int out = 0;
        for (int i = 0; i < counts.length-1; i++) {
            int cur = counts[i];
            if (cur>0 && counts[i+1]>0) {
                out += cur;
            }
        }
        return out;
    }

    @org.junit.jupiter.api.Test
    public void test(){
        assertEquals(2, countElements(new int[]{1,2,3}));
        assertEquals(0, countElements(new int[]{1}));
        assertEquals(0, countElements(new int[]{1,3}));
        assertEquals(0, countElements(new int[]{1,1,3,3,5,5,7,7}));
    }
}
