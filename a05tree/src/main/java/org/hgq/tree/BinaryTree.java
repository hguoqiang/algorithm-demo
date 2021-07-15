package org.hgq.tree;

import org.hgq.tree.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-07-08 13:31
 **/
public abstract class BinaryTree<E> implements BinaryTreeInfo {
    protected CustTreeNode<E> root;

    protected int size;

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
        root = null;
        size = 0;
    }

    protected void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    /**
     * 是否包含指定的元素
     *
     * @param element
     * @return
     */
    protected abstract boolean contains(E element);

    /**
     * 添加元素
     *
     * @param element
     */
    protected abstract void add(E element);


    protected abstract CustTreeNode<E> createNode(E element, CustTreeNode<E> parent);

    /**
     * 添加node之后的调整
     *
     * @param node 新添加的节点
     */
    protected abstract void afterAdd(CustTreeNode<E> node);


    /**
     * 删除元素
     *
     * @param element
     */
    protected abstract boolean remove(E element);

    /**
     * 删除node之后的调整
     *
     * @param node 删除的节点
     */
    protected abstract void afterRemove(CustTreeNode<E> node);


    /**
     * 前序遍历，根左右，使用栈实现，后进先出，先进后出
     */
    public void preorderTraversal() {

        Stack<CustTreeNode> stack = new Stack<>();
        CustTreeNode<E> node = root;

        while (node != null) {
            //访问元素
            System.out.println(node.element);

            //把右子树压栈
            if (node.right != null) {
                stack.push(node.right);
            }
            node = node.left;

            //node==null, 就开始从栈顶取出元素
            if (node == null && !stack.isEmpty()) {
                node = stack.pop();
            }
        }
    }

    /**
     * 前序遍历，根左右，使用栈实现，后进先出，先进后出
     * 第二种方式
     */
    public void preorderTraversal2() {

        if (root == null) {
            return;
        }
        Stack<CustTreeNode> stack = new Stack<>();
        CustTreeNode<E> node = root;

        while (node != null) {
            //访问元素
            System.out.println(node.element);

            //把右子树压栈
            if (node.right != null) {
                stack.push(node.right);
            }

            //把左子树压栈
            if (node.left != null) {
                stack.push(node.left);
            }

            //就开始从栈顶取出元素
            if (!stack.isEmpty()) {
                node = stack.pop();
            } else {
                return;
            }
        }
    }

    /**
     * 中序遍历，左根右，使用栈实现
     */
    public void inorderTraversal() {
        if (root == null) {
            return;
        }
        Stack<CustTreeNode> stack = new Stack<>();
        CustTreeNode<E> node = root;

        while (node != null || !stack.isEmpty()) {

            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                CustTreeNode top = stack.pop();
                //访问元素
                System.out.println(top.element);
                node = top.right;
            }
        }
    }

    /**
     * 后序遍历，左右根，使用栈实现
     */
    public void postorderTraversal() {
        if (root == null) {
            return;
        }
        Stack<CustTreeNode> stack = new Stack<>();
        CustTreeNode<E> node = root;

        //记录上一次访问的节点
        CustTreeNode<E> pre = null;

        //把根节点压栈，在栈顶
        stack.push(node);

        while (!stack.isEmpty()) {
            //看一下栈顶元素
            CustTreeNode cur = stack.peek();
            //如果栈顶元素的左子树和右子树都是空的，或者上一次访问的栈顶节点是当前栈顶节点的左子树或者右子树，说明 这个节点的左右子树访问完毕，那就访问这个节点，出栈
            if ((cur.right == null && cur.left == null) || (pre != null && (pre == cur.left || pre == cur.right))) {
                stack.pop();
                System.out.println(cur.element);
                pre = cur;
            } else {
                //如果栈顶元素右子树不是空，压入栈顶
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                //如果栈顶元素左子树不是空，压入栈顶
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }
    }

    /**
     * 层序遍历，从上到下，从左到右，使用队列实现，先进先出
     */
    public void levelOrderTraversal() {
        if (root == null) {
            return;
        }
        Queue<CustTreeNode> queue = new LinkedList<>();
        CustTreeNode<E> node = root;
        queue.offer(node);
        while (!queue.isEmpty()) {

            CustTreeNode poll = queue.poll();
            //访问
            System.out.println(poll.element);

            if (poll.left != null) {
                queue.offer(poll.left);
            }

            if (poll.right != null) {
                queue.offer(poll.right);
            }

        }
    }


    /**
     * 前驱节点，中序遍历时的前一个节点
     * 中序遍历，左根右
     *
     * @param node
     * @return
     */
    public CustTreeNode<E> predecessor(CustTreeNode<E> node) {
        if (node == null) {
            return null;
        }
        //如果node.left !=null， node.left.right.right.... 直到 null
        if (node.left != null) {
            CustTreeNode<E> pre = node.left;
            while (pre.right != null) {
                pre = pre.right;
            }
            return pre;
        } else if (node.parent != null) {
            //如果node.left ==null && node.parent !=null， node.parent.parent.parent.... 直到  node.parent == node.parent.parent.right
            while (node != null && node.parent != null) {
                if (node == node.parent.right) {
                    return node.parent;
                }
                node = node.parent;
            }
            return null;

        } else {
            // 如果node.left ==null &&  node.paren==null，只有右子树的根节点，没有前驱节点
            return null;
        }

    }

    /**
     * 后继节点，中序遍历时的后一个节点
     * 中序遍历，左根右
     *
     * @param node
     * @return
     */
    public CustTreeNode<E> successor(CustTreeNode<E> node) {
        if (node == null) {
            return null;
        }
        //如果 node.right !=null，往右子树中最左子树找
        if (node.right != null) {
            CustTreeNode<E> post = node.right;
            while (post != null) {
                if (post.left == null) {
                    return post;
                }
                post = post.left;
            }
            return post;
        } else if (node.parent != null) {

            while (node != null && node.parent != null) {
                if (node == node.parent.left) {
                    return node.parent;
                }
                node = node.parent;
            }
            return null;
        } else {
            return null;
        }
    }

    /**
     * 返回一颗二叉树的高度
     *
     * @return
     */
    public int height() {
        if (root == null) {
            return 0;
        }
        int height = 0;
        CustTreeNode<E> node = root;
        Queue<CustTreeNode> queue = new LinkedList<>();
        queue.offer(node);

        // 队列中元素个数
        int nodeSize = queue.size();

        while (!queue.isEmpty()) {
            CustTreeNode poll = queue.poll();
            nodeSize--;
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
            if (nodeSize == 0) {
                height++;
                nodeSize = queue.size();
            }

        }

        return height;
    }


    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((CustTreeNode<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((CustTreeNode<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        CustTreeNode<E> myNode = (CustTreeNode<E>) node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }
        return myNode.element + "_p(" + parentString + ")";
    }

    public static class CustTreeNode<E> {
        public E element;
        public CustTreeNode<E> parent;
        public CustTreeNode<E> left;
        public CustTreeNode<E> right;

        public CustTreeNode(E element, CustTreeNode<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean isLeftChild() {
            return this.parent != null && this.parent.left == this;
        }

        public boolean isRightChild() {
            return this.parent != null && this.parent.right == this;
        }
    }
}
