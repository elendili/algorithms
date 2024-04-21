package leetcode.hard;

public class TextEditor {

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
            if (c != start) {
                sb.append(c.c);
            }
            if (c == cursor) {
                sb.append('|');
            }
            c = c.right;
        }
        return sb.toString();
    }
}
