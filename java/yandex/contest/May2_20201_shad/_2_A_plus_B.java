package yandex.contest.May2_20201_shad;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class _2_A_plus_B {
    public static void main(String[] args) throws IOException {
        try (FileReader r = new FileReader("input.txt");
             Scanner scan = new Scanner(r)) {
            long a = scan.nextLong();
            long b = scan.nextLong();
            long out = a + b;
            Files.write(Paths.get("output.txt"), ("" + out).getBytes());
        }
    }
}
