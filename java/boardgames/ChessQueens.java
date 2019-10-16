package boardgames;

import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/* place queens safely on board */
public class ChessQueens {

    List<Map<Integer,Integer>> board(int width, int height){
        // map x->y
        List<Map<Integer,Integer>> positions = new ArrayList<>();

        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,0);
        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
                if(!map.containsKey(x)){
                    map.put(x,y);
                }
            }

        }

        return positions;
    }

    @Test
    public void test(){
        assertEquals(0,board(3,3).size());
        assertEquals(2,board(4,4).size());
        assertEquals(92,board(8,8).size());
    }
}
