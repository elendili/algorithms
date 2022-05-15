package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
// https://leetcode.com/problems/walking-robot-simulation/
public class WalkingRobotSimulation {
    class Solution {
        public int robotSim(int[] commands, int[][] obstacles) {
            int[] p = new int[]{0, 0};// position, current coordinates
            int[] d = new int[]{0, 1};// direction, to north on start
            Set<Integer> oset = Arrays.stream(obstacles)
                    .map(this::key)
                    .collect(Collectors.toSet());
            int out=0;
            for (int c : commands) {
                if (c == -1) {// turn right
                    turnRight(d);
                } else if (c == -2) { // turn left
                    turnLeft(d);
                } else { // move
                    for (; c > 0; c--) {
                        p[0] += d[0];
                        p[1] += d[1];
                        int nk = key(p);
                        if (oset.contains(nk)) {
                            p[0] -= d[0];
                            p[1] -= d[1];
                            break;
                        }
                    }
                    out = Math.max(out, p[0] * p[0] + p[1] * p[1]);
                }
            }
            return out;
        }

        int key(int[] p) {
            int x = p[0]+30000;
            int y = p[1]+30000;
            return x << 16 | y;
        }

        /*
        N: 0,1
        E: 1,0
        S: 0,-1
        W: -1,0
        */
        void turnRight(int[] d) {
            if (d[0] != 0) {
                d[1] = -d[0];
                d[0] = 0;
            } else {
                d[0] = d[1];
                d[1] = 0;
            }
        }

        void turnLeft(int[] d) {
            if (d[0] != 0) {
                d[1] = d[0];
                d[0] = 0;
            } else {
                d[0] = -d[1];
                d[1] = 0;
            }
        }
    }

    @Test
    public void test(){
        Solution s = new Solution();
        Assertions.assertEquals(25,s.robotSim(new int[]{4,-1,3},new int[][]{}));
        Assertions.assertEquals(65,s.robotSim(new int[]{4,-1,4,-2,4},new int[][]{{2,4}}));
    }
}
