package yandex.contest;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import static hackerrank.TestHelper.assertStdOutAfterStdInput;
import static hackerrank.TestHelper.stubStdInAndStdOut;

public class _4_parenthesesTest {

    @Test
    public void test() {
        int n = 2;
        assertStdOutAfterStdInput(n + "\n", "(())\n()()\n",
                () -> _4_parentheses.generateInBytes(n));

        int n1 = 3;

        assertStdOutAfterStdInput(n1 + "\n", "((()))\n(()())\n(())()\n()(())\n()()()\n",
                () -> _4_parentheses.generateInBytes(n1));
    }

    int perfCount = 10;

    @Test
    public void testPerformanceInBytes() {
        int n = perfCount;
        OStream os = new OStream();
        stubStdInAndStdOut(new ByteArrayInputStream((n + "\n").getBytes()),
                new PrintStream(os, true),
                () -> _4_parentheses.generateInBytes(n)
        );
        System.out.println("Symbols: " + os.counter);
    }

    static class OStream extends OutputStream {
        int counter = 0;

        @Override
        public void write(int b) throws IOException {
            counter++;
        }
    }

}
