package math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.Arrays;

/*
code copied and adopted from Integer.toString
for discovery why do they use negative numbers in cycle

Discovery result: no clue :(
 */
public class WhyJDKConversionUseNegativeNumber {
    static final char[] digits = {
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
    };

    public static String toStringOriginal(int i, int radix) {
        char[] buf = new char[33];
        boolean negative = (i < 0);
        int charPos = 32;

        if (!negative) {
            i = -i; // that's the thing. why negation???
        }

        while (i <= -radix) {
            buf[charPos--] = digits[-(i % radix)];
            i = i / radix;
        }
        buf[charPos] = digits[-i];

        if (negative) {
            buf[--charPos] = '-';
        }

        char[] outca = Arrays.copyOfRange(buf, charPos, charPos + (33 - charPos));
        return new String(outca);
    }

    public static String toStringWithPositives(int i, int radix) {
        char[] buf = new char[33];
        boolean negative = (i < 0);
        int charPos = 32;
        i = Math.abs(i);
        while (i >= radix) {
            buf[charPos--] = digits[(i % radix)];
            i = i / radix;
        }
        buf[charPos] = digits[i];

        if (negative) {
            buf[--charPos] = '-';
        }

        char[] outca = Arrays.copyOfRange(buf, charPos, charPos + (33 - charPos));
        return new String(outca);
    }

    @Test
    public void test() {
        Assertions.assertEquals("0", toStringOriginal(0, 7));
        Assertions.assertEquals("10", toStringOriginal(7, 7));
        Assertions.assertEquals("-10", toStringOriginal(-7, 7));
        Assertions.assertEquals("2626", toStringOriginal(1000, 7));
        Assertions.assertEquals("-202", toStringOriginal(-100, 7));
    }

    @Test
    public void testPos() {
        Assertions.assertEquals("0", toStringWithPositives(0, 7));
        Assertions.assertEquals("10", toStringWithPositives(7, 7));
        Assertions.assertEquals("-10", toStringWithPositives(-7, 7));
        Assertions.assertEquals("2626", toStringWithPositives(1000, 7));
        Assertions.assertEquals("-202", toStringWithPositives(-100, 7));
    }

    @Test
    public void testPos2() {
        Assertions.assertEquals("0", toStringWithPositives(0, 7));
        Assertions.assertEquals("10", toStringWithPositives(7, 7));
        Assertions.assertEquals("-10", toStringWithPositives(-7, 7));
        Assertions.assertEquals("2626", toStringWithPositives(1000, 7));
        Assertions.assertEquals("-202", toStringWithPositives(-100, 7));
    }

    @Benchmark
    public void original() {
        String x = WhyJDKConversionUseNegativeNumber.toStringOriginal(-1000000, 7);
    }

    @Benchmark
    public void positive() {
        String x = WhyJDKConversionUseNegativeNumber.toStringWithPositives(-1000000, 7);
    }


    @Test
    public void benchmark() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(this.getClass().getSimpleName())
                .warmupIterations(100)
                .warmupTime(TimeValue.milliseconds(5))
                .measurementIterations(100)
                .measurementTime(TimeValue.milliseconds(5))
                .mode(Mode.Throughput)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
