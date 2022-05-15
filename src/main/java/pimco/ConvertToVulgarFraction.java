package pimco;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConvertToVulgarFraction {
    /*
        IN: 99.625F
        OUT: "99 5/8"

        Limitations: Will always have a denominator of a power of 2, so: x/2, x/4, x/8, x/16, x/32
        Will never be smaller than 1/32 = 0.03125
     */

    public static String floatToFraction_whatIMadeOnInterview(final float inval) {
        int integerPart = (int) inval;
        String out = String.valueOf(integerPart);
        final float fractionPart = inval - integerPart;
        if (fractionPart != 0) {
            int[] denominators = new int[]{2, 4, 8, 16, 32}; //
            outLabel:
            for (int denominator : denominators) {
                for (int i = 1; i < denominator; i++) {
                    float divided = ((float) i) / denominator;
                    if (divided == fractionPart) {
                        out += " " + i + "/" + denominator;
                        break outLabel;
                    }
                }
            }
        }
        return out;
    }

    public static String floatToFractionBetterSolution(final float inval) {
        int integerPart = (int) inval;
        String out = String.valueOf(integerPart);
        final float fractionPart = inval - integerPart;
        if (fractionPart != 0) {
            int denominator = (int) Math.pow(10, 5);// because 32 = 2^5
            int numerator = (int) (fractionPart * denominator);
            while (numerator % 5 == 0 && denominator % 5 == 0) {
                denominator = denominator / 5;
                numerator = numerator / 5;
            }
            while (numerator % 2 == 0 && denominator % 2 == 0) {
                denominator = denominator / 2;
                numerator = numerator / 2;
            }
            out += " " + numerator + "/" + denominator;
        }
        return out;

    }

    @Test
    public void test() {
        Assertions.assertEquals("99 5/8", floatToFraction_whatIMadeOnInterview(99.625F));
        Assertions.assertEquals("99", floatToFraction_whatIMadeOnInterview(99.0F));
        Assertions.assertEquals("99 1/32", floatToFraction_whatIMadeOnInterview(99.03125f));
    }

    @Test
    public void floatToFractionBetterSolutionTest() {
        Assertions.assertEquals("99", floatToFractionBetterSolution(99.0F));
        Assertions.assertEquals("99 1/32", floatToFractionBetterSolution(99.03125f));
        Assertions.assertEquals("99 5/8", floatToFractionBetterSolution(99.625F));
    }

}
