package yandex.contest.old;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class _1 {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        Set<Character> set = new HashSet<>();
        int x;
        while ((x = r.read()) != -1 && x != '\n') {
            set.add((char) x);
        }
        int count = 0;
        while ((x = r.read()) != -1 && x != '\n') {
            if (set.contains((char) x)) {
                count += 1;
            }
        }
        System.out.println(count);
    }
}
