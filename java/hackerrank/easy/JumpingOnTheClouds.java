package hackerrank.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/jumping-on-the-clouds/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup
 */
public class JumpingOnTheClouds {
    int jumpingOnClouds(int[] a) {
        int out = 0;
        int i = 0;
        while (i < a.length - 2) {
            if (a[i + 2] == 0) {
                i += 2;
            } else {
                i += 1;
            }
            out += 1;
        }
        if (i != a.length - 1) {
            out += 1;
        }
        return out;
    }

    @Test
    public void test() {
        Assertions.assertEquals(1, jumpingOnClouds(new int[]{0, 0}));
        Assertions.assertEquals(1, jumpingOnClouds(new int[]{0, 1, 0}));
        Assertions.assertEquals(1, jumpingOnClouds(new int[]{0, 0, 0}));
        Assertions.assertEquals(2, jumpingOnClouds(new int[]{0, 0, 0, 0}));
        Assertions.assertEquals(3, jumpingOnClouds(new int[]{0, 0, 0, 0, 1, 0}));
        Assertions.assertEquals(3, jumpingOnClouds(new int[]{0, 1, 0, 0, 0, 1, 0}));
        Assertions.assertEquals(4, jumpingOnClouds(new int[]{0, 0, 1, 0, 0, 1, 0}));
        Assertions.assertEquals(5, jumpingOnClouds(new int[]{0, 1, 0, 0, 1, 0, 0, 1, 0}));
    }
}
