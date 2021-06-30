package org.hgq.linked;

/**
 * @description: https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 * @author: huangguoqiang
 * @create: 2021-06-30 15:00
 **/
public class DeleteDuplicates83 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node5 = new ListNode(1);
        ListNode node1 = new ListNode(1);
        ListNode node9 = new ListNode(3);
        ListNode node55 = new ListNode(3);
        ListNode node6 = new ListNode(4);
        head.next = node5;
        node5.next = node1;
        node1.next = node9;
        node9.next = node55;
        node55.next = node6;
        System.out.println(head);
        DeleteDuplicates83 solution = new DeleteDuplicates83();
        System.out.println(solution.deleteDuplicates(head));
    }


    public ListNode deleteDuplicates(ListNode head) {
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.val == prev.next.val) {
                prev.next = prev.next.next;
            }else {
                prev = prev.next;
            }
        }
        return head;
    }

}
