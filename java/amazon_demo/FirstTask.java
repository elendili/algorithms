package amazon_demo;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class FirstTask {

    private int[] getNewStates(int[] states) {
        int[] outStates = Arrays.copyOf(states, states.length);
        for (int i = states.length - 1; i > -1; i--) {
            int ns = getNewStateOfCell(states, i);
            outStates[i] = ns;
        }
        return outStates;
    }

    private int getNewStateOfCell(int[] states, int index) {
        int lnSate = index > 0 ? states[index - 1] : 0;
        int rnState = index < states.length - 1 ? states[index + 1] : 0;
        int out = (lnSate + rnState) == 1 ? 1 : 0;
        return out;
    }

    public List<Integer> cellCompete(int[] states, int days) {
        assert days > -1 : "days amount should be positive";
        assert Arrays.stream(states).allMatch(i -> i == 0 || i == 1) : "cell values should be 0 or 1";
        int[] outStates = states;
        for (; days > 0; days--) {
            outStates = getNewStates(outStates);
        }
        return Arrays.stream(outStates).boxed().collect(toList());
    }

    public List<Integer> cellCompete2(int[] states, int days) {
        assert days > -1 : "days amount should be positive";
        assert Arrays.stream(states).allMatch(i -> i == 0 || i == 1) : "cell values should be 0 or 1";
        int[] outStates = Arrays.copyOf(states, states.length);
        for (; days > 0; days--) {
            int oldRightState = 0;
            for (int i = states.length - 1; i > -1; i--) {
                int leftState = i > 0 ? outStates[i - 1] : 0;
                int newState = (leftState + oldRightState) == 1 ? 1 : 0;
                oldRightState = outStates[i];
                outStates[i] = newState;
            }
        }
        return Arrays.stream(outStates).boxed().collect(toList());
    }

    public List<Integer> cellCompete3(int[] states, int days) {
        assert days > 0 : "days should be positive number";
        int[] outStates = Arrays.copyOf(states,states.length);
        for (; days > 0; days--) {
            int oldLeftState = 0;
            for (int i = 0; i < outStates.length; i++) {
                int rightState = i < outStates.length - 1 ? outStates[i+1] : 0;
                // turn off when sum of neighbour states is 0 or 2
                int newState = (oldLeftState + rightState) == 1 ? 1 : 0;
                oldLeftState = outStates[i];
                outStates[i] = newState;
            }
        }
        return Arrays.stream(outStates).boxed().collect(Collectors.toList());
    }

    @Test
    public void test1Day() {
        assertEquals(asList(0, 1, 0, 0, 1, 0, 1, 0),
                cellCompete(new int[]{1, 0, 0, 0, 0, 1, 0, 0}, 1));
    }

    @Test
    public void test1_2Day() {
        assertEquals(asList(0, 1, 0, 0, 1, 0, 1, 0),
                cellCompete3(new int[]{1, 0, 0, 0, 0, 1, 0, 0}, 1));
    }

    @Test
    public void test2Days() {
        assertEquals(asList(0, 0, 0, 0, 0, 1, 1, 0),
                cellCompete(new int[]{1, 1, 1, 0, 1, 1, 1, 1}, 2));
    }

    @Test
    public void test2_2Days() {
        assertEquals(asList(0, 0, 0, 0, 0, 1, 1, 0),
                cellCompete3(new int[]{1, 1, 1, 0, 1, 1, 1, 1}, 2));
    }
}
