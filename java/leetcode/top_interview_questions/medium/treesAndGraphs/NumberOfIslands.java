package leetcode.top_interview_questions.medium.treesAndGraphs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/108/trees-and-graphs/792/
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
 */
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int count=0;
        for (int i = 0; i < grid.length; i++) {
            char[] row = grid[i];
            for (int j = 0; j < row.length; j++) {
                char cell = row[j];
                if(cell=='1') {
                    count++;
                    recursiveMark(grid,i,j);
                }
            }
        }
        return count;
    }

    void recursiveMark(char[][] a, int i, int j) {
        if(i>-1 && j>-1 && i<a.length && j<a[i].length){
            if(a[i][j]=='1'){
                a[i][j]=0;
                recursiveMark(a,i,j-1);
                recursiveMark(a,i,j+1);
                recursiveMark(a,i-1,j);
                recursiveMark(a,i+1,j);
            }
        }
    }

    @Test
    public void test() {
        char[][] field = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        Assertions.assertEquals(1, numIslands(field));
    }

    @Test
    public void test2() {
        char[][] field = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        Assertions.assertEquals(3, numIslands(field));
    }

    @Test
    public void test3() {
        char[][] field = new char[][]{
                {'1', '1', '1', '1'},
                {'0', '0', '0', '1'},
                {'0', '0', '0', '1'},
                {'1', '1', '1', '1'}
        };
        Assertions.assertEquals(1, numIslands(field));
    }
    @Test
    public void test4() {
        char[][] field = new char[][]{
                {'1', '0', '0', '1'},
                {'1', '0', '0', '1'},
                {'1', '0', '0', '1'},
                {'1', '1', '1', '1'}
        };
        Assertions.assertEquals(1, numIslands(field));
    }
    @Test
    public void test5() {
        char[][] field = new char[][]{
                {'1', '0', '1', '1'},
                {'1', '0', '0', '0'},
                {'0', '0', '0', '1'},
                {'1', '0', '1', '1'},
                {'0', '0', '1', '0'}
        };
        Assertions.assertEquals(4, numIslands(field));
    }

    @Test
    public void test6() {
        char[][] field = new char[][]{
                {'0', '0', '0', '1'},
                {'1', '0', '0', '0'},
                {'0', '1', '0', '0'},
                {'0', '0', '1', '0'},
                {'1', '0', '0', '1'},
                {'0', '0', '1', '0'}
        };
        Assertions.assertEquals(7, numIslands(field));
    }

}
