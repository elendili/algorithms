package leetcode.top_interview_questions.medium.backtracking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/109/backtracking/797/

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.


Constraints:

board and word consists only of lowercase and uppercase English letters.
1 <= board.length <= 200
1 <= board[i].length <= 200
1 <= word.length <= 10^3
 */
public class WordSearchIn2DGrid {

    public boolean exist(char[][] a, String word) {
        char[] chars = word.toCharArray();
        for(int y=0;y<a.length;y++){
            for(int x=0;x<a[y].length;x++) {
                if(a[y][x]==chars[0]){
                    if (recursion(a,y,x,chars,0)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean recursion(char[][] a, int y,int x, char[] chars, int ci){
        if(y<0 || y>=a.length || x<0 || x>=a[y].length || a[y][x]!=chars[ci]){
            return false;
        } else if (ci==chars.length-1) {
            return true;
        } else {
            char tmp = a[y][x];
            a[y][x]='0';
            boolean out =
                    recursion(a,y-1,x,chars,ci+1) ||
                            recursion(a,y+1,x,chars,ci+1) ||
                            recursion(a,y,x-1,chars,ci+1) ||
                            recursion(a,y,x+1,chars,ci+1)
                    ;
            a[y][x]=tmp;
            return out;
        }
    }

    @Test
    public void test(){
        char[][] grid = new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        Assertions.assertTrue( exist(grid, "A"));
        Assertions.assertTrue( exist(grid, "AB"));
        Assertions.assertTrue( exist(grid, "ASAD"));
        Assertions.assertTrue( exist(grid, "ABCCED"));
        Assertions.assertTrue( exist(grid, "SEE"));

        Assertions.assertFalse( exist(grid, "AA"));
        Assertions.assertFalse( exist(grid, "ABA"));
        Assertions.assertFalse( exist(grid, "ABB"));
        Assertions.assertFalse( exist(grid, "ABCB"));
        Assertions.assertFalse( exist(grid, "BCCFB"));
    }

}
