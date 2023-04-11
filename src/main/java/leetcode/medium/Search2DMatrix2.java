package leetcode.medium;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
// https://leetcode.com/study-plan/data-structure
public class Search2DMatrix2 {
    // search from left bottom, where the max of left column and min of last row
    public boolean searchMatrix(int[][] a, int target) {
        if(a.length>0 && a[0].length>0){
            int my = a.length-1;
            int mx = a[0].length-1;
            int x=0, y=my;
            while (x>=0 && x<=mx && y>=0 && y<=my) { // check coords in matrix
                int cv = a[y][x];
                if(cv>target){
                    y--;
                } else if(cv<target){
                    x++;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void test() {
        assertTrue(searchMatrix(new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        }, 5));
    }

    @Test
    public void test2() {
        assertFalse(searchMatrix(new int[][]{{}},0));
        assertFalse(searchMatrix(new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        }, 20));
    }

    @Test
    public void test3() {
        assertTrue(searchMatrix(new int[][]{{1,3}},3));
        assertTrue(searchMatrix(new int[][]{{1},{3}},3));
    }

}
