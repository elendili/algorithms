package helpers;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
            return method + "(" + inp + ") => " + expected;
        }
    }

    public void execute() {
        Instruction appCreationInstr = instructions.get(0);
        Object app = createApp(appCreationInstr.method(), appCreationInstr.inputArguments());
        instructions.stream().skip(1).forEach(i -> {
                    Object actual = executeInstruction(app, i.method(), i.inputArguments());
                    String actualString = String.valueOf(actual);
                    String expectedString = String.valueOf(i.expected);
                    System.out.println(expectedString + ", " + actualString);
                    assertEquals(expectedString, actualString, "on instr " + i);
                }
        );
    }

    Class<?> findClass(String className) {
        Optional<Class<?>> opt = classForName(className);
        if(opt.isEmpty()){
            opt = findClassInStacktrace(className);
        }
        return opt.get();
    }
    Optional<Class<?>> findClassInStacktrace(String className) {
        List<StackTraceElement> stes = Arrays.asList(new RuntimeException().getStackTrace());
        Optional<Class<?>> outOpt = stes.stream()
                .map(e->classForName(e.getClassName()).get())
                .flatMap(e->Arrays.stream(e.getDeclaredClasses()))
                .filter(e -> e.getName().replaceAll("^.*(\\.|\\$)", "").equals(className))
                .findFirst();
        return outOpt;
    }

    Optional<Class<?>> classForName(String className) {
        try {
            return Optional.of(Class.forName(className));
        } catch (ClassNotFoundException e) {
            return Optional.empty();
        }
    }

    Method findMethod(Class<?> clazz, String method) {
        List<Method> methods = Arrays.stream(clazz.getMethods()).filter(m -> m.getName().equals(method)).toList();
        if (methods.size() != 1) {
            throw new RuntimeException("Many methods "+methods.size()+" found with name " + method);
        }
        return methods.get(0);
    }

    Object[] convertArgumentsToExpectedTypes(Executable executable, List<?> rawArguments) {
        Class<?>[] parameterTypes = executable.getParameterTypes();
        int n = parameterTypes.length;
        assert rawArguments.size() == n : "amount of raw arguments " + rawArguments.size() + " differs from expected by method " + executable + " " + n;
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

    Object createApp(String className, List<?> rawArguments) {
        if (!className.matches("[A-Z].*")) {
            throw new RuntimeException("Not a camel case Class name: " + className);
        }
        Class<?> clazz = findClass(className);

        Constructor<?>[] constructorsArray = clazz.getConstructors();
        Object[] args = convertArgumentsToExpectedTypes(constructorsArray[0], rawArguments);


        Method m = findMethod(clazz, "init");
        Object[] params = convertArgumentsToExpectedTypes(m, rawArguments);
        Object out=null;
//        try {
//            out = m.invoke(application, params);
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            throw new RuntimeException(e);
//        }
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