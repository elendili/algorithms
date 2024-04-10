package helpers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    static private List<Instruction> parse(String string) {
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
    }

    void execute() {
        Object app = createApp(instructions.get(0));
        instructions.stream().skip(1).forEach(i -> executeInstruction(app, i));
    }

    Object createApp(Instruction instruction) {
        Instruction creationInstruction = instruction;
        Class<?> clazz = findClass(creationInstruction.method());
        Constructor<?> c = getConstructor(clazz);
        try {
            return c.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    Class<?> findClass(String className){
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    Constructor<?> getConstructor(Class<?> clazz){
        try {
            return clazz.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    void executeInstruction(Object application, Instruction instruction) {
        if (instruction.method.matches("[A-Z].*")) {
            // call instructor
        }
    }

    @org.junit.jupiter.api.Test
    public void test() {
        String input = "Input\n" +
                "[\"TextEditor\", \"addText\", \"deleteText\", \"addText\", \"cursorRight\", \"cursorLeft\", \"deleteText\", \"cursorLeft\", \"cursorRight\"]\n" +
                "[[], [\"leetcode\"], [4], [\"practice\"], [3], [8], [10], [2], [6]]\n" +
                "Output\n" +
                "[null, null, 4, null, \"etpractice\", \"leet\", 4, \"\", \"practi\"]";

        List<Instruction> actual = parse(input);

        String expected = "[Instruction[method=\"TextEditor\", inputArguments=[], expected=null], " +
                "Instruction[method=\"addText\", inputArguments=[leetcode], expected=null], " +
                "Instruction[method=\"deleteText\", inputArguments=[4], expected=4], " +
                "Instruction[method=\"addText\", inputArguments=[practice], expected=null], " +
                "Instruction[method=\"cursorRight\", inputArguments=[3], expected=etpractice], " +
                "Instruction[method=\"cursorLeft\", inputArguments=[8], expected=leet], " +
                "Instruction[method=\"deleteText\", inputArguments=[10], expected=4], " +
                "Instruction[method=\"cursorLeft\", inputArguments=[2], expected=], " +
                "Instruction[method=\"cursorRight\", inputArguments=[6], expected=practi]]";
        assertEquals(expected, actual.toString());

        LeetcodeApplicationTestWrapperHelper.from(input).execute();
    }
}
