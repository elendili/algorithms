package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Base7 {
    public String convertToBase7_2(int num) {
        return Integer.toString(num, 7);
    }

    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        int q = Math.abs(num);
        while (q != 0) {
            int z = q % 7;
            sb.insert(0, z);
            q = q / 7;
        }
        String out = sb.toString();
        return num > 0 ? out : "-" + out;
    }

    @Test
    public void test() {
        Assertions.assertEquals("0", convertToBase7(0));
        Assertions.assertEquals("10", convertToBase7(7));
        Assertions.assertEquals("-10", convertToBase7(-7));
        Assertions.assertEquals("2626", convertToBase7(1000));
        Assertions.assertEquals("-202", convertToBase7(-100));
    }

    @Test
    public void test_2() {
        Assertions.assertEquals("0", convertToBase7_2(0));
        Assertions.assertEquals("10", convertToBase7_2(7));
        Assertions.assertEquals("-10", convertToBase7_2(-7));
        Assertions.assertEquals("2626", convertToBase7_2(1000));
        Assertions.assertEquals("-202", convertToBase7_2(-100));
    }


}

