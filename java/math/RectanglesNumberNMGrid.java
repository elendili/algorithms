package math;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*
Let us derive a formula for number of rectangles.

If the grid is 1×1, there is 1 rectangle.
If the grid is 2×1, there will be 2 + 1 = 3 rectangles
If it grid is 3×1, there will be 3 + 2 + 1 = 6 rectangles.
we can say that for N*1 there will be N + (N-1) + (n-2) … + 1 = (N)(N+1)/2 rectangles

If we add one more column to N×1, firstly we will have as many rectangles in the 2nd column as the first,
and then we have that same number of 2×M rectangles.
So N×2 = 3 (N)(N+1)/2

After deducing this we can say
For N*M we’ll have (M)(M+1)/2 (N)(N+1)/2 = M(M+1)(N)(N+1)/4

So the formula for total rectangles will be M(M+1)(N)(N+1)/4
 */
public class RectanglesNumberNMGrid {
    public int rectCount(int n, int m) {
        return (m * n * (n + 1) * (m + 1)) / 4;
    }

    @Test
    public void test() {
        // (n(n+1)/2)^2
        assertEquals(1, rectCount(1, 1));   // 1^2
        assertEquals(9, rectCount(2, 2));   // 3^2
        assertEquals(36, rectCount(3, 3));  // 6^2
        assertEquals(100, rectCount(4, 4)); // 10^2
        assertEquals(225, rectCount(5, 5)); // 15^2
        assertEquals(441, rectCount(6, 6)); // 21^2
        assertEquals(784, rectCount(7, 7)); // 28^2

        // for 1 width  rect: https://www.mathsisfun.com/algebra/triangular-numbers.html
        //  n + (n-1) + (n-2) + ... 1 = n(n+1)/2
        assertEquals(1, rectCount(1, 1));   // 1^2
        assertEquals(3, rectCount(2, 1));   // 3^2
        assertEquals(6, rectCount(3, 1));  // 6^2
        assertEquals(10, rectCount(4, 1)); // 10^2
        assertEquals(15, rectCount(5, 1)); // 15^2
        assertEquals(21, rectCount(6, 1)); // 21^2
        assertEquals(28, rectCount(7, 1)); // 28^2

        //  n + (n-1) + (n-2) + ... 1 = n(n+1)/2
        assertEquals(1, rectCount(1, 1));   // 1^2
        assertEquals(3, rectCount(2, 1));   // 3^2
        assertEquals(6, rectCount(3, 1));  // 6^2
        assertEquals(10, rectCount(4, 1)); // 10^2
        assertEquals(15, rectCount(5, 1)); // 15^2
        assertEquals(21, rectCount(6, 1)); // 21^2
        assertEquals(28, rectCount(7, 1)); // 28^2
    }
}
