package leetcode.top_interview_questions.easy.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/127/strings/880/
Given a 32-bit signed integer, reverse digits of an integer.
 */
public class ReverseInteger {
    public int reverse(int x) {
        long out=0;
        int d;
        double v;
        int sign = (int)Math.signum(x);
        x=Math.abs(x);
        int size = (int)Math.log10(x);
        for(int i=0;i<size+1;i++){
            // get smallest digit of int
            d = x%10;
            v= (d*(Math.pow(10,size-i)));
            out+=v;
            if(out<0 || out>Integer.MAX_VALUE){ // overflow on summation
                return 0;
            }
            x = x/10;
        }
        return ((int)out)*sign;
    }

    @Test
    public void test(){
        assertEquals(321,reverse(123));
        assertEquals(-123,reverse(-321));
        assertEquals(21,reverse(120));
    }

    @Test
    public void overflow(){
        assertEquals(0,reverse(1534236469));
        assertEquals(0,reverse(1563847412));
    }
}
