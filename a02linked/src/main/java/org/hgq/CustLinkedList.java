package org.hgq;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-06-28 16:02
 **/
public class CustLinkedList<E> extends AbstractCustList<E> {

    private Node<E> head;
    private Node<E> tail;

    public CustLinkedList() {
    }

    @Override
    public void add(E element) {
        addLast(element);
    }

    @Override
    public void add(int index, E element) {
        rangCheckForAdd(index);
        if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            // 0  1  2  3
            // 1->2->3->4    在 index=2的位置插入 100
            // 1->2->100->3->4    在 index=2的位置插入 100
            // 找到原位置的节点
            Node<E> oldNode = getNode(index);

            Node prev = oldNode.prev;
            Node newNode = new Node(prev, element, oldNode);
            prev.next = newNode;
            oldNode.prev = newNode;

            size++;
        }


    }

    public void addFirst(E element) {
        Node first = head;
        Node<E> newNode = new Node<>(null, element, first);
        head = newNode;
        if (first == null) {
            tail = newNode;
        } else {
            first.prev = newNode;
        }
        size++;

    }

    public void addLast(E element) {
        Node last = tail;
        Node<E> newNode = new Node<>(last, element, null);
        tail = newNode;
        if (last == null) {
            head = newNode;
        } else {
            last.next = newNode;
        }

        size++;

    }


    @Override
    public E set(int index, E element) {

        Node<E> oldNode = getNode(index);
        oldNode.element = element;
        return oldNode.element;
    }

    @Override
    public E get(int index) {
        if (index == 0) {
            return head.element;
        } else if (index == size - 1) {
            return tail.element;
        } else {
            return getNode(index).element;
        }
    }

    private Node<E> getNode(int index) {
        rangCheck(index);

        if (index < (size >> 1)) {
            //从头部开始查找
            return getNodeFormHead(index);
        } else {
            //从尾部查找
            return getNodeFormTail(index);
        }
    }

    private Node<E> getNodeFormHead(int index) {
        // 0  1  2  3
        // 1->2->3->4
        //index=3 , i=0 ,node =2
        //index=3 , i=1 ,node =3
        //index=3 , i=2 ,node =4
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private Node<E> getNodeFormTail(int index) {
        // 0  1  2  3
        // 1->2->3->4
        //index=1 , i=3 ,node =3
        //index=1 , i=2 ,node =2
        Node<E> node = tail;
        for (int i = size - 1; i > index; i--) {
            node = node.prev;
        }
        return node;
    }

    @Override
    public E remove(int index) {
        Node<E> oldNode = getNode(index);
        E element = oldNode.element;
        Node prev = oldNode.prev;
        Node next = oldNode.next;
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            oldNode.prev = null;
        }
        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            oldNode.next = null;
        }
        oldNode.element = null;

        size--;
        return element;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                Node<E> node = getNode(i);
                if (node.element == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                Node<E> node = getNode(i);
                if (node.element.equals(element)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        Node<E> cur = this.head;
        for (int i = 0; i < size; i++) {
            Node tmp = cur.next;
            cur.element = null;
            cur.prev = null;
            cur.next = null;
            cur = tmp;
        }
        head = null;
        tail = null;
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
        Node prev;
        Node next;

        public Node(Node prev, E element, Node next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }
}
