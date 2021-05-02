package yandex.contest.May2_20201_shad;

import java.util.Scanner;

public class _1_A_plus_B {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            long a = scan.nextLong();
            long b = scan.nextLong();
            long out = a + b;
            System.out.println(out);
        }
    }
}
