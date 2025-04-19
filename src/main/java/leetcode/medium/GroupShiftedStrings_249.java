package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class GroupShiftedStrings_249 {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> out = new ArrayList<>();
        for (String s : strings) {
            for (List<String> group : out) {
                if (isInSameShiftingGroup(s, group.get(0))){
                    group.add(s);
                    s = null;
                    break;
                }
            }
            if (s!=null) {
                List<String> g = new ArrayList<>();
                g.add(s);
                out.add(g);
            }
        }
        return out;
    }

    boolean isInSameShiftingGroup(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int firstDiff = getDiff(a.charAt(0),b.charAt(0));
        for (int i = 1; i < a.length(); i++) {
            //. ["az"],["ba"]
            int diff = getDiff(a.charAt(i),b.charAt(i));
            if (diff != firstDiff) {
                return false;
            }
        }
        return true;
    }
    int getDiff(char a,char b){
        int diff = a-b;
        diff = (26 + diff)%26;
        return diff;
    }
}
