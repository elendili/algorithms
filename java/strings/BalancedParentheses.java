package strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class BalancedParentheses {

    public void generate(String cur, int o, int c, int n, String path) {
        if (cur.length() == 2 * n) {
            System.out.println(path);
            System.out.println(cur);
        } else {
            if (o < n) {
                generate(cur + "(", o + 1, c, n, path + "a ");
            }
            if (c < o) {
                generate(cur + ")", o, c + 1, n, path + "b ");
            }
        }
    }

    public void generate2(String cur, int oRemain, int cRemain, String path) {
        if (cRemain == 0) {
            System.out.println(path);
            System.out.println(cur);
        } else {
            if (oRemain > 0) {
                generate2(cur + "(", oRemain - 1, cRemain, path + "a ");
                if (oRemain < cRemain) {
                    generate2(cur + ")", oRemain, cRemain - 1, path + "b ");
                }
            } else {
                generate2(cur + ")", oRemain, cRemain - 1, path + "c ");
            }
        }
    }

    public List<String> allBalancedStrings(final int n) {
        List<String> out = new ArrayList<>();
        if (n == 0) {
            out.add("");
        } else {
            for (int i = 0; i < n; i++) {
                List<String> list = allBalancedStrings(i);
                for (String s1 : list) {
                    List<String> list2 = allBalancedStrings(n - i - 1);
                    for (String s2 : list2) {
                        String toAdd = "(" + s1 + ")" + s2;
                        out.add(toAdd);
                    }
                }
            }
        }
        return out;
    }

    @Test
    public void fromYandex() {
        generate("", 0, 0, 2, "");
        System.out.println("------");
        generate2("", 2, 2, "");
        System.out.println("------");
        System.out.println(allBalancedStrings(2));
    }

    public List<String> balancedParentheses(int n) {
        return null;
//        int c=0, o=0;
//        for(){
//
//        }
    }

    @Test
    public void t() {
        List<String> exp = asList(
                "()()()",
                "((()))",
                "(())()",
                "()(())",
                "(()())")
                .stream().sorted().collect(Collectors.toList());
        Assertions.assertEquals(exp, balancedParentheses(3));
    }
}
