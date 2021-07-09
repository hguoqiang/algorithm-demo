package org.hgq.bst;

import org.hgq.tree.BinaryTree;

import java.util.Comparator;

/**
 * @description: 二叉搜索树，左子树的所有节点都小于父节点，右子树的所有节点都大于父节点
 * @author: huangguoqiang
 * @create: 2021-07-08 13:36
 **/
public class BST<E> extends BinaryTree<E> {

    /**
     * 二叉搜索树是必须可比较的
     * 要么构建二叉搜索树时传入一个比较器
     * 要么二叉树所存放的元素就是可比较的
     */
    private Comparator comparator;

    public BST() {
    }

    public BST(Comparator comparator) {
        this.comparator = comparator;
    }

    /**
     * 是否包含指定元素
     *
     * @param element
     * @return
     */
    @Override
    protected boolean contains(E element) {
        return getNode(element) != null;
    }

    /**
     * 二叉搜索树添加元素，大于根节点的元素往右子树添加，小于根节点的元素往左子树添加
     *
     * @param element
     */
    @Override
    protected void add(E element) {
        elementNotNullCheck(element);
        //如果根节点是空的，添加第一个元素
        if (root == null) {
            root = new CustTreeNode<>(element, null);
            return;
        }

        CustTreeNode<E> node = root;
        CustTreeNode<E> parent = root;
        int cmp = 0;
        while (node != null) {
            //找到parent
            parent = node;
            //根parent比较
            cmp = this.compare(element, node.element);

            if (cmp > 0) {
                //往右子树方向找
                node = node.right;
            } else if (cmp < 0) {
                //往左子树方向查找
                node = node.left;
            } else {
                //相等，覆盖
                node.element = element;
                return;
            }
        }

        //走到这里 说明 node 已经是空，判断往哪个方向查找，确定父节点是谁
        if (cmp > 0) {
            //往 右子树 添加
            parent.right = new CustTreeNode<>(element, parent);
        } else {
            //往 左子树 添加
            parent.left = new CustTreeNode<>(element, parent);
        }
        size++;

    }

    /**
     * 删除元素
     *
     * @param element
     * @return
     */
    public boolean remove(E element) {
        elementNotNullCheck(element);

        CustTreeNode<E> delNode = getNode(element);
        if (delNode == null) {
            return false;
        }

        //删除度为2的节点，找到它的前驱节点或后继节点，替换自己的位置，然后删除前驱节点或者后继节点，前驱节点或者后继节点的度要么是0，要么是1
        if (delNode.left != null && delNode.right != null) {
            //找到后继节点
            CustTreeNode<E> successor = successor(delNode);
            //使后继节点的值覆盖自己的值
            delNode.element = successor.element;
            //删除后继节点
            delNode = successor;
        }

        //替换的子节点
        CustTreeNode<E> child = delNode.left != null ? delNode.left : delNode.right;

        if (delNode.parent == null) {
            //如果 (delNode.parent == null) && (child == null) 说明这棵树只有一个根节点，直接root = null;
            //如果 (delNode.parent == null) && (child != null)  说明 delNode 是 度为1的 根节点,root =child;
            root = child;
        } else if (child != null) {
            //删除度为1的节点,让子节点的parent指向自己的parent,让自己parent的right或者left指向自己的子节点
            child.parent = delNode.parent;
            if (delNode == delNode.parent.left) {
                delNode.parent.left = child;
            } else {
                delNode.parent.right = child;
            }
        } else {
            //删除度为0的节点
            if (delNode == delNode.parent.left) {
                delNode.parent.left = null;
            } else {
                delNode.parent.right = null;
            }
        }
        size--;
        return true;
    }

    public CustTreeNode<E> getNode(E element) {
        elementNotNullCheck(element);
        CustTreeNode<E> node = root;
        int cmp;
        while (node != null) {
            cmp = this.compare(element, node.element);
            if (cmp == 0) {
                return node;
            } else if (cmp > 0) {
                //element 大，往右子树方向找
                node = node.right;
            } else {
                //element 小，往左子树方向找
                node = node.left;
            }
        }
        return null;
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
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

}
