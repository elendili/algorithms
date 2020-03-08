package crackingCodeInterview.SingleLinkedList;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
/* IdentityHashMap is wow
* */
public class IntersectionOfSingleLinkedLists {
    public Node getIntersectionNode(Node a, Node b){
        Set<Node> setA = Collections.newSetFromMap( new IdentityHashMap<>());
        while(a!=null){
            setA.add(a);
            a=a.next;
        }
        while(b!=null){
            if(setA.contains(b)){
                return b;
            }
            b=b.next;
        }
        return null;
    }

    @Test
    public void test(){
        Node e=new Node().data(15);
        Node d=new Node().data(14).next(e);
        Node c=new Node().data(13).next(d);
        Node b=new Node().data(12).next(c);
        Node a=new Node().data(11).next(b);

        Node b2=new Node().data(22).next(c);
        Node a2=new Node().data(21).next(b2);
        Node z=new Node().data(21);

        Assert.assertSame(c, getIntersectionNode(a, a2));
        Assert.assertSame(null, getIntersectionNode(a, z));
    }
}
