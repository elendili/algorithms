package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;


public class MinimumKnightMoves_1197 {
    int[][] moveTypes = new int[][]{
            {-2, 1}, //up-right
            {-2, -1}, //up-left
            {2, 1}, //down-right
            {2, -1}, //down-left
            {-1, -2}, //left-up
            {1, -2}, //left-down
            {-1, 2}, //right-up
            {1, 2}, //right-down
    };

    static int getManhattanDistance(int ax, int ay, int bx, int by) {
        return Math.abs(ax - bx) + Math.abs(ay - by);
    }

    int getVisitedKey(int x, int y) {
        return x * 1000 + y;
    }

    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        Queue<int[]> q = new ArrayDeque<>();

//                new PriorityQueue<>(Comparator.comparing(pm -> pm.distance));

        int[] zeroPos = new int[]{0, 0, 0};
        int distanceToTargetFromOriginal = getManhattanDistance(0, 0, x, y);
        q.add(zeroPos);

        Map<Integer, Integer> visited = new HashMap<>();
        int targetMinMovesCount = Integer.MAX_VALUE;
        int totalPositionTried = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            int curMoves = cur[2];
            totalPositionTried++;
            int distToTarget = getManhattanDistance(cx, cy, x, y);
//            System.out.println(totalPositionTried + ", cx=" + cx + ", cy=" + cy + ", moves=" + curMoves + ", to " +  "target=" + distToTarget);

            if (curMoves > targetMinMovesCount || distToTarget > (distanceToTargetFromOriginal + 3)) {
                continue;
            }
            int visitedKey = getVisitedKey(cx, cy);
            Integer movesCountOnVisited = visited.get(visitedKey);
            if (movesCountOnVisited != null && curMoves >= movesCountOnVisited) {
                continue;
            }
            visited.put(visitedKey, curMoves);

            if (cx == x && cy == y && targetMinMovesCount > curMoves) { // found target
                return curMoves;
//                targetMinMovesCount = curMoves;
//                continue;
            }

            for (int[] move : moveTypes) {
                int nx = cx + move[0];
                int ny = cy + move[1];
                int newMoves = curMoves + 1;
                visitedKey = getVisitedKey(nx, ny);
                movesCountOnVisited = visited.get(visitedKey);
                if (movesCountOnVisited != null && newMoves >= movesCountOnVisited || newMoves > targetMinMovesCount) {
                    continue;
                }
//                System.out.println("added to q");
                q.add(new int[]{nx, ny, newMoves});
            }
        }
        if (targetMinMovesCount == Integer.MAX_VALUE) {
            return -1;
        }
        return targetMinMovesCount;
    }


    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """              
                    expected | x    | y 
                    1        | -2   | -1 
                    1        | -2   | 1 
                    1        | -1   | -2 
                    1        | -1   | 2 
                    1        | 1    | -2 
                    1        | 1    | 2 
                    1        | 2    | -1 
                    1        | 2    | 1 
                    4        | 5    | 5
                    81       | -99  | 142
                    110      | 217  | 47
                    """
    )
    public void test(int expected, int x, int y) {
        Assertions.assertEquals(expected, minKnightMoves(x, y));
    }
}
