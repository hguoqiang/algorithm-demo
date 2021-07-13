package org.hgq.avl;

import org.hgq.bst.BST;

import java.util.Comparator;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-07-12 20:35
 **/
public class AVLTree<E> extends BST<E> {
    public AVLTree() {
    }

    public AVLTree(Comparator comparator) {
        super(comparator);
    }

    @Override
    protected CustTreeNode<E> createNode(E element, CustTreeNode<E> parent) {
        return new AVLNode<>(element,parent);
    }

    @Override
    protected void afterAdd(CustTreeNode<E> node) {

        //添加元素可能导致所有的祖先节点都失衡，父节点和非祖先节点都不可能失衡

        node = node.parent;
        while (node != null) {
            if (isBalance(node)) {
                //如果平衡，更新高度
                ((AVLNode<E>) node).updateHeight();

            } else {
                //如果不平衡，就旋转，恢复平衡，只要把高度最低的祖先节点恢复平衡，整个二叉树就平衡了
                //第一次传入的这个node ，一定是高度最低的祖先节点，就是父节点的父节点
                rebalance((AVLNode<E>) node);
                break;
            }
            node = node.parent;
        }

    }

    /**
     * 恢复平衡
     *
     * @param grandfather
     */
    private void rebalance(AVLNode<E> grandfather) {
        AVLNode<E> parent = grandfather.tallerChild();
        AVLNode<E> node = parent.tallerChild();

        if (grandfather.left == parent) {
            if (parent.left == node) {
                //LL 对 grandfather 进行右旋转，让 parent 成为这一颗子树的父节点
                rightRotation(grandfather);

            } else {
                //LR  先对 parent 进行左转，变成LL ,然后 对 grandfather 进行右转
                leftRotation(parent);
                rightRotation(grandfather);
            }
        } else {

            if (parent.right == node) {
                //RR 对 grandfather 左旋转
                leftRotation(grandfather);
            } else {
                //RL 先对parent进行右旋转，然后对grandfather进行左旋转
                rightRotation(parent);
                leftRotation(grandfather);

            }
        }
    }

    private void leftRotation(AVLNode<E> grandfather) {
        AVLNode<E> parent = (AVLNode<E>) grandfather.right;
        AVLNode<E> child = (AVLNode<E>) parent.left;
        grandfather.right = child;
        parent.left = grandfather;

        afterRotation(grandfather, parent, child);
    }

    private void rightRotation(AVLNode<E> grandfather) {
        AVLNode<E> parent = (AVLNode<E>) grandfather.left;
        AVLNode<E> child = (AVLNode<E>) parent.right;
        grandfather.left = child;
        parent.right = grandfather;

        afterRotation(grandfather,parent,child);
    }

    private void afterRotation(AVLNode<E> grandfather, AVLNode<E> parent, AVLNode<E> child) {
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

        //更新高度 ，先更新子节点的高度，再更新父节点的高度，经过旋转，
        // 此时 grandfather 已经变成 parent的右子树，parent 成为这一颗子树的父节点
        grandfather.updateHeight();
        parent.updateHeight();
    }

    @Override
    public Object string(Object node) {
        AVLNode<E> myNode = (AVLNode<E>) node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }
        return myNode.element + "_p(" + parentString + ")" +"_h(" + myNode.height + ")";
    }

    /**
     * 是否平衡
     *
     * @param node
     * @return
     */
    private boolean isBalance(CustTreeNode<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }


    private static class AVLNode<E> extends CustTreeNode<E> {
        //节点的高度，新添加的节点一定是叶子节点，节点高度默认就是1
        int height = 1;

        public AVLNode(E element, CustTreeNode<E> parent) {
            super(element, parent);
        }

        /**
         * 节点的平衡因子等于 此节点 左右子树的高度差
         * 每个节点的平衡因子只可能是 -1、0、1，如果绝对值大于1，就是失衡
         *
         * @return 节点的平衡因子
         */
        public int balanceFactor() {
            //左子树高度
            int leftHeight = left == null ? 0 : ((AVLNode) left).height;
            //右子树高度
            int rightHeight = right == null ? 0 : ((AVLNode) right).height;
            return leftHeight - rightHeight;
        }

        /**
         * 节点的高度，从当前节点到最远叶子节点路径上的节点总数
         * 更新节点的高度，左右子树高度取最大值，然后加1
         */
        public void updateHeight() {
            //左子树高度
            int leftHeight = left == null ? 0 : ((AVLNode) left).height;
            //右子树高度
            int rightHeight = right == null ? 0 : ((AVLNode) right).height;
            height = Math.max(leftHeight, rightHeight) + 1;
        }

        /**
         * @return 返回这个节点中 高度高的子节点
         */
        public AVLNode<E> tallerChild() {
            //左子树高度
            int leftHeight = left == null ? 0 : ((AVLNode) left).height;
            //右子树高度
            int rightHeight = right == null ? 0 : ((AVLNode) right).height;
            if (leftHeight > rightHeight) {
                return (AVLNode) left;
            } else if (leftHeight < rightHeight) {
                return (AVLNode) right;
            } else {
                //如果高度相等，返回与这个节点同方向 的子节点
                return isLeftChild() ? (AVLNode) left : (AVLNode) right;
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(Math.max(1, 1));
    }
}
