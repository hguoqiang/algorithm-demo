package org.hgq.linked;

/**
 * @description: 移除链表元素 https://leetcode-cn.com/problems/remove-linked-list-elements/
 * @author: huangguoqiang
 * @create: 2021-06-30 13:41
 **/
public class RemoveElements203 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node5 = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node9 = new ListNode(3);
        ListNode node55 = new ListNode(3);
        ListNode node6 = new ListNode(4);
        head.next = node5;
        node5.next = node1;
        node1.next = node9;
        node9.next = node55;
        node55.next = node6;
        System.out.println(head);
        RemoveElements203 solution = new RemoveElements203();
        System.out.println(solution.removeElements3(head, 4));
    }

    /**
     * 删除头结点时另做考虑（由于头结点没有前一个结点）
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {

        //如果头节点是待删除的元素，让头节点指向下一个节点，可能新的头节点也是待删除元素
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            return head;
        }

        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }

        return head;
    }

    /**
     * 添加一个虚拟头结点，删除头结点就不用另做考虑
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements2(ListNode head, int val) {
        ListNode dummyHead = new ListNode(val - 1);
        dummyHead.next = head;
        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return dummyHead.next;
    }

    /**
     * 递归
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements3(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        head.next = removeElements3(head.next, val);
        if (head.val == val) {
            return  head.next;
        } else {
            return head;
        }
    }

}
