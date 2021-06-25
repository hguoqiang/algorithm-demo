package org.hgq.linked;

/**
 * 反转一个链表
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
class ReverseList206 {

    public static void main(String[] args) {
        //new ListNode(3, new ListNode(4))
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        System.out.println(head);
        System.out.println(new ReverseList206().reverseList2(head));
    }

    /**
     * 递归的方式反转链表
     *
     * @param head
     * @return
     */
    // 5 4 3 2 1 >> 1->2->3->4->5
    //把head.next 看作是一个整体，一个newHead，使这个整体的 next 指向head，head.next指向null
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList1(head.next);

        head.next.next = head;
        head.next = null;
        return newHead;
    }


    /**
     * 循环方式
     * 5 4 3 2 1 >> 1->2->3->4->5
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }

        return newHead;
    }


}