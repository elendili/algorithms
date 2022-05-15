package crackingCodeInterview;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Optional;

// child is running staircase with n stairs
// count how many stepping ways are possible
// from all combinations of steps of diff length:
// 1,2,3,..,k
// e.g. if k=3 and n=3, then there are 4 combinations:
// [1,1,1],[1,2],[2,1],[3]
// Basically the issue is Fibonacci sequence calculation,
// but with amount of summed elements = k
// https://rosettacode.org/wiki/Fibonacci_n-step_number_sequences
public class StairCaseNStep {
    BigDecimal getAmountOfSteps(int staircaseLength, int maxStepLength) {
        // init N elements
        LinkedList<BigDecimal> elements = new LinkedList<>();
        elements.add(new BigDecimal(1)); // zero element is always 1.
        for (; staircaseLength > 0; staircaseLength--) {
            // sum of N elements
            BigDecimal e = elements.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            // shift of elements
            elements.addFirst(e);
            if (elements.size() > maxStepLength) {
                elements.removeLast();
            }
        }
        return Optional.ofNullable(elements.peekFirst()).orElse(BigDecimal.ONE);
    }

    @Test
    public void test() {
        Assertions.assertEquals(1, getAmountOfSteps(0, 0).intValue());
        Assertions.assertEquals(1, getAmountOfSteps(1, 0).intValue());
        Assertions.assertEquals(1, getAmountOfSteps(0, 1).intValue());
        Assertions.assertEquals(1, getAmountOfSteps(1, 1).intValue());
        Assertions.assertEquals(1, getAmountOfSteps(2, 1).intValue());
        // classic fibonacci
        Assertions.assertEquals(2, getAmountOfSteps(2, 2).intValue());
        Assertions.assertEquals(3, getAmountOfSteps(3, 2).intValue());
        Assertions.assertEquals(5, getAmountOfSteps(4, 2).intValue());
        Assertions.assertEquals(8, getAmountOfSteps(5, 2).intValue());

        // tribonacci
        Assertions.assertEquals(2, getAmountOfSteps(2, 3).intValue());
        Assertions.assertEquals(4, getAmountOfSteps(3, 3).intValue());
        Assertions.assertEquals(7, getAmountOfSteps(4, 3).intValue());
        Assertions.assertEquals(13, getAmountOfSteps(5, 3).intValue());
        // decanacci
        Assertions.assertEquals(512, getAmountOfSteps(10, 10).intValue());
    }

    @Test
    public void bigTest(){
        Assertions.assertEquals("573147844013817084101", getAmountOfSteps(100, 2).toString());
        Assertions.assertEquals("180396380815100901214157639", getAmountOfSteps(100, 3).toString());
        Assertions.assertEquals("122442023685755523117510594696", getAmountOfSteps(100, 5).toString());
        Assertions.assertEquals("606147434557459526483161067501", getAmountOfSteps(100, 10).toString());
        Assertions.assertEquals("633825300114114700748351602688", getAmountOfSteps(100, 100).toString());
        Assertions.assertEquals("70330367711422815821835254877183549770181269836358732742604905087154537118196933579742249494562611733487750449241765991088186363265450223647106012053374121273867339111198139373125598767690091902245245323403501",
                getAmountOfSteps(1000, 2).toString());
        Assertions.assertEquals("5357543035931336604742125245300009052807024058527668037218751941851755255624680612465991894078479290637973364587765734125935726428461570217992288787349287401967283887412115492710537302531185570938977091076523237491790970633699383779582771973038531457285598238843271083830214915826312193418602834034688",
                getAmountOfSteps(1000, 1000).toString());
    }
}
