package Algorithms_R_Sedgewick;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
1.3.51 Expression evaluation with precedence order.
Extend Evaluate (page 129) to handle arithmetic expressions that are not fully parenthesized,
 using the standard precdence order for the operators +, -, *, and /.
 */
public class _1_3_51_ExpressionEvaluation {
    //    Set<Character> goodChars = asList('+', );
    static final Map<String, Integer> precedenceMap = Map.of(
            "+", 0,
            "-", 1,
            "*", 2,
            "/", 2
    );

    public List<String> tokenize(String expression) {
        Matcher m = Pattern.compile("(\\+|\\*|-|/|[0-9.,]+|\\)|\\()").matcher(expression);
        List<String> out = new ArrayList<>();
        while (m.find()) {
            String token = m.group(1); //group 0 is always the entire match
            out.add(token);
        }
        return out;
    }

    public double applyOp(String op, Double rightV, Double leftV) {
        if (op.equals("+")) {
            rightV = leftV + rightV;
        } else if (op.equals("-")) {
            rightV = leftV - rightV;
        } else if (op.equals("*")) {
            rightV = leftV * rightV;
        } else if (op.equals("/")) {
            rightV = leftV / rightV;
        }
        return rightV;
    }

    public double evaluate(String expression) {
        assert expression != null && !expression.isEmpty() : "expression should not be empty";
        // prepare, split
        List<String> tokens = tokenize(expression);
        // categorize values and operations
        Deque<String> ops = new ArrayDeque<>(); // stack for operations
        Deque<Double> values = new ArrayDeque<>(); // stack for values
        for (int i = 0; i <= tokens.size(); i++) {
            if (i == tokens.size() || tokens.get(i).equals(")")) {
                String op;
                while (!ops.isEmpty()
                        && !(op = ops.pop()).equals("(")) { // discard left parenthesis and stop brocessing
                    Double newV = applyOp(op, values.pop(), values.pop());
                    values.push(newV);
                }
            } else {
                String t = tokens.get(i);
                if (t.charAt(0) >= '0' && t.charAt(0) <= '9') {
                    // token is number
                    values.push(Double.parseDouble(t));
                } else if (t.equals("(")) {
                    ops.push(t);
                } else if (t.equals(")")) {
                    String op;
                    while (!ops.isEmpty()
                            && !(op = ops.pop()).equals("(")) {
                        Double newV = applyOp(op, values.pop(), values.pop());
                        values.push(newV);
                    }
                } else {
                    // token is operator
                    int tokenPrecedence = precedenceMap.get(t);
                    while (!ops.isEmpty()
                            && !ops.peek().equals("(")
                            && precedenceMap.get(ops.peek()) >= tokenPrecedence) {
                        Double newV = applyOp(ops.pop(), values.pop(), values.pop());
                        values.push(newV);
                    }
                    ops.push(t);
                }
            }
        }
        return values.pop();
    }

    @ParameterizedTest
    @CsvSource({
            "2.01+3, 5.01",
            "2.01-3, -0.99",
            "2.1*2, 4.2",
            "2.1/2, 1.05",
            "(1+2)*5, 15",
            "1+(2*5), 11",
            "1*2+3, 5",
            "1+2*3, 7",
            "(4+2)/2, 3",
            "10/(2+2), 2.5",
            "10/2/2, 2.5",
            "10*2*2, 40",
            "10+2/4*3-1, 10.5",
            "10+((2/4*3-1)), 10.5",
            "(3-1)*10, 20",
            "1-2-2+2*10, 17",
            "1-2-2+(2*10), 17",
            "(2-1)*10, 10",
            "(2+3)*10, 50",
            "1+(2+3)*10, 51",
            "1+10*(2+3), 51",
            "1+(2+3), 6",
            "1-(2+3)-4, -8",
            "1-2+(3-4), -2",
            "(1-2)-(3-4), 0",
            "(1-2)/(2-4), 0.5",
            "(1-2)*(2-4), 2",
            "(1-2*2-4), -7",
            "((((1-2)+1)-1)+1), 0",
            "(1+(1-(1+(1-2)))), 2",
    })
    public void test(String ex, Double exp) {
        Assertions.assertEquals(exp, evaluate(ex), 0.00001);
    }

}
