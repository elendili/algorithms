package timus;

import org.junit.jupiter.api.Test;

import java.io.*;

import static timus.Checker.check;

// JUDGE_ID 316594SC
public class TwoGangsters_1409 {
    public static void main(String[] args) throws IOException {
        solution(System.in, System.out);
    }

    static void solution(InputStream is, PrintStream ps) {
        try {
            StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(is)));
            st.nextToken();
            int a = (int) st.nval;
            st.nextToken();
            int b = (int) st.nval;
            int outA = b - 1;
            int outB = a - 1;
            ps.println(outA + " " + outB);
            ps.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        check(TwoGangsters_1409::solution, "4 7", "6 3");
    }
}

