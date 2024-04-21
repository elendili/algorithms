package yandex.contest.old;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicInteger;

import static helpers.TestHelper.assertStdOutAfterStdInput;
import static helpers.TestHelper.stubStdInAndStdOut;

public class _4_parenthesesTest {

    @Test
    public void generateInBytesTest() {
        AtomicInteger n = new AtomicInteger(0);
        assertStdOutAfterStdInput(n.get() + "\n", "\n",
                () -> _4_parentheses.generateInBytes(n.get()));

        n.set(1);
        assertStdOutAfterStdInput(n.get() + "\n", "()\n",
                () -> _4_parentheses.generateInBytes(n.get()));

        n.set(2);
        assertStdOutAfterStdInput(n.get() + "\n",
                "(())\n()()\n",
                () -> _4_parentheses.generateInBytes(n.get()));

        n.set(3);
        assertStdOutAfterStdInput(n.get() + "\n",
                "((()))\n(()())\n(())()\n()(())\n()()()\n",
                () -> _4_parentheses.generateInBytes(n.get()));
    }


    @Test
    public void generateInIndexesTest() {
        AtomicInteger n = new AtomicInteger(0);
        assertStdOutAfterStdInput(n.get() + "\n", "\n",
                () -> _4_parentheses.generateInIndexes(n.get()));

        n.set(1);
        assertStdOutAfterStdInput(n.get() + "\n", "()\n",
                () -> _4_parentheses.generateInIndexes(n.get()));

        n.set(2);
        assertStdOutAfterStdInput(n.get() + "\n",
                "(())\n()()\n",
                () -> _4_parentheses.generateInIndexes(n.get()));

        n.set(3);
        assertStdOutAfterStdInput(n.get() + "\n",
                "((()))\n(()())\n(())()\n()(())\n()()()\n",
                () -> _4_parentheses.generateInIndexes(n.get()));
    }


    int perfCount = 11;

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

    @Test
    public void testPerformanceInIndexes() {
        int n = perfCount;
        OStream os = new OStream();
        stubStdInAndStdOut(new ByteArrayInputStream((n + "\n").getBytes()),
                new PrintStream(os, true),
                () -> _4_parentheses.generateInIndexes(n)
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
