package leetcode.medium;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeaderboardDesign {

    class Leaderboard {
        Map<Integer, int[]> playerScores = new HashMap<>();
        List<int[]> scores = new ArrayList<>();
        boolean sorted = false;

        public Leaderboard() {
            // array or map to store playerId and score
        }

        public void addScore(int playerId, int score) {
            int[] val = playerScores.get(playerId);
            if (val == null) {
                val = new int[]{0};
                playerScores.put(playerId, val);
                scores.add(val);
            }
            val[0] += score;
            sorted = false;
        }

        public int top(int K) {
            if (!sorted) {
                scores.sort(Comparator.<int[]>comparingInt(a -> a[0]).reversed());
                sorted = true;
            }
            K = Math.min(K, scores.size());
            int total = 0;
            for (int i = 0; i < K; i++) {
                total += scores.get(i)[0];
            }
            return total;
        }

        public void reset(int playerId) {
            int[] val = playerScores.remove(playerId);
            if (val != null) {
                scores.remove(val);
            }
        }
    }

    @org.junit.jupiter.api.Test
    public void test() {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.addScore(1, 73);   // leaderboard = [[1,73]];
        leaderboard.addScore(2, 56);   // leaderboard = [[1,73],[2,56]];
        leaderboard.addScore(3, 39);   // leaderboard = [[1,73,[2,56],[3,39]];
        leaderboard.addScore(4, 51);   //
        leaderboard.addScore(5, 4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
        int actual = leaderboard.top(1);            // returns 73;
        int expected = 73;
        assertEquals(expected, actual);
        leaderboard.reset(1);
        leaderboard.reset(2);
        leaderboard.addScore(2, 51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
        actual = leaderboard.top(3);             // returns 141 = 51 + 51 + 39;
        expected = 141;
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        List<String> commands = List.of("addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "addScore", "top", "reset", "addScore", "addScore", "reset", "top", "addScore", "reset", "top", "reset", "addScore", "top", "top", "top", "reset", "addScore", "addScore", "addScore", "addScore", "reset", "addScore", "top", "addScore", "reset", "addScore", "top", "top", "reset", "top", "top", "addScore", "top", "reset", "addScore", "top", "top", "reset", "addScore", "addScore", "reset", "reset", "reset", "addScore", "top", "reset", "reset", "top", "addScore", "top");
        List<int[]> values = List.of(new int[]{1, 37}, new int[]{2, 8}, new int[]{3, 79}, new int[]{4, 30}, new int[]{5, 33}, new int[]{6, 12}, new int[]{7, 83}, new int[]{8, 79}, new int[]{9, 60}, new int[]{10, 95}, new int[]{11, 11}, new int[]{12, 51}, new int[]{13, 26}, new int[]{14, 84}, new int[]{15, 33}, new int[]{16, 6}, new int[]{17, 89}, new int[]{18, 91}, new int[]{19, 30}, new int[]{20, 54}, new int[]{21, 24}, new int[]{22, 96}, new int[]{23, 78}, new int[]{24, 37}, new int[]{25, 27}, new int[]{26, 99}, new int[]{27, 43}, new int[]{28, 81}, new int[]{29, 40}, new int[]{30, 49}, new int[]{31, 22}, new int[]{32, 79}, new int[]{33, 15}, new int[]{34, 36}, new int[]{35, 63}, new int[]{36, 18}, new int[]{37, 84}, new int[]{38, 10}, new int[]{39, 15}, new int[]{40, 81}, new int[]{41, 30}, new int[]{42, 99}, new int[]{43, 40}, new int[]{44, 73}, new int[]{45, 34}, new int[]{46, 66}, new int[]{47, 98}, new int[]{48, 51}, new int[]{49, 29}, new int[]{50, 89}, new int[]{47, 7}, new int[]{9}, new int[]{1}, new int[]{9, 59}, new int[]{44, 64}, new int[]{2}, new int[]{43}, new int[]{33, 53}, new int[]{3}, new int[]{19}, new int[]{4}, new int[]{8, 21}, new int[]{39}, new int[]{15}, new int[]{8}, new int[]{5}, new int[]{1, 18}, new int[]{33, 13}, new int[]{18, 8}, new int[]{31, 57}, new int[]{1}, new int[]{23, 69}, new int[]{1}, new int[]{46, 60}, new int[]{6}, new int[]{8, 60}, new int[]{22}, new int[]{20}, new int[]{7}, new int[]{42}, new int[]{41}, new int[]{13, 67}, new int[]{15}, new int[]{8}, new int[]{50, 3}, new int[]{31}, new int[]{10}, new int[]{9}, new int[]{22, 82}, new int[]{15, 9}, new int[]{10}, new int[]{11}, new int[]{12}, new int[]{41, 48}, new int[]{18}, new int[]{13}, new int[]{14}, new int[]{34}, new int[]{20, 48}, new int[]{17});

        List<Integer> expected =
                Arrays.asList(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 847, null, null, null, null, 2628, null, null, 1736, null, null, 2553, 1451, 850, null, null, null, null, null, null, null, 147, null, null, null, 2175, 2033, null, 2780, 2770, null, 1637, null, null, 2463, 1122, null, null, null, null, null, null, null, 1833, null, null, 2366, null, 1758);


        assertEquals(commands.size(), values.size());
        assertEquals(commands.size(), expected.size());
        Leaderboard leaderboard = new Leaderboard();
        for (int i = 0; i < commands.size(); i++) {
            String command = commands.get(i);
            int[] val = values.get(i);
            Integer exp = expected.get(i);
            switch (command) {
                case "addScore" -> leaderboard.addScore(val[0], val[1]);
                case "top" -> assertEquals(exp, leaderboard.top(val[0]));
                case "reset" -> leaderboard.reset(val[0]);
            }
        }
    }
}
