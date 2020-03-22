package leetcode;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/word-ladder-ii/
public class WordLadder2 {
    class Solution {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            // prepare distances
            wordList = Stream.concat(wordList.stream(), Stream.of(beginWord)).collect(Collectors.toList());
            Map<String, Set<String>> dists = makeDistancesMap(wordList);
            if (!dists.containsKey(endWord)) {
                return Collections.emptyList();
            }
            class X {
                final X prev;
                final String word;
                final int length;

                X(String word, X prev) {
                    this.prev = prev;
                    this.word = word;
                    this.length = prev == null ? 1 : prev.length + 1;
                }

                boolean pathHasSameWord(String word) {
                    X p = this;
                    while (p != null) {
                        if (p.word.equals(word)) {
                            return true;
                        }
                        p = p.prev;
                    }
                    return false;
                }

                List<String> path() {
                    LinkedList<String> out = new LinkedList<>();
                    X x = this;
                    do {
                        out.addFirst(x.word);
                    } while ((x = x.prev) != null);
                    return out;
                }
            }
            LinkedList<X> q = new LinkedList<>();
            q.addLast(new X(beginWord, null));
            List<X> preOut = new ArrayList<>();
            Set<String> visited = new HashSet<>();
            int finalLength = Integer.MAX_VALUE;
            while (!q.isEmpty()) {
                X x = q.poll();
                if (
                        x.length <= dists.size()
                                && x.length <= finalLength
                                && !visited.contains(x.word)
                ) {
                    if (x.word.equals(endWord)) {
                        finalLength = x.length;
                        preOut.add(x);
                    } else {
                        Set<String> set = dists.get(x.word);
                        set.forEach(s -> {
                            if (x.pathHasSameWord(s)) {
                                visited.add(s);
                            } else {
                                X nX = new X(s, x);
                                q.addLast(nX);
                            }
                        });
                    }
                }
            }
            List<List<String>> out = new LinkedList<>();
            for (X x : preOut) {
                if (x.length == finalLength) {
                    out.add(x.path());
                }
            }
            return out;
        }

        Map<String, Set<String>> makeDistancesMap(List<String> wordList) {
            Map<String, Set<String>> d = new HashMap<>();
            for (int i = 0; i < wordList.size() - 1; i++) {
                String w1 = wordList.get(i);
                for (int j = i + 1; j < wordList.size(); j++) {
                    String w2 = wordList.get(j);
                    int di = distance(w1, w2);
                    if (di == 1) {
                        d.compute(w1, (k, v) -> {
                            v = (v == null) ? new HashSet<>() : v;
                            v.add(w2);
                            return v;
                        });
                        d.compute(w2, (k, v) -> {
                            v = (v == null) ? new HashSet<>() : v;
                            v.add(w1);
                            return v;
                        });
                    }
                }
            }
            return d;
        }

