package leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static helpers.TestHelper.extract1dArrayFromBracketedString;

/**
 * https://leetcode.com/problems/divide-chocolate/editorial/?envType=study-plan-v2&envId=premium-algo-100
 */
public class DivideChocolate_1231 {
    public int maximizeSweetness(int[] sweetness, int k) {
        int requiredPiecesNumber = k + 1;
        int left = Integer.MAX_VALUE; // min sweetness value will be assigned
        int sum = 0;
        for (int s : sweetness) {
            left = Math.min(left, s);
            sum += s;
        }
        int right = sum / requiredPiecesNumber;

        while (left < right) {
            int mid = (left + right + 1) / 2;
            int curPieceSweetness = 0; // stands for the total sweetness of the current piece
            int readyPieces = 0; // number of pieces on which bar is split

            // check that found piece sweetness is valid for task condition
            for (int s : sweetness) {
                curPieceSweetness += s;
                if (curPieceSweetness >= mid) {
                    readyPieces += 1;
                    curPieceSweetness = 0; // new piece
                }
            }

            if (readyPieces >= requiredPiecesNumber) {
                left = mid;
            } else {
                right = mid - 1;
            }


        }

        return right;
    }


    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """              
                    expected | sweetness              | k 
                    6        | [1,2,3,4,5,6,7,8,9]    | 5 
                    1        | [5,6,7,8,9,1,2,3,4]    | 8
                    5        | [1,2,2,1,2,2,1,2,2]    | 2 
                    4        | [1,1,1,2,2,2,2,2,2]    | 2 
                    """
    )
    public void test(int expected, String sweetness, int k) {
        Assertions.assertEquals(expected, maximizeSweetness(extract1dArrayFromBracketedString(sweetness), k));
    }
}
