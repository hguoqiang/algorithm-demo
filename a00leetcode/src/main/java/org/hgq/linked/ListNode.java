package org.hgq.linked;

/**
 * Definition for singly-linked list.
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(val);
        ListNode node = next;
        while (node != null) {
            sb.append(",");
            sb.append(node.val);
            node = node.next;

        }
        sb.append("]");
        return sb.toString();
    }
}