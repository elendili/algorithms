package leetcode.easy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/destination-city/description/
 */
public class DestinationCity {
    public String destCity(List<List<String>> paths) {
        Set<String> citiesFrom = new HashSet<>();
        for (List<String> l : paths) {
            citiesFrom.add(l.get(0));
        }
        for (List<String> l : paths) {
            if(!citiesFrom.contains(l.get(1))){
                return l.get(1);
            }
        }
        return "";
    }
}
