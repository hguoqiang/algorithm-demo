package org.hgq.linked;


/**
 * 删除给定的节点
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */

public class DeleteNode237 {

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node1 = new ListNode(1);
        ListNode node9 = new ListNode(9);
        head.next = node5;
        node5.next = node1;
        node1.next = node9;

        System.out.println(head);
        DeleteNode237 solution = new DeleteNode237();
        solution.deleteNode(head);
        System.out.println(head);
    }

    public void deleteNode(ListNode node) {
        //获取不到待删除 node 的前一个，所以把待删除node 的next 节点的值 赋值给 待删除node，然后把待删除node的next 指向node.next.next
        if (node.next != null) {
            node.val = node.next.val;
            node.next = node.next.next;
        }

    }




}