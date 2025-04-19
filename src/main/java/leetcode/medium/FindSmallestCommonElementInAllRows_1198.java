package leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindSmallestCommonElementInAllRows_1198 {
    public int smallestCommonElement(int[][] mat) {
        // search common element by columns, shifting current position in every row
        int n = mat.length;
        int m = mat[0].length;
        int cur_max = 0;
        int count = 0;
        int[] pos = new int[n];
        while (true) {
            for (int i = 0; i < n; i++) { // iterate rows
                // shift to get cur_max
                while (pos[i] < m && mat[i][pos[i]] < cur_max) {
                    pos[i]++;
                }
                if(pos[i]>=m){
                    return -1;
                }
                int cur_val = mat[i][pos[i]];
                if(cur_val!=cur_max){
                    count=1;
                    cur_max=cur_val;
                } else {
                    count++;
                    if (count==n){
                        return cur_max;
                    }
                }
            }
        }
    }
    
    @org.junit.jupiter.api.Test
    public void test(){
        assertEquals(5, smallestCommonElement(new int[][]{{1,2,3,4,5},{2,4,5,8,10},{3,5,7,9,11},{1,3,5,7,9}}));
        assertEquals(2, smallestCommonElement(new int[][]{{1,2,3},{2,3,4},{2,3,5}}));
    }
}
