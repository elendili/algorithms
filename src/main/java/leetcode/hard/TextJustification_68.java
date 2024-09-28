package leetcode.hard;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/text-justification/
 * <p>
 * Given an array of strings words and a width maxWidth, format the text such that
 * each line has exactly maxWidth characters and is fully (left and right) justified.
 * <p>
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
 * Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 * <p>
 * Extra spaces between words should be distributed as evenly as possible.
 * If the number of spaces on a line does not divide evenly between words,
 * the empty slots on the left will be assigned more spaces than the slots on the right.
 * <p>
 * For the last line of text, it should be left-justified, and no extra space is inserted between words.
 * <p>
 * Note:
 * <p>
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 */
public class TextJustification_68 {
    final StringBuilder lineBuilder = new StringBuilder();


    // read from left, count words and their length with space
    // when length more than maxWidth:
    // "drop" last word and calculate required amount of additional spaces and divide the amount using amount of gaps (words in line count - 1)
    // when still words exist
    // when words ended - put all words using 1 space.
    public List<String> fullJustify(String[] words, int maxWidth) {
        int wordsCount = words.length;
        int lineLength = 0;
        int firstWordInLineIndex = 0;
        List<String> out = new ArrayList<>();
        int wi = 0;
        for (wi = 0; wi < wordsCount; wi++) {
            String word = words[wi];
            int lineLengthWithCurrentWord = lineLength + word.length();
            lineLengthWithCurrentWord += ((lineLength > 0) ? 1 : 0); // add space
            if (lineLengthWithCurrentWord > maxWidth) {
                wi--; // drop last word
                String line = fullyJustifiedLine(words, firstWordInLineIndex, wi, maxWidth, lineLength);
                out.add(line);
                firstWordInLineIndex = wi + 1;
                lineLength = 0;
            } else if (lineLengthWithCurrentWord == maxWidth || wi == wordsCount - 1) {
                String line = leftJustifiedLine(words, firstWordInLineIndex, wi, maxWidth);
                out.add(line);
                firstWordInLineIndex = wi + 1;
                lineLength = 0;
            } else {
                lineLength = lineLengthWithCurrentWord;
            }

        }

        return out;
    }

    String fullyJustifiedLine(String[] words, int firstWordInLineIndex, int lastIndex, int maxWidth, int minLineLength) {

        // full justified
        // calc extra space
        int gapsCount = lastIndex - firstWordInLineIndex;
        int extraSpacesCount = (maxWidth - minLineLength);
        int minExtraEveryGapSpaceCount = extraSpacesCount;
        int leftOvers = 0;
        if (gapsCount > 0) {
            minExtraEveryGapSpaceCount = extraSpacesCount / gapsCount;
            leftOvers = extraSpacesCount - (minExtraEveryGapSpaceCount*gapsCount);
        }

        // create line
        for (int j = firstWordInLineIndex; j <= lastIndex; j++) {
            lineBuilder.append(words[j]);
            if (j < lastIndex && lineBuilder.length() < maxWidth) {
                lineBuilder.append(' ');
                for (int i = 0; i < minExtraEveryGapSpaceCount && extraSpacesCount > 0; i++) {
                    lineBuilder.append(' ');
                    extraSpacesCount--;
                }
                if(leftOvers>0){
                    lineBuilder.append(' ');
                    leftOvers--;
                    extraSpacesCount--;
                }
            }
        }
        addExtraSpaces(maxWidth);
        String out = lineBuilder.toString();
        lineBuilder.setLength(0);
        return out;
    }

    String leftJustifiedLine(String[] words, int firstWordInLineIndex, int lastWordIndex, int maxWidth) {
        for (int j = firstWordInLineIndex; j <= lastWordIndex; j++) {
            lineBuilder.append(words[j]);
            if (j < lastWordIndex) {
                lineBuilder.append(' ');
            }
        }
        addExtraSpaces(maxWidth);
        String out = lineBuilder.toString();
        lineBuilder.setLength(0);
        return out;
    }

    void addExtraSpaces(int maxWidth) {
        // add extra spaces
        while (lineBuilder.length() < maxWidth) {
            lineBuilder.append(' ');
        }
    }

    @Test
    public void preTest() {
        assertEquals(asList("This", "is  "), fullJustify(new String[]{"This", "is"}, 4));
        assertEquals(asList("This is"), fullJustify(new String[]{"This", "is"}, 7));
        assertEquals(asList("This is "), fullJustify(new String[]{"This", "is"}, 8));
        assertEquals(asList("This is "), fullJustify(new String[]{"This", "is"}, 8));
    }

