package leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/problems/design-a-text-editor/description/
 */
public class DesignTextEditor {

    @Test
    public void test() {
/**
 * ["TextEditor", "addText", "deleteText", "addText", "cursorRight", "cursorLeft", "deleteText", "cursorLeft", "cursorRight"]
 * [[], ["leetcode"], [4], ["practice"], [3], [8], [10], [2], [6]]
 * Output
 * [null, null, 4, null, "etpractice", "leet", 4, "", "practi"]
 */
        TextEditor te = new TextEditor();
        te.addText("leetcode");
        Assertions.assertEquals("leetcode|", te.toString());
        Assertions.assertEquals(4, te.deleteText(4));
        Assertions.assertEquals("leet|", te.toString());
        te.addText("practice");
        Assertions.assertEquals("leetpractice|", te.toString());
        Assertions.assertEquals("etpractice", te.cursorRight(3));
        Assertions.assertEquals("leetpractice|", te.toString());
        Assertions.assertEquals("leet", te.cursorLeft(8));
        Assertions.assertEquals("leet|practice", te.toString());
        Assertions.assertEquals(4, te.deleteText(10));
        Assertions.assertEquals("|practice", te.toString());
        Assertions.assertEquals("", te.cursorLeft(2));
        Assertions.assertEquals("practi", te.cursorRight(6));
        Assertions.assertEquals("practi|ce", te.toString());


        Assertions.assertEquals("practice", te.cursorRight(100));
        Assertions.assertEquals("practice|", te.toString());
        Assertions.assertEquals(8, te.deleteText(100));
        Assertions.assertEquals("|", te.toString());
        Assertions.assertEquals("", te.cursorLeft(100));
        Assertions.assertEquals("", te.cursorRight(100));

        te.addText("leetcode");
        Assertions.assertEquals("leetcode|", te.toString());
        Assertions.assertEquals("leet", te.cursorLeft(4));
        Assertions.assertEquals("leet|code", te.toString());
        te.addText("xxx");
        Assertions.assertEquals("leetxxx|code", te.toString());
        Assertions.assertEquals(3, te.deleteText(3));
        Assertions.assertEquals("leet|code", te.toString());
    }

}

