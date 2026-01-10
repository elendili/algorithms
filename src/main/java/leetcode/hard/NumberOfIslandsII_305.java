package leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static helpers.TestHelper.extract2dIntegerArrayFromBracketedString;

public class NumberOfIslandsII_305 {
    int[][] directions = new int[][]{
            new int[]{0, 1},
            new int[]{1, 0},
            new int[]{-1, 0},
            new int[]{0, -1},
    };
    int m;
    int n;
    int[][] map;

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        this.m = m;
        this.n = n;
        this.map = new int[m][n];
        List<Integer> out = new ArrayList<>();
        int nextIslandId = 1;
        int curIslandCount = 0;
        TreeSet<Integer> islandsAround = new TreeSet<>();
        for (int[] p : positions) {
            int pr = p[0];
            int pc = p[1];

            if (map[pr][pc] > 0) { // island already exists
                out.add(curIslandCount);
                continue;
            }
            // new land to be created

            // gather islandNumber around position
            islandsAround.clear();
            for (int i = 0; i < directions.length; i++) {
                int[] dir = directions[i];
                int r = pr + dir[0];
                int c = pc + dir[1];
                if (r > -1 && r < m && c > -1 && c < n && map[r][c] > 0) {
                    islandsAround.add(map[r][c]);
                }
            }

            if (!islandsAround.isEmpty()) {
                curIslandCount -= islandsAround.size();
                map[pr][pc] = islandsAround.getFirst();
                // mark island
                colorIsland(pr, pc);
            } else {
                map[pr][pc] = nextIslandId++;
            }
            curIslandCount += 1;
            out.add(curIslandCount);
//            System.out.println(twoDArrayToString(map));
//            System.out.println();
        }
        return out;
    }

    void colorIsland(int r, int c) {
        // gather islandNumber around position
        for (int i = 0; i < directions.length; i++) {
            int[] dir = directions[i];
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (nr > -1 && nr < m && nc > -1 && nc < n && map[nr][nc] > 0 && map[nr][nc] != map[r][c]) {
                map[nr][nc] = map[r][c];
                colorIsland(nr, nc);
            }
        }
    }


    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """
                    expected             | m | n |  positions 
                    [1,1,2,3]            | 3 | 3 |  [[0,0],[0,1],[1,2],[2,1]]
                    [1]                  | 1 | 1 |  [[0,0]]
                    [1,2]                | 2 | 2 |  [[0,0],[1,1]]
                    [1,1]                | 2 | 2 |  [[0,0],[0,1]]
                    [1,2,3,4,3,2,1]      | 3 | 3 |  [[0,1],[1,2],[2,1],[1,0],[0,2],[0,0],[1,1]]
                    [1,1,2,2]            | 3 | 3 |  [[0,0],[0,1],[1,2],[1,2]]
                    """
    )
    public void test(String expected, int m, int n, String positions) {
        Assertions.assertEquals(expected,
                numIslands2(m, n, extract2dIntegerArrayFromBracketedString(positions)).toString().replaceAll(" ", "")
        );
    }
}
