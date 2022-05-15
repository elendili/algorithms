package yandex.contest.May2_20201_shad;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class L_BishopPlacements {
    public static void main(String[] args) {
        process(System.in, System.out);
    }

    public static void process(final InputStream in,
                               final PrintStream out
    ) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(in))) {
            int N = Integer.parseInt(r.readLine());
            long safePlacements = bishop_placements(N, 3);
            long pc = possibleCombinations(N);
            long toOut = pc - safePlacements;
            out.print(toOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static long possibleCombinations(int N) {
        //            9!/((9-3)!*(3!)) = 84
        //        3 => 84;
        int size = N * N;
        long a = factorial(size, size - 3);
        long b = 6; // 3!
        return a / b;
    }

    static long factorial(int top, int bottomNotInclusive) {
        long out = 1;
        for (int i = bottomNotInclusive + 1; i <= top; i++) {
            out *= i;
        }
        return out;
    }

    static long bishop_placements(int N, int K) {
        if (K > (2 * N - 1)) {
            return 0;
        }
        // memory
        long[][] D = new long[N * 2][K + 1];
        for (int i = 0; i < N * 2; ++i) {
            D[i][0] = 1;
        }
        D[1][1] = 1;
        for (int i = 2; i < N * 2; ++i) {
            for (int j = 1; j <= K; ++j) {
                D[i][j] = D[i - 2][j] + D[i - 2][j - 1] * (squares(i) - j + 1);
            }
        }
        long ans = 0;
        for (int i = 0; i <= K; ++i) {
            long toAdd = D[N * 2 - 1][i] * D[N * 2 - 2][K - i];
            ans += toAdd;
        }
        return ans;
    }

    static int squares(int i) {
        if (i % 2 == 1)
            return i / 4 * 2 + 1;
        else
            return (i - 1) / 4 * 2 + 2;
    }

    @Test
    public void test1000() {
        check("664918332334000", "1000");
    }

    @Test
    public void test1() {
        check("0", "1");
    }

    @Test
    public void test2() {
        check("4", "2");
    }

    @Test
    public void test3() {
        check("58", "3");
    }

    @Test
    public void test4() {
        check("328", "4");
    }

    public void check(String expected, String in) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);
        process(
                new ByteArrayInputStream(in.getBytes()),
                out
        );
        Assertions.assertEquals(expected, baos.toString());
    }
}
