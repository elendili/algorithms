package hackerrank.medium;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

/* https://www.hackerrank.com/challenges/ctci-recursive-staircase/problem
Davis has a number of staircases in his house and he likes to climb each staircase , , or  steps at a time. Being a very precocious child, he wonders how many ways there are to reach the top of the staircase.

Given the respective heights for each of the  staircases in his house, find and print the number of ways he can climb each staircase, module  on a new line.
*/
public class DavisStaircase {

    static int stepPerms(int n) {
        if (n < 2) {
            return 1;
        }
        long res = 2, n_1 = 2, n_2 = 1, n_3 = 1;
        for (int i = 0; i < n - 2; i++) {
            res = n_1 + n_2 + n_3;
            n_3 = n_2;
            n_2 = n_1;
            n_1 = res;
        }
        int out = (int) (res % 10000000007L);
        return out;


    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int s = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int sItr = 0; sItr < s; sItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int res = stepPerms(n);

            bufferedWriter.write(String.valueOf(res));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }

    @Test
    public void test() {
        Map<Integer, Integer> answers = new HashMap<>();
        answers.put(0, 1);
        answers.put(1, 1);
        answers.put(2, 2);
        answers.put(3, 4);
        answers.put(4, 7);
        answers.put(5, 13);
        answers.put(6, 24);
        answers.put(7, 44);
        answers.put(8, 81);
        assertTrue(answers.entrySet().stream()
                .allMatch(e -> stepPerms(e.getKey()) == e.getValue()));
    }
}
