package leetcode.medium;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AsteroidCollision {

    static <T> int getLastIndex(List<T> list) {
        return list.size() - 1;
    }

    public int[] asteroidCollision(int[] asteroids) {

        List<Integer> outList = new ArrayList<>(asteroids.length);
        outList.add(asteroids[0]);
        for (int i = 1; i < asteroids.length; i++) {
            int newA = asteroids[i];
            boolean addNew = true;
            int last;
            int sizeNew = Math.abs(newA);
            while (addNew && newA < 0 && (!outList.isEmpty() && (last = outList.get(getLastIndex(outList))) > 0)) {
                int sizeLast = Math.abs(last);
                if (sizeNew >= sizeLast) {
                    outList.remove(getLastIndex(outList));
                    if (sizeNew == sizeLast) {
                        addNew = false;
                    }
                } else {
                    addNew = false;
                }
            }

            if (addNew) {
                outList.add(newA);
            }
        }

        int[] out = outList.stream().mapToInt(i -> i).toArray();
        return out;
    }

    @Test
    public void test() {
        assertEquals("[1]", Arrays.toString(asteroidCollision(new int[]{1})));
        assertEquals("[1, 2]", Arrays.toString(asteroidCollision(new int[]{1, 2})));
        assertEquals("[2, 1]", Arrays.toString(asteroidCollision(new int[]{2, 1})));
    }

    @Test
    public void test2() {
        assertEquals("[5, 10]", Arrays.toString(asteroidCollision(new int[]{5, 10, -5})));
    }

    @Test
    public void test3() {
        assertEquals("[]", Arrays.toString(asteroidCollision(new int[]{8, -8})));
        assertEquals("[]", Arrays.toString(asteroidCollision(new int[]{8, 8, -8, -8})));
        assertEquals("[2]", Arrays.toString(asteroidCollision(new int[]{2, 8, -8, -1})));
        assertEquals("[-3]", Arrays.toString(asteroidCollision(new int[]{2, 8, -8, -3})));
        assertEquals("[10]", Arrays.toString(asteroidCollision(new int[]{10, 2, -5})));
        assertEquals("[1, 2, 3]", Arrays.toString(asteroidCollision(new int[]{1, 2, 3})));
        assertEquals("[-1, -2, -3]", Arrays.toString(asteroidCollision(new int[]{-1, -2, -3})));
        assertEquals("[]", Arrays.toString(asteroidCollision(new int[]{3, 2, 1, -1, -2, -3})));
        assertEquals("[-2, -1]", Arrays.toString(asteroidCollision(new int[]{3, 2, 1, -3, -2, -1})));
        assertEquals("[1, 2]", Arrays.toString(asteroidCollision(new int[]{1, 2, 3, -1, -2, -3})));
    }

}
