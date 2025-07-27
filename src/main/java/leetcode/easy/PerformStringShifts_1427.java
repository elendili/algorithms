package leetcode.easy;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/perform-string-shifts/description/?envType=study-plan-v2&envId=premium-algo-100
 */
public class PerformStringShifts_1427 {
    public String stringShift(String s, int[][] shift) {
        int n = s.length();
        int index = 0;

        for (int[] sh : shift) {
            int direction = sh[0];
            int amount = sh[1];
            if (direction == 0) {
                index = (index + amount) % n;
            } else {
                index = (n*amount+(index - amount)) % n;
            }
        }
        // read string from index to end and then from start to index
        StringBuilder sb = new StringBuilder();
        for (int i = index; i < n; i++) {
            sb.append(s.charAt(i));
        }
        for (int i = 0; i < index; i++) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    @org.junit.jupiter.api.Test
    public void test(){
        assertEquals("bcda", stringShift("abcd", new int[][]{{0,1}}));
        assertEquals("dabc", stringShift("abcd", new int[][]{{1,1}}));
        assertEquals("cab", stringShift("abc", new int[][]{{0,1},{1,2}}));
        assertEquals("efgabcd", stringShift("abcdefg", new int[][] {{1,1},{1,1},{0,2},{1,3}}));
    }
    @org.junit.jupiter.api.Test
    public void test2(){
        assertEquals("deleetco", stringShift("leetcode", new int[][] {{1,10}}));
        assertEquals("codeleet", stringShift("leetcode", new int[][] {{1,100}}));
        assertEquals("etcodele", stringShift("leetcode", new int[][] {{0,10}}));
        assertEquals("codeleet", stringShift("leetcode", new int[][] {{0,100}}));
    }
}
