package yandex.contest;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class _3_find_unique {
    private static byte[] buffer = new byte[50];

    private static byte[] readTillNewLine(InputStream is) throws IOException {
        byte x;
        int i = 0;
        while ((x = (byte) is.read()) != '\n' && x != -1) {
            buffer[i] = x;
            i++;
        }
        byte[] out = new byte[i];
        System.arraycopy(buffer, 0, out, 0, i);
        return out;
    }

    public static void process(final InputStream in,
                               final PrintStream out) {
        try (final BufferedInputStream bis = new BufferedInputStream(in, 1 << 15)
        ) {
            readTillNewLine(bis);
            byte[] x;
            byte[] oldX = null;
            int counter = 0;
            while ((x = readTillNewLine(bis)).length != 0) {
                if (oldX != null && !Arrays.equals(oldX, x)) {
                    out.write(oldX);
                    out.write('\n');
                }
                oldX = x;
                counter++;
            }
            if (oldX!=null){
                out.write(oldX);
                out.write('\n');
                out.flush();
            }
        } catch (IOException e) {
//            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        process(System.in, System.out);
    }

}
