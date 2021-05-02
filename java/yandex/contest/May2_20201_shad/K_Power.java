package yandex.contest.May2_20201_shad;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class K_Power {
    public static void main(String[] args) {
        process(System.in, System.out);
    }

    int lastIndex = -1;
    int _1_MinIndex = -1;
    int _1_MinValue = Integer.MAX_VALUE;
    int _2_MinIndex = -1;
    int _2_MinValue = Integer.MAX_VALUE;
    int _3_MinIndex = -1;
    int _3_MinValue = Integer.MAX_VALUE;

    int addAndGetMinPower(int a) {
        /* power = min of left + min of right
         track min and their correspondent indexes
         value->index

when size < 3 return 0
add => set index and check agains existent 2 minimums
        */
        lastIndex++;
        if (a < _1_MinValue) {
            _3_MinValue = _2_MinValue;
            _3_MinIndex = _2_MinIndex;
            _2_MinValue = _1_MinValue;
            _2_MinIndex = _1_MinIndex;
            _1_MinValue = a;
            _1_MinIndex = lastIndex;
        } else if (a < _2_MinValue) {
            _3_MinValue = _2_MinValue;
            _3_MinIndex = _2_MinIndex;
            _2_MinValue = a;
            _2_MinIndex = lastIndex;
        } else if (a <= _3_MinValue) {
            _3_MinValue = a;
            _3_MinIndex = lastIndex;
        }
        int out = 0;
        if (_3_MinIndex > -1) {
            if (Math.abs(_1_MinIndex - _2_MinIndex) > 1
            ) {
                out = _1_MinValue + _2_MinValue;
            } else if (Math.abs(_1_MinIndex - _3_MinIndex) > 1
            ) {
                out = _1_MinValue + _3_MinValue;
            } else if (Math.abs(_2_MinIndex - _3_MinIndex) > 1
            ) {
                out = _2_MinValue + _3_MinValue;
            }
        }
        return out;
    }

    public static void process(final InputStream in,
                               final PrintStream out
    ) {
        K_Power kp = new K_Power();
        try (BufferedReader r = new BufferedReader(new InputStreamReader(in))) {
            r.readLine(); // ignore
            // stream of numbers
            StringBuilder sb = new StringBuilder(10);
            while (true) {
                int c = r.read();
                if (!(c == '-' || (c >= '0' && c <= '9'))) {
                    String s = sb.toString();
                    if (!s.isEmpty()) {
                        int n = Integer.parseInt(s);
                        int toOut = kp.addAndGetMinPower(n);
                        out.print(toOut + " ");
                    }
                    if (c == -1 || c == '\n') {
                        break;
                    }
                    sb.setLength(0);
                } else {
                    sb.append((char) c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test() {
        check("0 0 6 ",
                "3\n5 2 1");
    }

    @Test
    public void testE() {
        check("", " ");
        check("", " \n ");
        check("0 ", "1 \n 1\n \n");
    }

    @Test
    public void test2() {
        check("0 0 5 5 ",
                "4\n3 2 2 3");

        check("0 0 10 8 6 4 ",
                "6\n6 5 4 3 2 1");
    }

    @Test
    public void test3() {
        check("0 0 0 0 ",
                "4\n0 0 0 0 ");
        check("0 0 2 2 ",
                "4\n1 1 1 1");
        check("0 0 4 4 4 ",
                "4\n1 2 3 4 5");
        check("0 0 8 6 4 ",
                "4\n5 4 3 2 1");
        check("0 0 8 6 4 ",
                "4\n5 4 3 2 1");
        check("0 0 10 -10 -10 -10 -10 ",
                "4\n5 -5 5 -5 5 0 5");
    }

    @Test
    public void test4() {
        int max = Integer.MAX_VALUE / 2;
        int min = Integer.MIN_VALUE / 2;
        check("0 0 " + min * 2 + " " + min * 2 + " ",
                "4\n" + min + " " + min + " " + min + " " + min);
        check("0 0 " + max * 2 + " " + max * 2 + " ",
                "4\n" + max + " " + max + " " + max + " " + max);

        check("0 0 " + min * 2 + " " + min * 2 + " ",
                "4\n" + min + " " + max + " " + min + " " + max);
        check("0 0 " + max * 2 + " " + min * 2 + " ",
                "4\n" + max + " " + min + " " + max + " " + min);
    }

    @Test
    public void test5() {
        check("0 0 4 2 2 2 2 2 2 ",
                "4\n1 2 3 1 2 3 1 2 3");
    }

    @Test
    public void test6() {
        check("0 0 4 3 1 0 ",
                "4\n3 2 1 1 0 -1");
        check("0 0 2 2 2 0 ",
                "4\n1 1 1 1 1 -1");
        check("0 0 0 0 0 -2 ",
                "4\n-1 1 1 1 1 -1");
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
