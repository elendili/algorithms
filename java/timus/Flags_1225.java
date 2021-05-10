package timus;

import org.junit.jupiter.api.Test;

import java.io.*;

import static timus.Checker.check;

// JUDGE_ID 316594SC
/*
n=1 => 2
W, R
n=2 => 2
WR, RW

n=3 => 4
WRW, RWR, RBW, WBR

n=4 => 6
WRWR, RWRW,
RWBR, WRBW,
RBWR, WBRW,

n=5 => 10
WRWRW, RWRWR
+ 1 B
WBRWR, WRBWR, WRWBR, RBWRW, RWBRW, RWRBW
+ 2 B
WBRBW, RBWBR

n=6 => 16
WRWRWR, WRWRWR,
WBRWRW, WRBWRW, WRWBRW, WRWRBW
RBWRWR, RWBRWR, RWRBWR, RWRWBR
WBRBWR, WRBWBR, WBRWBR
RBWBRW, RWBRBW, RBWRBW

Seems like fibonacci f(n) = f(n-1) + f(n-2)
 */
public class Flags_1225 {
    public static void main(String[] args) {
        solution(System.in, System.out);
    }

    static void solution(InputStream is, PrintStream ps) {
        try {
            StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(is)));
            st.nextToken();
            int stripes = (int) st.nval;
            long out = 2;
            if (stripes > 2) {
                long prev2 = 2;
                long prev1 = 2;
                for (int i = 2; i < stripes; i++) {
                    out = prev1 + prev2;
                    prev2 = prev1;
                    prev1 = out;
                }
            }
            ps.println(out);
            ps.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        check(Flags_1225::solution, "1", "2");
        check(Flags_1225::solution, "2", "2");
    }

    @Test
    public void test2() {
        check(Flags_1225::solution, "3", "4");
        check(Flags_1225::solution, "4", "6");
        check(Flags_1225::solution, "5", "10");
        check(Flags_1225::solution, "6", "16");
    }

    @Test
    public void testBig() {
        check(Flags_1225::solution, "43", "866988874");
        check(Flags_1225::solution, "44", "1402817466");
        check(Flags_1225::solution, "45", "2269806340");
    }
}

