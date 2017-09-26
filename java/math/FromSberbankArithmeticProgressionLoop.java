package math;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.abs;

/*
Benchmark                                                                      Mode  Cnt  Score   Error  Units
FromSberbankArithmeticProgressionLoop.nonOptimizedLoop                         avgt    2  0.815           s/op
FromSberbankArithmeticProgressionLoop.optimizedLoop                            avgt    2  0.805           s/op
FromSberbankArithmeticProgressionLoop.optimizedWithLong                        avgt    2  0.846           s/op
FromSberbankArithmeticProgressionLoop.optimizedWithLongAndDifferentPrimitives  avgt    2  1.138           s/op
 */
public class FromSberbankArithmeticProgressionLoop {

    static final int targetSum =  1073741825;
    static final long sumByGauss = ((long) MAX_VALUE * (((long) MAX_VALUE) - 1)) / 2;
    static final long remainderAfterIntegerOVerflow = (long)(sumByGauss % MAX_VALUE );
    static final long remainderAfterIntegerOVerflow2 = (long)(sumByGauss % ((long)2*MAX_VALUE) );
    static final long countOfMaxInSum = (long)(sumByGauss / MAX_VALUE );
    static final long countOfRangeInSum = (sumByGauss / ((long)2*MAX_VALUE) );

    @Benchmark
    public int optimizedLoop() {
        int sum = 0;
        int beforeOverflow = MAX_VALUE/2-1;
        for (int i = 0; i < beforeOverflow; i++) {
            sum += i;
        }
        return sum;
    }

    @Benchmark
    public int nonOptimizedLoop() {
        int sum = 0;
        for (int i = 0; i < MAX_VALUE; i++) {
            sum += i;
            if (sum==targetSum){
                System.out.println("i="+i);
            }
        }
        return sum;
    }

    @Test
    public void test2(){
        System.out.println("Target Sum: 1073741825");
        System.out.println("Sum By Gauss: " +sumByGauss);
        System.out.println("Max_Int: " +MAX_VALUE);
        System.out.println("Max_Int / Target Sum : " +(MAX_VALUE / 1073741825));
        System.out.println("Max_Int - Target Sum : " +(MAX_VALUE - 1073741825));
        System.out.println("remainderAfterIntegerOVerflow: "+remainderAfterIntegerOVerflow);
        System.out.println("remainderAfterIntegerOVerflow2: "+remainderAfterIntegerOVerflow2);
        System.out.println("remainderAfterIntegerOVerflow2-MAX_VALUE: "+(remainderAfterIntegerOVerflow2-MAX_VALUE));
        System.out.println("countOfMaxInSum: "+countOfMaxInSum);
        System.out.println("countOfRangeInSum: "+countOfRangeInSum);
        Assert.assertEquals(nonOptimizedLoop(), optimizedLoop());
    }

    @Test
    public void test() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(this.getClass().getSimpleName())
                .warmupIterations(1)
                .measurementIterations(2)
                .mode(Mode.AverageTime)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
