package leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;

import static helpers.TestHelper.extract1dIntegerArrayFromBracketedString;
import static helpers.TestHelper.extract2dArrayFromBracketedString;

public class TheMazeIII_499 {

    int[][] dirs = new int[][]{
            {0, 1, 'r'},
            {1, 0, 'd'},
            {0, -1, 'l'},
            {-1, 0, 'u'}
    };

    record State(int row, int col, int dist, String path) {
    }

    public String findShortestWay(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;

        Queue<State> q = new PriorityQueue<>((a,b)->{
            if(a.dist == b.dist){
                return a.path.compareTo(b.path);
            }
            return a.dist - b.dist;
        });
        q.add(new State(start[0], start[1], 0, ""));
        boolean[][] seen = new boolean[m][n];

        while (!q.isEmpty()) {
            State state = q.poll();
            if (state.row == destination[0] && state.col == destination[1]) {
                return state.path;
            }
            if (seen[state.row][state.col]) {
                continue;
            }
            seen[state.row][state.col] = true;


            for (int di = 0; di < dirs.length; di++) {
                int[] dir = dirs[di];
                int r = state.row;
                int c = state.col;
                int newDistance = state.dist;
                String newPath = state.path + (char) dir[2];
                int rd = dir[0];
                int cd = dir[1];
                while (r + rd >= 0 && c + cd >= 0 && r + rd < m && c + cd < n && maze[r + rd][c + cd] == 0) {
                    r = r + rd;
                    c = c + cd;
                    newDistance += 1;

                    if (r == destination[0] && c == destination[1]) {
                        break;
                    }

                }
                State newState = new State(r, c, newDistance, newPath);
                q.add(newState);
            }
        }
        return "impossible";
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """
                    expected   | ball      | hole      | maze
                    r          | [0,0]     | [0,1]     | [[0,0]]
                    l          | [0,1]     | [0,0]     | [[0,0]]
                    lul        | [4,3]     | [0,1]     | [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]]
                    impossible | [4,3]     | [3,0]     | [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]]
                    dldr       | [0,4]     | [3,5]     | [[0,0,0,0,0,0,0],[0,0,1,0,0,1,0],[0,0,0,0,1,0,0],[0,0,0,0,0,0,1]]
                    drdrdrdldl | [2,4]     | [7,6]     | [[0,1,0,0,1,0,0,1,0,0],[0,0,1,0,0,1,0,0,1,0],[0,0,0,0,0,0,1,0,0,1],[0,0,0,0,0,0,1,0,0,1],[0,1,0,0,1,0,0,1,0,0],[0,0,1,0,0,1,0,0,0,0],[0,0,0,0,0,0,1,0,0,0],[1,0,0,1,0,0,0,0,0,1],[0,1,0,0,1,0,0,1,0,0],[0,0,0,0,0,1,0,0,1,0]]
                    """
    )
    public void test(String expected, String ball, String hole, String mazeString) {
        Assertions.assertEquals(expected,
                findShortestWay(extract2dArrayFromBracketedString(mazeString),
                        extract1dIntegerArrayFromBracketedString(ball),
                        extract1dIntegerArrayFromBracketedString(hole)));
    }

}
