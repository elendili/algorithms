package leetcode.top_interview_questions.easy;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
  https://leetcode.com/explore/featured/card/top-interview-questions-easy/102/math/743/
Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”.
For numbers which are multiples of both three and five output “FizzBuzz”.
 */
public class FizzBuzz {

    public List<String> fizzBuzz(int n) {
        // Postpone String calculation to runtime, just to get calculation time faster :)

        List<String> out = new java.util.AbstractList<String>(){
            private static final String FIZZ = "Fizz";
            private static final String BUZZ = "Buzz";
            @Override
            public int size() {
                return n;
            }

            @Override
            public String get(int i) {
                i++;
                boolean of3 = i % 3 == 0;
                boolean of5 = i % 5 == 0;
                String v;
                if (!of3 && !of5) {
                    v = Integer.toString(i);
                } else if (of3 && of5) {
                    v = FIZZ+BUZZ;
                } else if (of3) {
                    v = FIZZ;
                } else {
                    v = BUZZ;
                }
                return v;
            }
        };
        return out;
    }

    @Test
    public void test() {
        assertEquals(asList(
                "1",
                "2",
                "Fizz",
                "4",
                "Buzz",
                "Fizz",
                "7",
                "8",
                "Fizz",
                "Buzz",
                "11",
                "Fizz",
                "13",
                "14",
                "FizzBuzz"), fizzBuzz(15));
    }
}
