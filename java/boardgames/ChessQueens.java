package boardgames;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;
import static org.junit.Assert.assertEquals;

/* place queens safely on board */
public class ChessQueens {
    class Coords {
        Map<Integer, Integer> xToY = new HashMap<>();
        Map<Integer, Integer> yToX = new HashMap<>();
        final Integer width;
        final Integer height;

        Coords(Integer width, Integer height) {
            this.width = width;
            this.height = height;
        }

        public boolean contains(Integer x, Integer y) {
            return x.equals(yToX.get(y));
        }

        public void put(Integer x, Integer y) {
            assert x > -1 && x < width;
            assert y > -1 && y < width;
            this.xToY.put(x, y);
            this.yToX.put(y, x);
        }

        public Integer removeByY(Integer y) {
            Integer x = this.yToX.remove(y);
            this.xToY.remove(x);
            return x;
        }

        public Integer removeByX(Integer x) {
            Integer y = this.xToY.remove(x);
            this.yToX.remove(y);
            return y;
        }

        boolean isSafe4Queen(final Integer x, final Integer y) {
            /*
            xDistance = q1.x - q2.x
            yDistance = q1.y - q2.y
            slope = xDistance / yDistance
            diagonal is when slope = -1 or 1
            So, we can check equality of x delta and y delta
            */
            boolean hasOtherQueen =
                    xToY.containsKey(x)
                            || yToX.containsKey(y)
                            || xToY.entrySet().stream()
                            .anyMatch(e -> {
                                int _x = e.getKey();
                                int _y = e.getValue();
                                return abs(x - _x) == abs(y - _y);
                            });
            return !hasOtherQueen;
        }

        @Override
        public String toString() {
            String out = range(0, height).boxed().flatMap(y ->
                    range(0, width).boxed().map(x -> asList(x, y))
            ).map(a -> {
                        int x = a.get(0);
                        int y = a.get(1);
                        String o = contains(x, y) ? "Q" : ".";
                        return x == width - 1 ? o + "\n" : o + "";
                    }
            ).collect(joining());
            return out;
        }
    }


    List<Coords> board(int width, int height) {
        // map x->y
        List<Coords> positions = new ArrayList<>();

        Coords coords = new Coords(width, height);
        coords.put(0, 0);
        Integer checkedX = -1;
        for (int y = 0; y < height; y++) {
            boolean xSet = false;

            for (int x = checkedX + 1; x < width; x++) {
                if (coords.isSafe4Queen(x, y)) {
                    coords.put(x, y);
                    xSet = true;
                    checkedX = -1;
                    break;
                }
            }
            if (!xSet) {
                checkedX = coords.removeByY(y);
                y--;
            }

        }

        return positions;
    }

    @Test
    public void test() {
        assertEquals(0, board(3, 3).size());
        assertEquals(2, board(4, 4).size());
        assertEquals(92, board(8, 8).size());
    }

    @Test
    public void testToString() {
        Coords c = new Coords(5, 5);
        c.put(1, 1);
        System.out.println(c);
    }
}
