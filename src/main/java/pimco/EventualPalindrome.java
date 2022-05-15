package pimco;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class EventualPalindrome {

    static long findEventualPalindrome(long x) {
        return findEventualPalindrome(x, 20);
    }

    static long findEventualPalindrome(long x, int depth) {
        if (depth < 1) {
            System.out.println("eventual palindrome cannot be found");
            return Long.MIN_VALUE;
        }
        long n = reverseAndAdd(x);
        if (isPalindrome(n)) {
            return n;
        } else {
            return findEventualPalindrome(n, --depth);
        }
    }

    public static long reverseAndAdd(long x) {
        long[] straight = toArray(x);
        int n = straight.length;
        long[] backwardArray = new long[n];
        for (int i = 0; i < n; i++) {
            backwardArray[i] = straight[n - 1 - i];
        }
        return x + fromArray(backwardArray);
    }

    private static long[] toArray(long x) {
        int size = (int) Math.log10(x) + 1;
        long[] out = new long[size];
        long left = x;
        int i = size - 1;
        while (i > -1) {
            long next = left / 10;
            long digit = left - next * 10; // 123 - 120
            out[i--] = digit;
            left = next;
        }
        return out;
    }

    private static long fromArray(long[] a) {
        int n = a.length;
        int out = 0;
        for (int i = 0; i < n; i++) {
            out += a[i] * Math.pow(10, n - i - 1);
        }
        return out;
    }

    public static boolean isPalindrome(long x) {
        long[] a = toArray(x);
        int n = a.length;
        for (int i = 0; i < n / 2; i++) {
            if (a[i] != a[n - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public static void main(String[] args) {
        System.out.println(Arrays.toString(toArray(123)));
        System.out.println(Arrays.toString(toArray(199)));
        System.out.println("99=>" + fromArray(toArray(99)));
        System.out.println();
        System.out.println(reverseAndAdd(32));
        System.out.println("here: " + reverseAndAdd(150)); // 150 + 51 = 201
        System.out.println(isPalindrome(32)); // false
        System.out.println(isPalindrome(151)); // true
        System.out.println("---------------");
        for (int i = 1; i <= 200; i++) {
            System.out.println(findEventualPalindrome(i));
        }
    }
}