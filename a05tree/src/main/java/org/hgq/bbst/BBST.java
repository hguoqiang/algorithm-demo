package org.hgq.bbst;

import org.hgq.bst.BST;

import java.util.Comparator;

/**
 * @description: Balanced Binary Search Tree
 * @author: huangguoqiang
 * @create: 2021-07-15 20:01
 **/
public class BBST<E> extends BST<E> {
    public BBST() {
        this(null);
    }

    public BBST(Comparator comparator) {
        super(comparator);
    }


    protected void leftRotation(CustTreeNode<E> grandfather) {
        CustTreeNode<E> parent = grandfather.right;
        CustTreeNode<E> child = parent.left;
        //交换子节点
        grandfather.right = child;
        parent.left = grandfather;

        //维护各个节点的父节点
        afterRotation(grandfather, parent, child);
    }

    protected void rightRotation(CustTreeNode<E> grandfather) {
        CustTreeNode<E> parent = grandfather.left;
        CustTreeNode<E> child = parent.right;
        grandfather.left = child;
        parent.right = grandfather;

        afterRotation(grandfather, parent, child);
    }

    /**
     * 旋转的公共代码，不管是左旋转还是右旋转
     *
     * @param grandfather 失衡的节点
     * @param parent      失衡节点的 高度最高的子节点
     * @param child       grandfather 和 parent 交换的子节点，本来是 parent的子节点，变成 grandfather 的子节点
     */
    protected void afterRotation(CustTreeNode<E> grandfather, CustTreeNode<E> parent, CustTreeNode<E> child) {
        //让parent成为这颗子树的父节点
        parent.parent = grandfather.parent;
        if (grandfather.isLeftChild()) {
            grandfather.parent.left = parent;
        } else if (grandfather.isRightChild()) {
            grandfather.parent.right = parent;
        } else {
            // grandfather.parent==null
            root = parent;
        }
        // 更新child的parent
        if (child != null) {
            child.parent = grandfather;
        }
        // 更新grand的parent
        grandfather.parent = parent;

    }
}
