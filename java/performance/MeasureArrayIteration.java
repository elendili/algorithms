package performance;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public class MeasureArrayIteration {
    static int bigSize = 10;
    static int littleSize = 1;
    static int[][] littleBigArray = new int[littleSize][bigSize];
    static int[][] squareArray = new int[littleSize][littleSize];
/*
    @Benchmark
    public void vS_bigInternalLoop_faster() {
        for (int i = 0; i < littleSize; i++) {
            for (int j = 0; j < bigSize; j++) {
                littleBigArray[i][j] = 1;
            }
        }
    }

    @Benchmark
    public void vS_littleInternalLoop_slower() {
        for (int i = 0; i < bigSize; i++) {
            for (int j = 0; j < littleSize; j++) {
                littleBigArray[j][i] = 1;
            }
        }
    }*/


    @Benchmark
    public void changeInternalPointer_slower() {
        int littleSize=1;
        for (int i = 0; i < littleSize; i++) {
            squareArray[0][i]=1;
        }
    }

    @Benchmark
    public void changeExternalPointer_faster() {
        int littleSize=1;
        for (int i = 0; i < littleSize; i++) {
            squareArray[i][0]=1;
        }
    }


    @Test
    public void test() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(this.getClass().getSimpleName())
                .warmupIterations(5)
                .warmupTime(TimeValue.milliseconds(50))
                .measurementIterations(20)
                .measurementTime(TimeValue.milliseconds(50))
                .mode(Mode.Throughput)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}