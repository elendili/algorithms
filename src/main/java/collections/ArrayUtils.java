package collections;

import java.util.Arrays;
import java.util.List;

public class ArrayUtils {
    public static int[][] extract2dArrayFromBracketedString(String string) {
        string = string.replaceAll("(^\\s*\\[)|(]\\s*$)", "");
        if (string.isEmpty()) {
            return new int[][]{};
        }
        string = string.replaceAll("(^\\s*\\[)|(]\\s*$)", "");
        List<int[]> outList = Arrays.stream(string.split("],\\["))
                .map(ArrayUtils::extract1dArrayFromBracketedString).toList();
        int[][] out = outList.toArray(new int[][]{});
        return out;
    }

    public static int[] extract1dArrayFromBracketedString(String string) {
        string = string.replaceAll("(^\\s*\\[)|(]\\s*$)", "");
        if (string.isEmpty()) {
            return new int[]{};
        }
        int[] out = Arrays.stream(string.split(",\\s*"))
                .mapToInt(Integer::parseInt)
                .toArray();
        return out;
    }
}
