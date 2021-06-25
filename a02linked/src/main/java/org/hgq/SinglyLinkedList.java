package org.hgq;

/**
 * @description: 单向链表
 * @author: huangguoqiang
 * @create: 2021-06-24 11:08
 **/
public class SinglyLinkedList<E> extends AbstractCustList<E> {

    private Node<E> head;


    @Override
    public void add(E element) {
        if (size == 0) {
            head = new Node(element, null);
            size++;
        } else {
            add(size, element);
        }
    }

    /**
     * 最好O(1)，添加第一个元素
     * 最坏O(n)，添加最后一个元素
     * 平均O(n)
     * @param element
     */
    @Override
    public void add(int index, E element) {
        rangCheckForAdd(index);

        // 创建当前element节点 curNode
        // 找到 (index-1) 位置的节点 prev
        // 得到 index位置的节点(indexOldNode=prev.next)
        // 改变 prev.next 指向为新创建的curNode
        // curNode.next 指向 indexOldNode

        //index  0 1 2 3 4 5 6
        Node prev = getPreviousNode(index);
        prev.next = new Node(element, prev.next);
        size++;
    }

    /**
     * 最好O(1)，设置第一个元素
     * 最坏O(n)，设置最后一个元素
     * 平均O(n)
     * @param index
     * @return
     */
    @Override
    public E set(int index, E element) {
        rangCheck(index);

        if (index == 0) {
            E oldValue = head.element;
            head = new Node(element, head.next);
            return oldValue;
        }
        Node prev = getPreviousNode(index);
        Node<E> indexNode = prev.next;
        prev.next = new Node(element, indexNode.next);
        return indexNode.element;
   }

    /**
     * 最好O(1)，获取第一个元素
     * 最坏O(n)，获取最后一个元素
     * 平均O(n)
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        rangCheck(index);
        if (index == 0) {
            return head.element;
        }
        Node prev = getPreviousNode(index);
        return (E) prev.next.element;
    }

    /**
     * 最好O(1)，删除第一个元素
     * 最坏O(n)，删除最后一个元素
     * 平均O(n)
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        rangCheck(index);
        if (index == 0) {
            E delValue = head.element;
            head = head.next;
            size--;
            return delValue;
        }
        Node prev = getPreviousNode(index);
        Node delNode = prev.next;
        E delValue = (E) delNode.element;
        prev.next = delNode.next;
        size--;

        return delValue;
    }

    /**
     * 获取指定index位置的前一个元素
     * 最好O(1)，获取第一个元素
     * 最坏O(n)，获取最后一个元素
     * 平均O(n)
     * @param index
     * @return
     */
    private Node getPreviousNode(int index) {
        Node prev = head;
        for (int i = 1; i < index; i++) {
            prev = prev.next;
        }
        return prev;
    }

    @Override
    public int indexOf(E element) {

        if (head.element.equals(element)) {
            return 0;
        }
        Node curNode = head;
        for (int i = 1; i < size; i++) {
            curNode = curNode.next;
            if (curNode.element.equals(element)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size:")
                .append(size)
                .append(" [");
        Node tmp = head;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(",");
            }

            Object element = tmp.element;
            sb.append(element);
            tmp = tmp.next;
        }

        sb.append("]");
        return sb.toString();
    }

    private static class Node<E> {
        E element;
        Node next;

        public Node(E element, Node next) {
            this.element = element;
            this.next = next;
        }
    }
}
