package org.hgq;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-06-29 19:15
 **/
public class SinglyCircleLinkedList<E> extends AbstractCustList<E> {


    private Node<E> head;


    /**
     * 获取index位置的节点
     *
     * @param index
     * @return
     */
    public Node<E> getNode(int index) {
        rangCheck(index);
        // 0  1  2  3
        // 1->2->3->4
        Node first = head;
        for (int i = 0; i < index; i++) {
            first = first.next;
        }
        return first;
    }

    @Override
    public void add(E element) {

        add(size, element);
    }

    @Override
    public void add(int index, E element) {
        rangCheckForAdd(index);
        // 0  1  2  3
        // 1->2->3->4
        if (index == 0) {
            //往头部添加
            if (size == 0) {
                //空链表时候
                head = new Node<>(element, null);
                head.next = head;
            } else {
                //获取尾节点，让尾节点指向头节点
                Node<E> last = getNode(size - 1);
                head = new Node<>(element, head);
                last.next = head;
            }
        } else {
            Node<E> prev = getNode(index - 1);
            prev.next = new Node(element, prev.next);
        }
        size++;

    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E remove(int index) {
        rangCheck(index);
        E oldValue;
        if (index == 0) {
            oldValue = head.element;
            if (size == 1) {
                head = null;
            } else {
                Node<E> last = getNode(size - 1);
                head = head.next;
                last.next = head;
            }
        } else {
            Node<E> prev = getNode(index - 1);
            Node<E> old = prev.next;
            prev.next = old.next;
            oldValue = old.element;
        }
        size--;
        return oldValue;
    }

    @Override
    public int indexOf(E element) {
        return 0;
    }

    @Override
    public void clear() {

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