    @Test
    public void preTest2() {
        assertEquals(asList("a b", "c  "), fullJustify(new String[]{"a", "b", "c"}, 3));
        assertEquals(asList("a", "b", "c"), fullJustify(new String[]{"a", "b", "c"}, 1));
        assertEquals(asList("a ", "b ", "c "), fullJustify(new String[]{"a", "b", "c"}, 2));
        assertEquals(asList("ab", "cd", "ef"), fullJustify(new String[]{"ab", "cd", "ef"}, 2));
        assertEquals(asList("ab ", "cd ", "ef "), fullJustify(new String[]{"ab", "cd", "ef"}, 3));
        assertEquals(asList("ab  ", "cd  ", "ef  "), fullJustify(new String[]{"ab", "cd", "ef"}, 4));
        assertEquals(asList("ab cd", "ef   "), fullJustify(new String[]{"ab", "cd", "ef"}, 5));
        assertEquals(asList("ab  cd", "ef    "), fullJustify(new String[]{"ab", "cd", "ef"}, 6));
        assertEquals(asList("ab   cd", "ef     "), fullJustify(new String[]{"ab", "cd", "ef"}, 7));
        assertEquals(asList("ab cd ef"), fullJustify(new String[]{"ab", "cd", "ef"}, 8));
        assertEquals(asList("ab cd ef", "gh ij   "), fullJustify(new String[]{"ab", "cd", "ef", "gh", "ij"}, 8));
        assertEquals(asList("a    ", "aaaaa"), fullJustify(new String[]{"a", "aaaaa"}, 5));
    }

    @Test
    public void preTest3() {
        assertEquals(asList(
                "a    aa",
                "aaa    ",
                "aaaa   ",
                "aaaaa  ",
                "aaaaaa ",
                "aaaaaaa"
        ), fullJustify(new String[]{"a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa"}, 7));
        assertEquals(asList(
                "a  aa aaa",
                "aaaa     ",
                "aaaaa    ",
                "aaaaaa   ",
                "aaaaaaa  "
        ), fullJustify(new String[]{"a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa"}, 9));
        assertEquals(asList(
                "aaaaaaa  ",
                "aaaaaa   ",
                "aaaaa    ",
                "aaaa  aaa",
                "aa a     "
        ), fullJustify(new String[]{"aaaaaaa", "aaaaaa", "aaaaa", "aaaa", "aaa", "aa", "a"}, 9));
    }

    @Test
    public void test() {
        assertEquals(asList("This    is    an",
                        "example  of text",
                        "justification.  "),
                fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16));
        assertEquals(asList("What   must   be",
                        "acknowledgment  ",
                        "shall be        "),
                fullJustify(new String[]{"What", "must", "be", "acknowledgment", "shall", "be"}, 16));

        assertEquals(asList("Science  is  what we",
                        "understand      well",
                        "enough to explain to",
                        "a  computer.  Art is",
                        "everything  else  we",
                        "do                  "),
                fullJustify(new String[]{"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"}, 20));

    }

    @Test
    public void test2() {
        assertEquals(asList(
                        "The     important",
                        "thing  is  not to",
                        "stop questioning.",
                        "Curiosity has its",
                        "own   reason  for",
                        "existing.        "
                ),
                fullJustify(new String[]{"The", "important", "thing", "is", "not", "to", "stop", "questioning.", "Curiosity", "has", "its", "own", "reason", "for", "existing."}, 17));
    }

    @Test
    public void test3() {
        String expected = String.join("\n", asList(
                "Give  me  my  Romeo; and,",
                "when  he  shall die, Take",
                "him  and  cut  him out in",
                "little stars, And he will",
                "make  the  face of heaven",
                "so   fine  That  all  the",
                "world  will  be  in  love",
                "with  night  And  pay  no",
                "worship   to  the  garish",
                "sun.                     "
        ));
        List<String> actualList = fullJustify(new String[]{"Give", "me", "my", "Romeo;", "and,", "when", "he", "shall", "die,", "Take", "him", "and", "cut", "him", "out", "in", "little", "stars,", "And", "he", "will", "make", "the", "face", "of", "heaven", "so", "fine", "That", "all", "the", "world", "will", "be", "in", "love", "with", "night", "And", "pay", "no", "worship", "to", "the", "garish", "sun."}, 25);
        String actual = String.join("\n", actualList);
        assertEquals(expected, actual);
    }
}
