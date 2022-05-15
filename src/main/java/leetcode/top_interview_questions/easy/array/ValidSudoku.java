package leetcode.top_interview_questions.easy.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/92/array/769/

Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

A partially filled sudoku which is valid.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

Example 1:

Input:
[
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: true
Example 2:

Input:
[
  ["8","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being
    modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.
The given board contain only digits 1-9 and the character '.'.
The given board size is always 9x9.
 */
public class ValidSudoku {
    public boolean isValidSudoku(char[][] aa) {
        boolean[] set = new boolean[9];
        final int n = aa.length;
        int k;
        // search in rows
        for(char[] a:aa){
            for(char c:a){
                if(c!='.'){
                    k=c-49;
                    if(set[k]){
                        return false;
                    }
                    else{
                        set[k]=true;
                    }
                }
            }
            Arrays.fill(set,false);
        }
        // search in columns
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                char c = aa[j][i];
                if(c!='.'){
                    k=c-49;
                    if(set[k]){
                        return false;
                    }
                    else{
                        set[k]=true;
                    }
                }
            }
            Arrays.fill(set,false);
        }
        // search in 3x3 squares
        int squaresCount = n/3;
        for(int i=0;i<squaresCount;i++){
            for(int j=0;j<squaresCount;j++) {
                // check 3x3 square
                for(int i1=0;i1<3;i1++) {
                    for (int i2 = 0; i2 < 3; i2++) {
                        char c = aa[i*3+i1][j*3+i2];
                        if (c != '.') {
                            k=c-49;
                            if(set[k]){
                                return false;
                            }
                            else{
                                set[k]=true;
                            }
                        }
                    }
                }
                Arrays.fill(set,false);
            }
        }
        return true;
    }

    @Test
    public void testValid3() {
        char[][] actual;
        actual = new char[][]{
            {'5','3','.'},
            {'1','.','.'},
            {'.','.','9'},
        };
        assertTrue(isValidSudoku(actual));
    }

    @Test
    public void testValid6() {
        char[][] actual;
        actual = new char[][]{
            {'5','3','.',  '1','.','.'},
            {'1','.','.',  '.','2','.'},
            {'.','.','9',  '.','3','.'},

            {'.','4','.',  '2','.','.'},
            {'3','.','.',  '.','.','5'},
            {'.','.','7',  '.','1','.'},
        };
        assertTrue(isValidSudoku(actual));
    }

    @Test
    public void testValid9() {
        char[][] actual;
        actual = new char[][]{
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        assertTrue(isValidSudoku(actual));
    }

    @Test
    public void testInValid() {
        char[][] actual;
        actual = new char[][]{
                {'.','3','.'},
                {'1','.','2'},
                {'.','3','.'},
        };
        assertFalse(isValidSudoku(actual));
        actual = new char[][]{
                {'.','3','.'},
                {'1','.','1'},
                {'.','.','.'},
        };
        assertFalse(isValidSudoku(actual));
        actual = new char[][]{
                {'9','3','.'},
                {'1','.','.'},
                {'.','.','9'},
        };
        assertFalse(isValidSudoku(actual));
        actual = new char[][]{
            {'8','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        assertFalse(isValidSudoku(actual));
    }
}
