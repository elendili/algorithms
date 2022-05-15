package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//    https://leetcode.com/problems/word-search/
public class WordSearchIn2DArray {

    class Solution {
        public boolean exist(char[][] board, String word) {
            if (board != null && word != null && word.length() > 0) {
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[i].length; j++) {
                        if (exist(board, i, j, word, 0)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        protected boolean exist(char[][] board, int i, int j,
                                String word, int iW
        ) {
            if (iW == word.length()) {
                return true;
            }
            if (i < 0 || i >= board.length
                    || j < 0 || j >= board[0].length
                    || board[i][j] != word.charAt(iW)) {
                return false;
            }
            board[i][j] = '#';
            boolean out = exist(board, i - 1, j, word, iW + 1)
                    || exist(board, i + 1, j, word, iW + 1)
                    || exist(board, i, j - 1, word, iW + 1)
                    || exist(board, i, j + 1, word, iW + 1);

            board[i][j] = word.charAt(iW);
            return out;
        }
    }

    @Test
    public void test() {
        char[][] fromleetcode;
        Assertions.assertFalse(new Solution().exist(new char[][]{}, "X"));
        Assertions.assertFalse(new Solution().exist(new char[][]{{'Y'}}, "X"));
        Assertions.assertTrue(new Solution().exist(new char[][]{{'X'}}, "X"));
        Assertions.assertTrue(new Solution().exist(new char[][]{{'X', 'Y'}}, "YX"));
        Assertions.assertTrue(new Solution().exist(new char[][]{{'X', 'Y'}, {'A', 'B'}}, "YXAB"));
        Assertions.assertTrue(new Solution().exist(new char[][]{{'X', 'Y'}, {'A', 'B'}}, "YBAX"));
        Assertions.assertTrue(new Solution().exist(new char[][]{{'X', 'Y'}, {'A', 'B'}}, "ABYX"));
        Assertions.assertTrue(new Solution().exist(new char[][]{{'A', 'Y'}, {'A', 'B'}}, "ABYA"));
        Assertions.assertTrue(new Solution().exist(new char[][]{{'A', 'Y'}, {'A', 'B'}}, "AYBA"));
        Assertions.assertTrue(new Solution().exist(new char[][]{{'A', 'Y'}, {'A', 'B'}}, "BYAA"));
        Assertions.assertTrue(new Solution().exist(new char[][]{{'A', 'A'}, {'A', 'A'}}, "AAAA"));
        Assertions.assertTrue(new Solution().exist(
                new char[][]{{'A', 'A', 'A'}, {'A', 'A', 'A'}, {'A', 'A', 'A'}}, "AAAAAAAAA"));
        Assertions.assertTrue(new Solution().exist(
                new char[][]{{'A', 'E', 'E'}, {'D', 'E', 'E'}, {'F', 'E', 'E'}}, "DEEEEA"));
        Assertions.assertTrue(new Solution().exist(
                new char[][]{{'A', 'E', 'E'}, {'D', 'E', 'E'}, {'F', 'E', 'E'}}, "DEEEEF"));
        Assertions.assertFalse(new Solution().exist(new char[][]{{'A', 'Y'}, {'A', 'B'}}, "ABAY"));
        Assertions.assertFalse(new Solution().exist(new char[][]{{'A', 'Y'}, {'A', 'B'}}, "ABYB"));


        fromleetcode = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        Assertions.assertFalse(new Solution().exist(fromleetcode, "ABCB"));
        Assertions.assertTrue(new Solution().exist(fromleetcode, "SEE"));
        Assertions.assertTrue(new Solution().exist(fromleetcode, "ABCCED"));

        fromleetcode = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}};
        Assertions.assertTrue(new Solution().exist(fromleetcode, "ABCESEEEFS"));
/*

{'A','B','C','E'}
{'S','F','E','S'}
{'A','D','E','E'}
*/

    }
}
