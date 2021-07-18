package org.hgq.avl;

import org.hgq.bbst.BBST;

import java.util.Comparator;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-07-12 20:35
 **/
public class AVLTree<E> extends BBST<E> {
    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator comparator) {
        super(comparator);
    }

    @Override
    protected CustTreeNode<E> createNode(E element, CustTreeNode<E> parent) {
        return new AVLNode<>(element, parent);
    }

    @Override
    protected void afterAdd(CustTreeNode<E> node) {

        //添加元素可能导致所有的祖先节点都失衡，父节点和非祖先节点都不可能失衡

        node = node.parent;
        while (node != null) {
            if (isBalanced(node)) {
                //如果平衡，更新高度
                ((AVLNode<E>) node).updateHeight();

            } else {
                //如果不平衡，就旋转，恢复平衡，只要把高度最低的祖先节点恢复平衡，整个二叉树就平衡了
                //第一次传入的这个node ，一定是高度最低的祖先节点，就是父节点的父节点
                rebalanced((AVLNode<E>) node);
                break;
            }
            node = node.parent;
        }

    }

    @Override
    protected void afterRemove(CustTreeNode<E> node) {
        //删除节点可能导致父节点或者祖先节点失衡（只有一个节点会失衡）
        node = node.parent;
        while (node != null) {
            if (isBalanced(node)) {
                //如果平衡，更新高度
                ((AVLNode<E>) node).updateHeight();

            } else {
                //如果不平衡，就旋转，恢复平衡，但是恢复平衡后又可能导致更高的祖先节点失衡（最多需要O(log n 次调整) ）
                rebalanced((AVLNode<E>) node);
            }
            node = node.parent;
        }
    }

    /**
     * 恢复平衡
     *
     * @param grandfather
     */
    private void rebalanced(AVLNode<E> grandfather) {
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

    @Override
    protected void afterRotation(CustTreeNode<E> grandfather, CustTreeNode<E> parent, CustTreeNode<E> child) {
        super.afterRotation(grandfather, parent, child);

        //更新高度 ，先更新子节点的高度，再更新父节点的高度，经过旋转，
        // 此时 grandfather 已经变成 parent的右子树，parent 成为这一颗子树的父节点
        ((AVLNode) grandfather).updateHeight();
        ((AVLNode) parent).updateHeight();
    }

    @Override
    public Object string(Object node) {
        AVLNode<E> myNode = (AVLNode<E>) node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }
        return myNode.element + "_p(" + parentString + ")" + "_h(" + myNode.height + ")";
    }

    /**
     * 是否平衡
     *
     * @param node
     * @return
     */
    private boolean isBalanced(CustTreeNode<E> node) {
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
