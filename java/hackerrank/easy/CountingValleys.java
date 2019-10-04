package hackerrank.easy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

// https://www.hackerrank.com/challenges/counting-valleys/problem
public class CountingValleys {

    // Complete the countingValleys function below.
    static int countingValleys(int n, String s) {
        int currentLevel = 0;
        int valleysCounter = 0;
        for (char c : s.toCharArray()) {
            int incrementer = c == 'U' ? 1 : -1;
            currentLevel += incrementer;
            if (currentLevel == 0 && incrementer > 0) {
                valleysCounter = valleysCounter + 1;
            }
        }
        return valleysCounter;
    }

    @Test
    public void test() {
        assertEquals(1, countingValleys(8, "UDDDUDUU"));
    }

}
