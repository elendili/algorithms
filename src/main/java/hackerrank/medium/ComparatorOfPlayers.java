package hackerrank.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * https://www.hackerrank.com/challenges/ctci-comparator-sorting/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=sorting
 */
public class ComparatorOfPlayers {
    class Player {
        String name;
        int score;

        Player(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public String toString() {
            return name + ":" + score;
        }
    }

    class Checker implements Comparator<Player> {
        final Comparator<Player> comp = Comparator.<Player>comparingInt(p -> p.score)
                .reversed()
                .thenComparing(p -> p.name);

        // complete this method
        public int compare(Player a, Player b) {
            return comp.compare(a, b);
        }
    }

    @Test
    public void test() {
        List<Player> list = asList(
                new Player("Smith", 20),
                new Player("Jones", 15),
                new Player("Jones", 20)
        );
        Assertions.assertEquals("[Jones:20, Smith:20, Jones:15]",
                list.stream().sorted(new Checker()).collect(Collectors.toList()).toString());
    }
}
