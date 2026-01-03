package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static helpers.TestHelper.extract1dArrayFromBracketedString;
import static helpers.TestHelper.extract2dArrayFromBracketedString;

public class TheMaze_490 {

    int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};


    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];

        return backtrack(maze, start, destination, visited);
    }

    private boolean backtrack(int[][] maze, int[] node, int[] destination, boolean[][] visited) {
        if (visited[node[0]][node[1]]) return false;
        if (node[0] == destination[0] && node[1] == destination[1]) return true;

        visited[node[0]][node[1]] = true;

        for (int[] dir : dirs) {
            int r = node[0];
            int c = node[1];

            while (r + dir[0] >= 0 && c + dir[1] >= 0 && r + dir[0] < maze.length && c + dir[1] < maze[0].length && maze[r + dir[0]][c + dir[1]] != 1) {
                r = r + dir[0];
                c = c + dir[1];
            }

            if (backtrack(maze, new int[]{r, c}, destination, visited)) {
                return true;
            }
        }

        return false;
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """
                    expected | src      | dest      | maze
                    true     | [0,0]    | [0,1]     | [[0,0],[0,0]]
                    true     | [0,0]    | [1,0]     | [[0,0],[0,0]]
                    true     | [0,0]    | [1,1]     | [[0,0],[0,0]]
                    true     | [0,4]    | [4,4]     | [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]]
                    false    | [0,4]    | [3,2]     | [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]]
                    false    | [4,3]    | [0,1]     | [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]]
                    """
    )
    public void test(boolean expected, String src, String dest, String mazeString) {
        Assertions.assertEquals(expected,
                hasPath(extract2dArrayFromBracketedString(mazeString),
                        extract1dArrayFromBracketedString(src),
                        extract1dArrayFromBracketedString(dest)));
    }
}
