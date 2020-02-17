package yandex.contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _4_parentheses {

    public static void generateInBytes(int n) {
        generateInBytes(new byte[0], (byte) 0, (byte) 0, (byte) n);
    }

    public static void generateInBytes(byte[] cur, byte o, byte c, byte n) {
        if (o >= n && c >= n) {
            if (cur.length > 0) {
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
            byte[] newA = new byte[newIndex + 1];
            System.arraycopy(cur, 0, newA, 0, newIndex);

            if (o < n) {
                newA[newIndex] = '(';
                byte no = (byte) (o + 1);
                generateInBytes(newA, no, c, n);
            }
            if (c < o) {
                newA[newIndex] = ')';
                byte nc = (byte) (c + 1);
                generateInBytes(newA, o, nc, n);
            }
        }
    }

    public static void generateInIndexes(int n) {
        generateInIndexes(new byte[0], (byte) 0, (byte) 0, (byte) n);
    }

    public static void generateInIndexes(byte[] cur, byte o, byte c, byte n) {
        if (o >= n && c >= n) {
            if (cur.length > 0) {
                try {
                    byte[] out = new byte[n * 2];
                    for (int i = 0; i < cur.length; i++) {
                        byte openIndex = cur[i];
                        out[openIndex] = '(';
                    }
                    for (int i = 0; i < out.length; i++) {
                        if (out[i] == 0) {
                            out[i] = ')';
                        }
                    }
                    System.out.write(out);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println();
            System.out.flush();
        } else {
            if (o < n) {
                byte[] newA = new byte[cur.length + 1];
                System.arraycopy(cur, 0, newA, 0, cur.length);

                byte no = (byte) (o + 1);
                newA[newA.length - 1] = (byte)(o+c);
                generateInIndexes(newA, no, c, n);
            }
            if (c < o) {
                byte nc = (byte) (c + 1);
                generateInIndexes(cur, o, nc, n);
            }
        }
    }

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(r.readLine());
            generateInIndexes(n);
        } catch (IOException ignored) {
        }
    }

}
