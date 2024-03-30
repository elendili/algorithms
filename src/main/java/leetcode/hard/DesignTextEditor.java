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

class TextEditor {

    static class LinkedNode {
        char c;
        LinkedNode left;
        LinkedNode right;

        public LinkedNode(char c) {
            this.c = c;
        }

        public LinkedNode(char c, LinkedNode left, LinkedNode right) {
            this.c = c;
            this.left = left;
            this.right = right;
        }
    }

    final LinkedNode start;
    LinkedNode cursor;

    public TextEditor() {
        this.start = new LinkedNode('.');
        cursor = start;
    }

    public void addText(String text) {
        int n = text.length();
        LinkedNode rightFromOldCursor = cursor.right;
        for (int i = 0; i < n; i++) {
            LinkedNode next = new LinkedNode(text.charAt(i));
            cursor.right = next;
            next.left = cursor;
            cursor = next;
        }
        cursor.right = rightFromOldCursor;
        if (rightFromOldCursor != null) {
            rightFromOldCursor.left = cursor;
        }
    }

    public int deleteText(int k) {
        int i = 0;
        LinkedNode rightFromOldCursor = cursor.right;
        for (; i < k && cursor != start; i++) {
            cursor = cursor.left;
        }
        cursor.right = rightFromOldCursor;
        if (rightFromOldCursor != null) {
            rightFromOldCursor.left = cursor;
        }
        return i;
    }

    public String cursorLeft(int k) {
        for (int i = 0; i < k && cursor != start; i++) {
            cursor = cursor.left;
        }
        return getLeftOfCursor();
    }

    public String cursorRight(int k) {
        for (int i = 0; i < k && cursor.right != null; i++) {
            cursor = cursor.right;
        }
        return getLeftOfCursor();
    }

    private String getLeftOfCursor() {
        StringBuilder sb = new StringBuilder();
        LinkedNode c = cursor;
        for (int i = 0; i < 10 && c != start && c != null; i++) {
            sb.append(c.c);
            c = c.left;
        }
        return sb.reverse().toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        LinkedNode c = start;
        while (c != null) {
            if(c!=start) {
                sb.append(c.c);
            }
            if(c==cursor){
                sb.append('|');
            }
            c = c.right;
        }
        return sb.toString();
    }
}