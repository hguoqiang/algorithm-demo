package org.hgq.linked;

/**
 * @description: 给定一个链表，判断链表中是否有环。
 * https://leetcode-cn.com/problems/linked-list-cycle/
 * @author: huangguoqiang
 * @create: 2021-06-25 14:42
 **/
public class CycleList141 {


    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node1 = new ListNode(1);
        ListNode node9 = new ListNode(9);
        head.next = node5;
        node5.next = node1;
        node1.next = node9;
        //node9.next = node5;
        System.out.println(new CycleList141().hasCycle(head));
    }

    public boolean hasCycle(ListNode head) {

        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow.equals(fast)) {
                return true;
            }
        }
        return false;

    }
}
