package leetcode.medium;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/prime-subtraction-operation
 */
public class PrimeSubtractionOperation_2601 {

    private static final int[] primes = new int[]{
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997
    };

    public boolean primeSubOperation(int[] nums) {
        if (nums.length < 2) {
            return true;
        }
        for (int i = nums.length - 2; i > -1; i--) {
            int diff = nums[i] - nums[i + 1];
            if (diff >= 0) {    // [i] bigger than [i+1]
                // subtract prime from [i]
                // prime should be strictly less than [i] and more than diff

                int primeIndex = Arrays.binarySearch(primes, diff + 1);
                int absPrimeIndex = primeIndex < 0 ? Math.abs(primeIndex) - 1 : primeIndex;
                if(absPrimeIndex>=primes.length){
                    return false;
                }
                int chosenPrime = primes[absPrimeIndex];
//                System.out.println(chosenPrime);
                if (chosenPrime >= nums[i]) {
                    return false;
                } else {
                    nums[i] -= chosenPrime;
                }
            }
        }
        return true;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals(true, primeSubOperation(new int[]{4, 9, 6, 10}));
        assertEquals(true, primeSubOperation(new int[]{6, 8, 11, 12}));
        assertEquals(false, primeSubOperation(new int[]{5, 8, 3}));
    }
    @org.junit.jupiter.api.Test
    public void test2() {
        assertEquals(true, primeSubOperation(new int[]{991, 997}));
        assertEquals(true, primeSubOperation(new int[]{997, 991}));
        assertEquals(true, primeSubOperation(new int[]{992, 991}));
    }
    @org.junit.jupiter.api.Test
    public void test1000() {
        assertEquals(false, primeSubOperation(new int[]{1000, 0}));
        assertEquals(false, primeSubOperation(new int[]{1000, 0, 1000}));
        assertEquals(true, primeSubOperation(new int[]{998, 2, 1000}));
    }
    @org.junit.jupiter.api.Test
    public void test3() {
        assertEquals(false, primeSubOperation(new int[]{2, 1}));
        assertEquals(false, primeSubOperation(new int[]{3, 1}));
        assertEquals(false, primeSubOperation(new int[]{4, 1}));
        assertEquals(false, primeSubOperation(new int[]{5, 1}));
        assertEquals(false, primeSubOperation(new int[]{5, 2}));
        assertEquals(true, primeSubOperation(new int[]{5, 3}));
    }
}
