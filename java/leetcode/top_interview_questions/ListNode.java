package leetcode.top_interview_questions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public  ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public String toString(){
        StringBuilder out= new StringBuilder();
        ListNode ln = this;
        while(ln!=null){
            out.append(ln.val).append(">");
            ln=ln.next;
        }
        return out.toString();
    }

    public static ListNode genListFromZeroToNum(int n) {
        return genListFromRange(1,n);
    }

    public static ListNode genListFromRange(int start, int end) {
        assert start<=end;
        ListNode head = new ListNode(start);;
        if(start==end){
            return head;
        }
        ListNode cur = head;
        for(int i=start+1;i<=end;i++){
            cur.next=new ListNode(i);
            cur = cur.next;
        }
        return head;
    }

    public static ListNode genListFromNums(int... ints) {
        if(ints.length==0){
            return new ListNode();
        }
        ListNode head = new ListNode(ints[0]);;
        ListNode cur = head;
        for(int i=1;i<ints.length;i++){
            cur.next=new ListNode(ints[i]);
            cur = cur.next;
        }
        return head;
    }

    static class Test4ListNode{
        @Test
        public void test(){
            assertEquals("1>", genListFromRange(1,1).toString());
            assertEquals("1>2>", genListFromRange(1,2).toString());
            assertEquals("1>2>3>", genListFromRange(1,3).toString());
            assertEquals("3>4>5>", genListFromRange(3,5).toString());
        }
    }

}
