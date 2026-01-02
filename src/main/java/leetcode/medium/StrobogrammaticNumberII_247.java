package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StrobogrammaticNumberII_247 {
    char[][] symmetry = new char[][]{
            {'0', '0'},
            {'1', '1'},
            {'6', '9'},
            {'8', '8'},
            {'9', '6'},
    };

    int n;

    public List<String> findStrobogrammatic(int n) {
        this.n = n;
        List<String> out = new ArrayList<>();
        char[] word = new char[n];
        if (n % 2 == 1) {
            for (char[] cc : symmetry) {
                if (cc[0] == cc[1]) {
                    word[n / 2] = cc[0];
                    recursive(word, 0, out);
                }
            }
        } else {
            recursive(word, 0, out);
        }
        return out;
    }

    void recursive(char[] number, int position, List<String> result) {
        if (position >= n / 2) {
            result.add(String.valueOf(number));
        } else {
            for (char[] cc : symmetry) {
                if (position == 0 && cc[0] == '0') {
                    continue;
                }
                number[position] = cc[0];
                number[number.length - position - 1] = cc[1];
                recursive(number, position + 1, result);
            }
        }
    }

    @org.junit.jupiter.api.Test
    public void test1() {
        assertEquals("[0, 1, 8]", findStrobogrammatic(1).toString());
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        assertEquals("[11, 69, 88, 96]", findStrobogrammatic(2).toString());
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        assertEquals("[101, 609, 808, 906, 111, 619, 818, 916, 181, 689, 888, 986]", findStrobogrammatic(3).toString());
    }

    @org.junit.jupiter.api.Test
    public void test4() {
        assertEquals("[1001, 1111, 1691, 1881, 1961, 6009, 6119, 6699, 6889, 6969, 8008, 8118, 8698, 8888, 8968, 9006, 9116, 9696, 9886, 9966]", findStrobogrammatic(4).toString());
    }

}
