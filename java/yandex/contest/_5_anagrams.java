package yandex.contest;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;

public class _5_anagrams {
    //ASCII 97:a, 122:z
    static int[] getFrequencies(BufferedInputStream bis) {
        int[] out = new int[26];
        try {
            int x,i;
            while ((x = bis.read()) != -1 && x != '\n') {
                i=x-97;
                out[i] += 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    public static void main(String[] args) {
        try (final BufferedInputStream bis = new BufferedInputStream(System.in, 1 << 15)) {
            int[] freq1 = getFrequencies(bis);
            int[] freq2 = getFrequencies(bis);
            if (Arrays.equals(freq1, freq2)) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
