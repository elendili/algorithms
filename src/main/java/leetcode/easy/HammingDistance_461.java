package leetcode.easy;

/**
 * https://leetcode.com/problems/hamming-distance/description/
 */
public class HammingDistance_461 {

    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int out = 0;
        while (xor != 0) {
            out += 1;
            // unset the rightmost bit of 'xor'
            xor = xor & (xor - 1);
        }
        return out;
    }

}
