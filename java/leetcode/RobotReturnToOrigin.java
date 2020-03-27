package leetcode;

import java.util.Arrays;

//    https://leetcode.com/problems/robot-return-to-origin/submissions/
public class RobotReturnToOrigin {
    public boolean judgeCircle(String moves) {
        // 0 index - X, 1 index - Y
        int[] coords = new int[]{0, 0};
        for (char c : moves.toCharArray()) {
            switch (c) {
                case 'L':
                    coords[0] = coords[0] - 1;
                    break;
                case 'R':
                    coords[0] = coords[0] + 1;
                    break;
                case 'U':
                    coords[1] = coords[1] - 1;
                    break;
                case 'D':
                    coords[1] = coords[1] + 1;
                    break;
            }
        }
        return Arrays.equals(new int[]{0, 0}, coords);
    }
}
