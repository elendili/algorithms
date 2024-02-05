package misc;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * You are given a table of game results in a competition.
 * Each row contains  two teams names and the game score.
 * Your task is to determine which team won in each game
 * and print out the name of a team with the highest total number of wins (the competition winner)
 * <p>
 * <p>
 * String [][] results = {
 * {"Team A", "Team B", "1:3"},
 * {"Team A", "Team C", "4:3"},
 * {"Team C", "Team B", "0:2"},
 * {"Team C", "Team A", "4:5"},
 * {"Team B", "Team A", "4:2"},
 * {"Team B", "Team C", "4:10"},
 * };
 */

public class SDETinMLP {
    record Results(List<String> winners, String totalWinner) {

    }

    static Results findWinners(String[][] results) {
        Map<String, Integer> teamWinCounts = new HashMap<>();
        List<String> list = new ArrayList();
        for (String[] curRes : results) {
            String teamA = curRes[0];
            String teamB = curRes[1];
            String[] points = curRes[2].split(":");
            int a = Integer.parseInt(points[0]);
            int b = Integer.parseInt(points[1]);
            // assume someone win
            String localWinner = a > b ? teamA : teamB;
            list.add(localWinner);

            teamWinCounts.compute(localWinner, (k, v) -> {
                v = v == null ? 0 : v;
                v = v + 1;
                return v;
            });
        }
        Optional<Map.Entry<String, Integer>> totalWinner = teamWinCounts.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue));
        return new Results(list, totalWinner.get().getKey());
    }

    @Test
    public void test() {
        String[][] results = {
                {"Team A", "Team B", "1:3"},
                {"Team A", "Team C", "4:3"},
                {"Team C", "Team B", "0:2"},
                {"Team C", "Team A", "4:5"},
                {"Team B", "Team A", "4:2"},
                {"Team B", "Team C", "4:10"},
        };
        Results actual = findWinners(results);
        assertEquals("Team B", actual.totalWinner());
        assertEquals("[Team B, Team A, Team B, Team A, Team B, Team C]", actual.winners().toString());
    }
}
