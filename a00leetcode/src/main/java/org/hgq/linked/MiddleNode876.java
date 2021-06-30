package org.hgq.linked;

/**
 * @description: https://leetcode-cn.com/problems/middle-of-the-linked-list/
 * @author: huangguoqiang
 * @create: 2021-06-30 15:18
 **/
public class MiddleNode876 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node5 = new ListNode(2);
        ListNode node1 = new ListNode(3);
        ListNode node9 = new ListNode(4);
        ListNode node55 = new ListNode(5);
        // ListNode node6 = new ListNode(6);
        head.next = node5;
        node5.next = node1;
        node1.next = node9;
        node9.next = node55;
        //node55.next = node6;
        System.out.println(head);
        MiddleNode876 solution = new MiddleNode876();
        System.out.println(solution.middleNode2(head));
    }

    /**
     * 单指针法
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        int n = 0;

        ListNode node = head;
        while (node != null) {
            n++;
            node = node.next;
        }
        int middle = n / 2;
        int c = 0;

        node = head;
        while (c < middle) {
            c++;
            node = node.next;
        }
        return node;
    }

    /**
     * 快慢指针法
     *
     * @param head
     * @return
     */
    public ListNode middleNode2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
