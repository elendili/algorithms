package boardgames;

import org.junit.jupiter.api.Test;

import java.util.*;

import static java.lang.Math.abs;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;

/* place queens safely on board */
public class ChessQueens {
    class Coords {
        final int x, y;

        Coords(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coords coords = (Coords) o;
            return x == coords.x &&
                    y == coords.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    class CoordsOnTable {
        Set<Coords> set = new HashSet<>();
        Map<Integer, Set<Coords>> xToCoords = new HashMap<>();
        Map<Integer, Set<Coords>> yToCoords = new HashMap<>();
        final Integer width;
        final Integer height;

        CoordsOnTable(Integer width, Integer height) {
            this.width = width;
            this.height = height;
        }

        public boolean contains(Integer x, Integer y) {
            return set.contains(new Coords(x, y));
        }

        public void put(Integer x, Integer y) {
            assert x > -1 && x < width;
            assert y > -1 && y < width;
            Coords add = new Coords(x, y);
            set.add(add);
            xToCoords.compute(x, (k, v) -> {
                if (v == null) {
                    Set<Coords> o = new HashSet<>();
                    o.add(add);
                    return o;
                } else {
                    v.add(add);
                    return v;
                }
            });
            yToCoords.compute(y, (k, v) -> {
                if (v == null) {
                    Set<Coords> o = new HashSet<>();
                    o.add(add);
                    return o;
                } else {
                    v.add(add);
                    return v;
                }
            });
        }

        public void removeByY(Integer y) {
            Set<Coords> coords = yToCoords.remove(y);
            if (coords!=null){
                coords.forEach(e->{
                    set.remove(e);
                    xToCoords.get(e.x).remove(e);
                });
            }
        }

        public void removeByX(Integer x) {
            Set<Coords> coords = xToCoords.remove(x);
            if (coords!=null){
                coords.forEach(e->{
                    set.remove(e);
                    yToCoords.get(e.y).remove(e);
                });
            }
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
                    xToCoords.containsKey(x)
                            || yToCoords.containsKey(y)
                            || set.stream()
                            .anyMatch(e -> {
                                int _x = e.x;
                                int _y = e.y;
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


    List<CoordsOnTable> board(int width, int height) {
        // map x->y
        List<CoordsOnTable> positions = new ArrayList<>();

        CoordsOnTable coords = new CoordsOnTable(width, height);
        Set<Integer> yChecked = new HashSet<>();
        for (int y = 0; y < height; y++) {
            // save ys ?
            boolean xSet = false;
            for (int x = 0; x < width; x++) {
                if (coords.isSafe4Queen(x, y)) {
                    coords.put(x, y);
                    xSet = true;
                    break;
                }
            }
            if (!xSet) {
                y--;
                coords.removeByY(y);
                while (yChecked.contains(y)) {

                    yChecked.remove(y);
                    y--;
                }

                yChecked.add(y);
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
        CoordsOnTable c = new CoordsOnTable(5, 5);
        c.put(1, 1);
        System.out.println(c);
    }
}
