package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// https://leetcode.com/problems/greatest-common-divisor-of-strings/
public class GreatestCommonDivisorOfStrings {
    public String gcdOfStrings(String s1, String s2) {
        if (s1.length() > s2.length()) {
            String tmp = s1;
            s1 = s2;
            s2 = tmp;
        }
        if (!s1.isEmpty()) {
            for (int i = 0; i < s2.length(); i++) {
                if (s2.charAt(i) != s1.charAt(i % s1.length())) {
                    return "";
                }
            }
            int gcd = gcd(s1.length(), s2.length());
            String start = s1.substring(0, gcd);
            String end = s1.substring(s1.length() - gcd);
            if (start.equals(end)) {
                return start;
            }
        }
        return "";
    }

    int gcd(int a, int b) {
        if (a == b) {
            return a;
        } else if (a > b) {
            return gcd(a - b, b);
        } else {
            return gcd(b - a, a);
        }
    }

    /*
    Best solution
    public String gcdOfStrings(String str1, String str2) {
        if (str1.length() < str2.length()) { // make sure str1 is not shorter than str2.
            return gcdOfStrings(str2, str1);
        }else if (!str1.startsWith(str2)) { // shorter string is not common prefix.
            return "";
        }else if (str2.isEmpty()) { // gcd string found.
            return str1;
        }else { // cut off the common prefix part of str1.
            return gcdOfStrings(str1.substring(str2.length()), str2);
        }
    }

     */

    @Test
    public void test1() {
        Assertions.assertEquals("ABC", gcdOfStrings("ABCABC", "ABC"));
    }

    @Test
    public void test2() {
        Assertions.assertEquals("AB", gcdOfStrings("ABABAB", "ABAB"));
    }

    @Test
    public void testEmpty() {
        Assertions.assertEquals("", gcdOfStrings("", "CODE"));
    }

    @Test
    public void testNeg() {
        Assertions.assertEquals("", gcdOfStrings("AB", "ABA"));
        Assertions.assertEquals("", gcdOfStrings("AB", "ABABA"));
        Assertions.assertEquals("", gcdOfStrings("ABA", "ABABA"));
        Assertions.assertEquals("", gcdOfStrings("ABAB", "ABABA"));
        Assertions.assertEquals("", gcdOfStrings("LEET", "CODE"));
        Assertions.assertEquals("", gcdOfStrings("ABCDEF", "ABC"));
    }

    @Test
    public void fromSite() {
        Assertions.assertEquals("TA", gcdOfStrings(
                "TATATATATA",
                "TATATATATATATATATA"
        ));
    }

    @Test
    public void fromSite2() {
        Assertions.assertEquals("CN", gcdOfStrings(
                "CNCNCNCNCN",
                "CNCNCNCNCNCNCNCNCNCNCNCNCN"
        ));
    }

    @Test
    public void fromSite3() {
        Assertions.assertEquals("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                gcdOfStrings(
                        "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                        "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
                ));
    }
}