        int distance(String w1, String w2) {
            if (w1.length() != w2.length()) {
                return Integer.MAX_VALUE;
            }
            int out = 0;
            for (int i = 0; i < w1.length(); i++) {
                if (w1.charAt(i) != w2.charAt(i)) {
                    out += 1;
                }
            }
            return out;
        }
    }

    @Test
    public void test() {
        Solution s = new Solution();
        assertEquals("[]", s.findLadders("hit", "hot", Collections.emptyList()).toString());
        // hot->dot->dog
        assertEquals("[[hot, dot, dog]]", s.findLadders("hot", "dog", asList("hot", "dot", "dog")).toString());
        // hit->hot
        assertEquals("[[hit, hot]]", s.findLadders("hit", "hot", asList("hot", "hat", "hut", "het")).toString());
        // git->hit->hot
        assertEquals("[[git, hit, hot]]", s.findLadders("git", "hot", asList("gat", "hot", "het", "hit")).toString());
    }

    @Test
    public void test2() {
        Solution s = new Solution();
        // "hit" -> "hot" -> "dot" -> "dog" -> "cog"
        assertEquals("[[hit, hot, lot, log, cog], [hit, hot, dot, dog, cog]]", s.findLadders("hit", "cog", asList("hot", "dot", "dog", "lot", "log", "cog")).toString());
        assertEquals("[]", s.findLadders("hit", "cog", asList("hot", "dog", "log", "cog")).toString());
        assertEquals("[]", s.findLadders("hit", "cog", asList("hot", "dot", "dog", "lot", "log")).toString());
        assertEquals("[[red, ted, tex, tax], [red, ted, tad, tax], [red, rex, tex, tax]]",
                s.findLadders("red", "tax",
                        asList("ted", "tex", "red", "tax", "tad", "den", "rex", "pee")).toString());
    }

    @Test
    public void perfTest() {
        Solution s = new Solution();
        assertEquals("[[cet, cat, can, ian, inn, ins, its, ito, ibo, ibm, ism], [cet, get, gee, gte, ate, ats, its, ito, ibo, ibm, ism], [cet, cot, con, ion, inn, ins, its, ito, ibo, ibm, ism]]", s.findLadders("cet", "ism",
                asList("kid", "tag", "pup", "ail", "tun", "woo", "erg",
                        "luz", "brr", "gay", "sip", "kay", "per", "val",
                        "mes", "ohs", "now", "boa", "cet", "pal", "bar",
                        "die", "war", "hay", "eco", "pub", "lob", "rue",
                        "fry", "lit", "rex", "jan", "cot", "bid", "ali",
                        "pay", "col", "gum", "ger", "row", "won", "dan",
                        "rum", "fad", "tut", "sag", "yip", "sui", "ark",
                        "has", "zip", "fez", "own", "ump", "dis", "ads",
                        "max", "jaw", "out", "btu", "ana", "gap", "cry",
                        "led", "abe", "box", "ore", "pig", "fie", "toy",
                        "fat", "cal", "lie", "noh", "sew", "ono", "tam",
                        "flu", "mgm", "ply", "awe", "pry", "tit", "tie",
                        "yet", "too", "tax", "jim", "san", "pan", "map",
                        "ski", "ova", "wed", "non", "wac", "nut", "why",
                        "bye", "lye", "oct", "old", "fin", "feb", "chi",
                        "sap", "owl", "log", "tod", "dot", "bow", "fob",
                        "for", "joe", "ivy", "fan", "age", "fax", "hip",
                        "jib", "mel", "hus", "sob", "ifs", "tab", "ara",
                        "dab", "jag", "jar", "arm", "lot", "tom", "sax",
                        "tex", "yum", "pei", "wen", "wry", "ire", "irk",
                        "far", "mew", "wit", "doe", "gas", "rte", "ian",
                        "pot", "ask", "wag", "hag", "amy", "nag", "ron",
                        "soy", "gin", "don", "tug", "fay", "vic", "boo",
                        "nam", "ave", "buy", "sop", "but", "orb", "fen",
                        "paw", "his", "sub", "bob", "yea", "oft", "inn",
                        "rod", "yam", "pew", "web", "hod", "hun", "gyp",
                        "wei", "wis", "rob", "gad", "pie", "mon", "dog",
                        "bib", "rub", "ere", "dig", "era", "cat", "fox",
                        "bee", "mod", "day", "apr", "vie", "nev", "jam", "pam", "new", "aye", "ani", "and", "ibm", "yap", "can", "pyx", "tar", "kin", "fog", "hum", "pip", "cup", "dye", "lyx", "jog", "nun", "par", "wan", "fey", "bus", "oak", "bad", "ats", "set", "qom", "vat", "eat", "pus", "rev", "axe", "ion", "six", "ila", "lao", "mom", "mas", "pro", "few", "opt", "poe", "art", "ash", "oar", "cap", "lop", "may", "shy", "rid", "bat", "sum", "rim", "fee", "bmw", "sky", "maj", "hue", "thy", "ava", "rap", "den", "fla", "auk", "cox", "ibo", "hey", "saw", "vim", "sec", "ltd", "you", "its", "tat", "dew", "eva", "tog", "ram", "let", "see", "zit", "maw", "nix", "ate", "gig", "rep", "owe", "ind", "hog", "eve", "sam", "zoo", "any", "dow", "cod", "bed", "vet", "ham", "sis", "hex", "via", "fir", "nod", "mao", "aug", "mum", "hoe", "bah", "hal", "keg", "hew", "zed", "tow", "gog", "ass", "dem", "who", "bet", "gos", "son", "ear", "spy", "kit", "boy", "due", "sen", "oaf", "mix", "hep", "fur", "ada", "bin", "nil", "mia", "ewe", "hit", "fix", "sad", "rib", "eye", "hop", "haw", "wax", "mid", "tad", "ken", "wad", "rye", "pap", "bog", "gut", "ito", "woe", "our", "ado", "sin", "mad", "ray", "hon", "roy", "dip", "hen", "iva", "lug", "asp", "hui", "yak", "bay", "poi", "yep", "bun", "try", "lad", "elm", "nat", "wyo", "gym", "dug", "toe", "dee", "wig", "sly", "rip", "geo", "cog", "pas", "zen", "odd", "nan", "lay", "pod", "fit", "hem", "joy", "bum", "rio", "yon", "dec", "leg", "put", "sue", "dim", "pet", "yaw", "nub", "bit", "bur", "sid", "sun", "oil", "red", "doc", "moe", "caw", "eel", "dix", "cub", "end", "gem", "off", "yew", "hug", "pop", "tub", "sgt", "lid", "pun", "ton", "sol", "din", "yup", "jab", "pea", "bug", "gag", "mil", "jig", "hub", "low", "did", "tin", "get", "gte", "sox", "lei", "mig", "fig", "lon", "use", "ban", "flo", "nov", "jut", "bag", "mir", "sty", "lap", "two", "ins", "con", "ant", "net", "tux", "ode", "stu", "mug", "cad", "nap", "gun", "fop", "tot", "sow", "sal", "sic", "ted", "wot", "del", "imp", "cob", "way", "ann", "tan", "mci", "job", "wet", "ism", "err", "him", "all", "pad", "hah", "hie", "aim", "ike", "jed", "ego", "mac", "baa", "min", "com", "ill", "was", "cab", "ago", "ina", "big", "ilk", "gal", "tap", "duh", "ola", "ran", "lab", "top", "gob", "hot", "ora", "tia", "kip", "han", "met", "hut", "she", "sac", "fed", "goo", "tee", "ell", "not", "act", "gil", "rut", "ala", "ape", "rig", "cid", "god", "duo", "lin", "aid", "gel", "awl", "lag", "elf", "liz", "ref", "aha", "fib", "oho", "tho", "her", "nor", "ace", "adz", "fun", "ned", "coo", "win", "tao", "coy", "van", "man", "pit", "guy", "foe", "hid", "mai", "sup", "jay", "hob", "mow", "jot", "are", "pol", "arc", "lax", "aft", "alb", "len", "air", "pug", "pox", "vow", "got", "meg", "zoe", "amp", "ale", "bud", "gee", "pin", "dun", "pat", "ten", "mob"
                )).toString());
    }
}
