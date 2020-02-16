package yandex.contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _4_parentheses {

    public static void generateInBytes(int n) {
        generateInBytes(new byte[0], 0, 0, n);
    }

    public static void generateInBytes(byte[] cur, int o, int c, int n) {
        if (o >= n && c >= n) {
            if(cur.length>0){
                try {
                    System.out.write(cur);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println();
            System.out.flush();
        } else {

            int newIndex = c + o;
            byte[] newA = new byte[newIndex+1];
            System.arraycopy(cur, 0, newA, 0, newIndex);

            if (o < n) {
                newA[newIndex] = '(';
                generateInBytes(newA, o + 1, c, n);
            }
            if (c < o) {
                newA[newIndex] = ')';
                generateInBytes(newA, o, c + 1, n);
            }
        }
    }

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(r.readLine());
            generateInBytes(n);
        } catch (IOException ignored) {
        }
    }

}
