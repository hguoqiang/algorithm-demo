package org.hgq.bst;

import org.hgq.tree.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
        root = null;
        size = 0;

    }

    /**
     * 添加元素
     *
     * @param element
     */
    public void add(E element) {
        elementNotNullCheck(element);
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
                //相等
                node.element = element;
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

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
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
    public boolean remove(E element) {
        elementNotNullCheck(element);
        //找到节点
        Node<E> node = getNode(element);
        if (node == null) {
            return false;
        }

        if (node.left != null && node.right != null) {
            //如果是度为2的节点，从前驱节点或者后继节点选择一个替换自己的位置，然后删除前驱节点或者后继节点
            //如果一个节点的度是2，那么它的前驱节点或者后继节点的度要么是0，要么是1

            //找到后继节点
            Node<E> successor = this.successor(node);
            //用后继节点的值覆盖当前这个节点
            node.element = successor.element;

            // 删除后继节点
            node = successor;
        }

        //如果是叶子节点(度为0的节点)，直接删除
        if (node.left == null && node.right == null) {

            //如果删除的节点是根节点(这棵树只有一个根节点)，直接root = null;
            if (node.parent == null) {
                root = null;
            } else if (node.parent.left == node) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }

        } else {
            //如果是度为1的节点，直接把子节点的parent 指向自己的parent
            if (node.left != null) {
                node.left.parent = node.parent;
                if (node.parent == null) {
                    // 说明度为1的节点是根节点
                    root = node.left;
                } else {
                    if (node == node.parent.left) {
                        node.parent.left = node.left;
                    } else {
                        node.parent.right = node.left;
                    }
                }
            } else {
                node.right.parent = node.parent;
                if (node.parent == null) {
                    // 说明度为1的节点 是根节点
                    root = node.right;
                } else {
                    if (node == node.parent.left) {
                        node.parent.left = node.right;
                    } else {
                        node.parent.right = node.right;
                    }
                }
            }
        }
        size--;
        return true;

    }


    /**
     * 删除元素
     *
     * @param element
     */
    public boolean remove2(E element) {
        elementNotNullCheck(element);
        //找到节点
        Node<E> delNode = getNode(element);
        if (delNode == null) {
            return false;
        }

        if (delNode.left != null && delNode.right != null) {
            //如果是度为2的节点，从前驱节点或者后继节点选择一个替换自己的位置，然后删除前驱节点或者后继节点
            //如果一个节点的度是2，那么它的前驱节点或者后继节点的度要么是0，要么是1

            //找到后继节点
            Node<E> successor = this.successor(delNode);
            //用后继节点的值覆盖当前这个节点
            delNode.element = successor.element;

            // 删除后继节点
            delNode = successor;
        }

        // 替换的子节点
        Node<E> childNode = delNode.left != null ? delNode.left : delNode.right;

        if (delNode.parent == null) {
            //如果 (delNode.parent == null) && (childNode == null) 说明这棵树只有一个根节点，直接root = null;
            //如果 (delNode.parent == null) && (childNode != null)  说明 delNode 是 度为1的 根节点,root =childNode;
            root = childNode;
        } else if (childNode == null) {
            //来到这里说明 (delNode.left == null && delNode.right == null) delNode 是叶子节点
            if (delNode.parent != null && delNode.parent.left == delNode) {
                delNode.parent.left = null;
            } else {
                delNode.parent.right = null;
            }
        } else {
            // 来到这里说明  (delNode.left != null) 或者 (delNode.right != null) ,delNode 是度为1的节点
            childNode.parent = delNode.parent;
            if (delNode == delNode.parent.left) {
                delNode.parent.left = childNode;
            } else {
                delNode.parent.right = childNode;
            }
        }
        size--;
        return true;

    }


    private Node<E> getNode(E element) {
        elementNotNullCheck(element);
        Node<E> node = root;
        int compare;
        while (node != null) {
            compare = this.compare(element, node.element);
            if (compare > 0) {
                //往右子树找
                node = node.right;
            } else if (compare < 0) {
                //往左子树找
                node = node.left;
            } else {
                return node;
            }
        }
        return null;
    }

    /**
     * 是否包含指定元素
     *
     * @param element
     * @return
     */
    public boolean contains(E element) {
        return getNode(element) != null;
    }

    /**
     * 前序遍历，递归方式
     */
    public void preorderTraversal() {
        preorderTraversal(root);

    }

    public void preorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        //先访问这个节点
        System.out.println(node.element);
        //然后访问这个节点的左子树
        preorderTraversal(node.left);
        //再访问这个节点的右子树
        preorderTraversal(node.right);
    }

    /**
     * 前序遍历、非递归方式、（使用栈第一种方式）
     */
    public void preorderTraversal2() {
        Node<E> node = root;
        Stack<Node> stack = new Stack<>();
        if (node == null) {
            return;
        }
        stack.push(node);

        while (!stack.isEmpty()) {
            node = stack.pop();
            System.out.println(node.element);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    /**
     * 前序遍历、非递归方式、（使用栈第二种)
     */
    public void preorderTraversal3() {
        Node<E> node = root;
        Stack<Node> stack = new Stack<>();

        while (node != null) {
            System.out.println(node.element);
            if (node.right != null) {
                stack.push(node.right);
            }
            node = node.left;
            if (node == null && !stack.isEmpty()) {
                node = stack.pop();
            }
        }
    }

    /**
     * 中序遍历,左 根 右，递归
     */
    public void inorderTraversal() {
        inorderTraversal(root);
    }

    private void inorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        //左
        inorderTraversal(node.left);
        // 根
        System.out.println(node.element);
        //右
        inorderTraversal(node.right);
    }

    /**
     * 中序遍历，左 根 右，非递归，使用栈，先进后出，后进先出 右 中 左
     */
    public void inorderTraversal2() {
        if (root == null) {
            return;
        }
        Node<E> node = root;
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                Node pop = stack.pop();
                System.out.println(pop.element);
                node = pop.right;
            }
        }
    }

    /**
     * 后序遍历，左右根 ，递归
     */
    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    private void postOrderTraversal(Node<E> node) {

        if (node == null) {
            return;
        }
        postOrderTraversal(node.left);

        postOrderTraversal(node.right);

        System.out.println(node.element);
    }

    /**
     * 后序遍历，左右根，使用栈
     */
    public void postOrderTraversal1() {
        if (root == null) {
            return;
        }
        // pre 是上一次出栈的元素
        Node pre = null;
        Node<E> node = root;
        Stack<Node> stack = new Stack<>();
        //根节点在栈顶
        stack.push(node);
        while (!stack.isEmpty()) {
            //查看栈顶元素
            Node cur = stack.peek();
            // 如果 栈顶元素的左子树和右子树都是空的， 或者 上一次访问的元素是栈顶元素的左子树或者右子树，说明 左右子树访问完毕，那就访问父节点了，即 出栈
            if (cur.right == null && cur.left == null || (pre != null && (pre == cur.left || pre == cur.right))) {
                stack.pop();
                System.out.println(cur.element);
                pre = cur;
            } else {
                //如果栈顶元素的右子树不是空的，把右子树压入栈顶
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                //如果栈顶元素的左子树不是空的，把左子树压入栈顶
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }

        }
    }


    /**
     * 层序遍历，使用队列
     */
    public void levelOrderTraversal() {
        if (root == null) {
            return;
        }
        Node<E> node = root;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            System.out.println(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

    }


    /**
     * 前驱节点
     *
     * @param node
     * @return
     */
    private Node<E> predecessor(Node<E> node) {

        if (node == null) {
            return null;
        }
        if (node.left != null) {
            //这种情况，前驱在左子树的最右子树
            Node<E> pre = node.left;
            while (pre.right != null) {
                pre = pre.right;
            }
            return pre;
        } else if (node.parent != null) {

            while (node != null && node.parent != null) {
                if (node == node.parent.right) {
                    return node.parent;
                }
                node = node.parent;
            }

            return null;
        } else {
            // node.left ==null && node.parent == null
            return null;
        }

    }

    /**
     * 后继节点
     *
     * @param node
     * @return
     */
    private Node<E> successor(Node<E> node) {

        if (node == null) {
            return null;
        }
        if (node.right != null) {
            //这种情况，后继节点在右子树的最左子树
            Node<E> pre = node.right;
            while (pre.left != null) {
                pre = pre.left;
            }
            return pre;
        } else if (node.parent != null) {

            while (node != null && node.parent != null) {
                if (node == node.parent.left) {
                    return node.parent;
                }
                node = node.parent;
            }

            return null;
        } else {
            // node.right ==null && node.parent == null
            return null;
        }
    }

    public void invertTree() {
        root = invertTree(root);

    }

    /**
     * 反转一颗二叉树
     *
     * @param root
     * @return
     */
    public Node<E> invertTree(Node<E> root) {
        if (root == null) {
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        Node<E> temp = null;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            temp = node.left;
            node.left = node.right;
            node.right = temp;

        }
        return root;

    }

    /**
     * 是否是完全二叉树
     *
     * @return
     */
    public boolean isCompleteBinaryTree2() {
        if (root == null) {
            return false;
        }

        boolean isLeaf = false;
        Node<E> node = root;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            node = queue.poll();

            if (isLeaf && !(node.left == null && node.right == null)) {
                return false;
            }

            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                // node.left == null && node.right != null ,这种一定不是
                return false;
            }

            if (node.right != null) {
                queue.offer(node.right);
            } else {
                // (node.left != null && node.right == null) || (node.left == null && node.right == null)
                //这两种情况下，剩下的节点必须是叶子节点 才能是完全二叉树
                isLeaf = true;
            }
        }
        return true;
    }

    /**
     * 是否是完全二叉树
     *
     * @return
     */
    public boolean isCompleteBinaryTree() {
        if (root == null) {
            return false;
        }
        Node<E> node = root;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            node = queue.poll();

            if (node.left != null && node.right != null) {
                queue.offer(node.left);
                queue.offer(node.right);
            } else if (node.left == null && node.right != null) {
                return false;
            } else if ((node.left != null && node.right == null)
                    || (node.left == null && node.right == null)) {
                //这两种情况下，剩下的节点必须都是 叶子节点才是 完全二叉树
                if (node.left != null) {
                    queue.offer(node.left);
                }
                while (!queue.isEmpty()) {
                    node = queue.poll();
                    if (node.left != null || node.right != null) {
                        return false;
                    }
                }
                return true;

            }
        }
        return false;
    }

    /**
     * 计算二叉树的高度，非递归，层序遍历法
     *
     * @return
     */
    public int height1() {

        if (root == null) {
            return 0;
        }
        int height = 0;
        int levelSize = 1;
        Node<E> node = root;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            levelSize--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

    /**
     * 计算二叉树的高度，递归
     *
     * @return
     */
    public int height() {
        return height(root);
    }

    private int height(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }


    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>) node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }
        return myNode.element + "_p(" + parentString + ")";
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

        public boolean isLeaf() {
            return left == null && right == null;
        }
    }


}
