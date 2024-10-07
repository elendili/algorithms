package leetcode.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/contains-duplicate-ii/description/?envType=study-plan-v2&envId=top-interview-150
 */
public class ContainsDuplicateII_219 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> valToIndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer oldIndex = valToIndexMap.put(nums[i], i);
            if (oldIndex != null && (i - oldIndex) <= k) {
                return true;
            }
        }
        return false;
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """
                    nums          | k               | expected
                    1,2,3,1       | 3               | true
                    1,0,1,1       | 1               | true
                    1,2,3,1,2,3   | 2               | false
                    """
    )
    public void test(String numbersString, int k, boolean expected) {
        int[] a = Arrays.stream(numbersString.split(", *"))
                .mapToInt(Integer::parseInt).toArray();
        assertEquals(expected, containsNearbyDuplicate(a, k));
    }


}
