package yandex.contest.old;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2_sequences {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String x = r.readLine();// forget
//            System.err.println("count: " + x);
            long curCount = 0;
            long countMax = 0;
            while ((x = r.readLine()) != null && !x.isEmpty()) {
//                System.err.println(x);
                if (!x.equals("1")) {
                    curCount = 0;
                } else {
                    curCount += 1;
                }
                countMax = Math.max(countMax, curCount);
            }
            System.out.println(countMax);
        } catch (IOException e) {
//            System.err.println(e.getMessage());
        }
    }

}
