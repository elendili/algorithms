package helpers;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeetcodeApplicationTestWrapperHelper {
    private final List<Instruction> instructions;

    private LeetcodeApplicationTestWrapperHelper(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public static LeetcodeApplicationTestWrapperHelper from(String string) {
        return new LeetcodeApplicationTestWrapperHelper(parse(string));
    }

    /**
     * Input
     * ["TextEditor", "addText", "deleteText", "addText", "cursorRight", "cursorLeft", "deleteText", "cursorLeft", "cursorRight"]
     * [[], ["leetcode"], [4], ["practice"], [3], [8], [10], [2], [6]]
     * Output
     * [null, null, 4, null, "etpractice", "leet", 4, "", "practi"]
     */

    static protected List<Instruction> parse(String string) {
        String cleaninput = string.replaceAll("(?m)^Input\n", "").replaceAll("(?m)^Output\n", "");
        List<List<String>> lines = Arrays.stream(cleaninput.split("\n"))
                .map(String::trim)
                .map(l -> l.replaceAll("^\\[", ""))
                .map(l -> l.replaceAll("]$", ""))
                .map(l -> Arrays.stream(l.split(", "))
                        .map(String::trim)
                        .map(e -> e.replaceAll("\\[", "")
                                .replaceAll("]$", ""))
                        .toList())
                .toList();
        List<Instruction> out = new ArrayList<>();
        for (int i = 0; i < lines.get(0).size(); i++) {
            String method = lines.get(0).get(i);
            method = method.replaceAll("^\"|\"$", "");
            String inputArgs = lines.get(1).get(i);
            List<?> inputArgsList = Arrays.stream(inputArgs.split(", "))
                    .map(LeetcodeApplicationTestWrapperHelper::processValue).toList();
            String expected = lines.get(2).get(i);
            out.add(new Instruction(method, inputArgsList, processValue(expected)));
        }
        return out;
    }

    static private Object processValue(String s) {
        if (s.equals("null")) {
            return null;
        } else if (s.equals("")) {
            return "";
        } else if (s.startsWith("\"")) {
            return s.substring(1, s.length() - 1);
        } else {
            return Integer.valueOf(s);
        }
    }

    record Instruction(String method, List<?> inputArguments, Object expected) {

        @Override
        public String toString() {
            String inp = inputArguments.stream().map(String::valueOf).collect(Collectors.joining(", "));
            return method+"("+inp+") => "+expected;
        }
    }

    void execute() {
        Object app = createApp(instructions.get(0));
        instructions.stream().skip(1).forEach(i -> {
                    Object actual = executeInstruction(app, i.method(), i.inputArguments());
                    String actualString = String.valueOf(actual);
                    String expectedString = String.valueOf(i.expected);
                    System.out.println(expectedString+", "+actualString);
                    assertEquals(expectedString, actualString, "on instr "+i);
                }
        );
    }

    Object createApp(Instruction instruction) {
        Instruction creationInstruction = instruction;
        Class<?> clazz = findClass(creationInstruction.method());
        Constructor<?> c = getConstructor(clazz);
        try {
            return c.newInstance();
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    Class<?> findClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    Constructor<?> getConstructor(Class<?> clazz) {
        try {
            return clazz.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    Method findMethod(Class<?> clazz, String method) {
        List<Method> methods = Arrays.stream(clazz.getMethods()).filter(m -> m.getName().equals(method)).toList();
        if (methods.size() != 1) {
            throw new RuntimeException("Many methods found with name " + method);
        }
        return methods.get(0);
    }

    Object[] convertArgumentsToExpectedTypes(Method method, List<?> rawArguments) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        int n = parameterTypes.length;
        assert rawArguments.size() == n : "amount of raw arguments " + rawArguments.size() + " differs from expected by method " + method + " " + n;
        Object[] out = new Object[n];
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> pt = parameterTypes[i];
            Object arg = rawArguments.get(i);
            Object argToOut;
            if (arg.getClass().isAssignableFrom(pt)) {
                argToOut = arg;
            } else if (pt.isAssignableFrom(int.class)) {
                argToOut = Integer.valueOf(String.valueOf(arg));
            } else {
                throw new RuntimeException("Assigning parameter type " + pt + " to argument " + arg + " failed");
            }
            out[i] = argToOut;
        }
        return out;
    }

    Object executeInstruction(Object application, String methodName, List<?> rawArguments) {
        if (!methodName.matches("[a-z].*")) {
            throw new RuntimeException("Not a camel case method name: " + methodName);
        }
        Method m = findMethod(application.getClass(), methodName);
        Object[] params = convertArgumentsToExpectedTypes(m, rawArguments);
        Object out;
        try {
            out = m.invoke(application, params);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return out;
    }


}

class TestingOf {
    @Test
    public void test() {
        String input = "Input\n" +
                "[\"leetcode.hard.TextEditor\", \"addText\", \"deleteText\", \"addText\", \"cursorRight\", \"cursorLeft\", \"deleteText\", \"cursorLeft\", \"cursorRight\"]\n" +
                "[[], [\"leetcode\"], [4], [\"practice\"], [3], [8], [10], [2], [6]]\n" +
                "Output\n" +
                "[null, null, 4, null, \"etpractice\", \"leet\", 4, \"\", \"practi\"]";

        LeetcodeApplicationTestWrapperHelper.from(input).execute();
    }
}