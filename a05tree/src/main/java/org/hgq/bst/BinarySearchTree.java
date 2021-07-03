package org.hgq.bst;

import org.hgq.bst.printer.BinaryTreeInfo;

import java.util.Comparator;

/**
 * @description: 二叉搜索树
 * @author: huangguoqiang
 * @create: 2021-07-03 11:30
 **/
public class BinarySearchTree<E> implements BinaryTreeInfo {

    private Node<E> root;

    private int size;

    private Comparator<E> comparator;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * 元素的数量
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 是否是空的
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空所有元素
     */
    public void clear() {

    }

    /**
     * 添加元素
     *
     * @param element
     */
    public void add(E element) {
        //找到根节点，从根节点开始比较

        //根节点是空的，添加第一个元素
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }

        Node<E> node = root;
        Node<E> parent = root;
        int cmp = 0;
        while (node != null) {
            //找到parent
            parent = node;

            //要插入的方向，左子树还是右子树
            cmp = compare(element, node.element);

            if (cmp > 0) {
                //新添加的元素大于父节点的元素，往右边找
                node = node.right;

            } else if (cmp < 0) {
                //新添加的元素小于父节点的元素，往左边找
                node = node.left;
            } else {
                //相等 不处理
                return;
            }
        }
        //插入新节点
        Node newNode = new Node(element, parent);
        if (cmp > 0) {
            //新添加的元素大于父节点的元素，右子树
            parent.right = newNode;
        } else if (cmp < 0) {
            //新添加的元素小于父节点的元素，左子树
            parent.left = newNode;
        }

        size++;

    }

    /**
     * if(e1>e2) return 1,
     * if(e1<e2) return -1,
     * if(e1==e2) return 0
     *
     * @param e1
     * @param e2
     * @return
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return this.comparator.compare(e1, e2);
        } else {
            return ((Comparable<E>) e1).compareTo(e2);
        }
    }

    /**
     * 删除元素
     *
     * @param element
     */
    public void remove(E element) {
    }

    /**
     * 是否包含指定元素
     *
     * @param element
     * @return
     */
    public boolean contains(E element) {
        return false;
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>)node).element;
    }


    private static class Node<E> {
        E element;
        Node<E> parent;
        Node<E> left;
        Node<E> right;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }


}
