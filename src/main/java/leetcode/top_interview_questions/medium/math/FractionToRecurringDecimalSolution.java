package leetcode.top_interview_questions.medium.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/113/math/821/
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

If multiple answers are possible, return any of them.

Example 1:
Input: numerator = 1, denominator = 2
Output: "0.5"
Example 2:
Input: numerator = 2, denominator = 1
Output: "2"
Example 3:
Input: numerator = 2, denominator = 3
Output: "0.(6)"
Example 4:
Input: numerator = 4, denominator = 333
Output: "0.(012)"
Example 5:
Input: numerator = 1, denominator = 5
Output: "0.2"

Constraints:
-231 <= numerator, denominator <= 231 - 1
denominator != 0

 */
public class FractionToRecurringDecimalSolution {

    public String fractionToDecimal(long numerator, long denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        sb.append((numerator < 0 ^ denominator < 0) ? '-' : "");
        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);
        sb.append(numerator / denominator);
        long remainder = numerator % denominator;
        if (remainder > 0) {
            sb.append('.');
            int[] quotientToIndex = new int[10];
            long[] quotientToRemainder = new long[10];
            while (remainder > 0) {
                remainder *= 10;
                int quotient = (int) (remainder / denominator);
                if (quotientToIndex[quotient]>0) {
                    if(quotientToRemainder[quotient]==remainder){
                        int index = quotientToIndex[quotient];
                        sb.insert(index, '(');
                        sb.append(')');
                        break;
                    } else {
                        sb.append(quotient);
                    }
                } else {
                    sb.append(quotient);
                    quotientToRemainder[quotient]=remainder;
                    quotientToIndex[quotient]= sb.length()-1;
                }
                remainder = remainder % denominator;
                if (sb.length() >= Integer.MAX_VALUE / 10_000) {
                    break;// to prevent "infinite" division, when getting result takes too much numbers
                }
            }
        }
        return sb.toString();
    }

    @Test
    public void test() {
        Assertions.assertEquals("0.5", fractionToDecimal(1, 2));
        Assertions.assertEquals("2", fractionToDecimal(2, 1));
        Assertions.assertEquals("0.(6)", fractionToDecimal(2, 3));
        Assertions.assertEquals("0.(012)", fractionToDecimal(4, 333));
        Assertions.assertEquals("0.2", fractionToDecimal(1, 5));
    }

    @Test
    public void negative() {
        Assertions.assertEquals("0", fractionToDecimal(0, -5));
        Assertions.assertEquals("-0.5", fractionToDecimal(-1, 2));
        Assertions.assertEquals("0.5", fractionToDecimal(-1, -2));
        Assertions.assertEquals("-0.5", fractionToDecimal(1, -2));
    }

    @Test
    public void MaxInteger() {
        Assertions.assertEquals("-0.0000000004656612873077392578125",
                fractionToDecimal(1, Integer.MIN_VALUE));
        Assertions.assertEquals("-0.9999999995343387126922607421875",
                fractionToDecimal(Integer.MAX_VALUE, Integer.MIN_VALUE));

        Assertions.assertEquals("1",
                fractionToDecimal(Integer.MIN_VALUE, Integer.MIN_VALUE));
        Assertions.assertEquals("1",
                fractionToDecimal(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void notInfiniteDivision() {
        Assertions.assertEquals("0.000000000(465661287741420127210598557452297357332238328952564232938517101983090026288377145573848062729321478613951689204156109322007847318433082959086711480363003425638194592332648665022709094940413809325931416196684740666937772805744570499048168304421164378916066492829533752826539569372813747611654687311178713395426714229794884314569536889502347344069133833115113743688085809059521005544365388848227810904595824800986633450711735943995170243079932632930514060827450883414084914526049899427266697797241339271181579000485668890630517984396329135071792765587375280994340107770953427786895472357883558010574018592549505264078737482501880715137236486316878801506831125828280230824165261186813247564074813913623629057429385424991497234433421152116191715110271903788924127620574205760447500050484668510486063091537042606190817995174618433392251314029331611496705200054408237388737720799388103931572385068584592145480767027922689009385825143555016390564866727744105018437006527964963138070854486963576159406058638734797610654307166723820536140185348820113901812689287413553602447317542924841440212765187223223212382964056341875396987307255144517175056521943822989187969816092373631980618119221756345798965865558912852368236381903510896399161681904608087525338016008341699846407119041687919797085150887337653792777707607278328023178808412681155291089001382783987915873516272635679945941716382225711254613205096324165441397731603493664044415265363096506673001224801876791568358234659170950445505744261206820850453172671099354206676924793680128449276209295984552517518915717973295336564346530068988474150177532946856257456220926210489986660415294263898669056500000000)",
                fractionToDecimal(1, Integer.MAX_VALUE - 1));
    }

    @Test
    public void infiniteDivision() {
        Assertions.assertEquals(Integer.MAX_VALUE / 10_000,
                fractionToDecimal(Integer.MIN_VALUE, Integer.MAX_VALUE).length());
    }

    @Test
    public void repeating() {
        Assertions.assertEquals("0.(0000000009)", fractionToDecimal(1, 1111111111));
        Assertions.assertEquals("0.(8999999991)", fractionToDecimal(999999999, 1_111_111_111));
        Assertions.assertEquals("0.(000000001)", fractionToDecimal(1, 999999999));
//        Assertions.assertEquals("0.(000000001)", fractionToDecimal(1, 999999999));
    }
}
