package yandex.contest.May2_20201_shad;

import org.junit.jupiter.api.Test;

public class I_Dices {

    @Test
    public void test() {
        int countUnderCondition = 0;
        int total = 0;
        for (int a = 1; a < 10; a++) {
            for (int b = 1; b < 10; b++) {
                for (int c = 1; c < 10; c++) {
                    total++;
                    if ((a + b + c) <= 23
                            && (a == 8 || b == 8 || c == 8)
                    ) {
                        countUnderCondition++;
                    }
                }
            }
        }
        System.out.println(total);
        System.out.println(countUnderCondition);
        System.out.println((double) countUnderCondition / total);
    }
}
