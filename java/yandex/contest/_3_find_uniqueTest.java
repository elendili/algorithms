package yandex.contest;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Random;

import static hackerrank.TestHelper.assertStdOutAfterStdInput;
import static hackerrank.TestHelper.stubStdInAndStdOut;

public class _3_find_uniqueTest {

    @Test
    public void tst() {
        assertStdOutAfterStdInput("5\n2\n4\n8\n8\n8\n",
                "2\n4\n8\n",
                () -> _3_find_unique.main(null));
        assertStdOutAfterStdInput("5\n2\n2\n2\n8\n8\n",
                "2\n8\n",
                () -> _3_find_unique.main(null));
    }

    @Test
    public void tstPerformance() {
        int top = 1 << 31 -1;
        System.out.println("top: " + top);
        InputStream is = new IStream(top);
        PrintStream ps = new PrintStream(new OStream(), true);
        stubStdInAndStdOut(is,ps,
                () -> _3_find_unique.process(is,ps)
        );
    }

    static class OStream extends OutputStream {

        @Override
        public void write(int b) throws IOException {

        }
    }


    static class IStream extends InputStream {
        private static final Random rn = new Random(0);
        private final int top;

        private int counter;
        private int oldValue;
        private boolean newLineSet;

        public IStream(int top) {
            this.top = top;
        }

        @Override
        public int read() {
            counter++;
            if (counter < top) {
                if (newLineSet && rn.nextInt(5)==0) {
                    newLineSet = false;
                    return '\n';
                } else {
                    newLineSet = true;
                    int nv = rn.nextBoolean() ? oldValue : counter;
                    oldValue = nv;
                    return (byte) nv;
                }
            } else {
                return -1;
            }
        }
    }
}
