package org.hgq.bst;

import org.hgq.tree.BinaryTree;
import org.hgq.tree.printer.BinaryTrees;

import java.util.*;

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
    protected Comparator comparator;

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
    public void add(E element) {
        elementNotNullCheck(element);
        //如果根节点是空的，添加第一个元素
        if (root == null) {
            root = createNode(element, null);
            return;
        }

        CustTreeNode<E> node = root;
        //添加的不是第一个节点 找到parent
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

        //走到这里 说明 node 已经是空，看看插入到父节点的哪个位置
        CustTreeNode<E> newNode = createNode(element, parent);
        if (cmp > 0) {
            //往 右子树 添加
            parent.right = newNode;
        } else {
            //往 左子树 添加
            parent.left = newNode;
        }


        //新添加节点之后的处理
        afterAdd(newNode);

        size++;

    }

    @Override
    protected   CustTreeNode<E> createNode(E element, CustTreeNode<E> parent){
        return new CustTreeNode<>(element, parent);
    }

    @Override
    protected void afterAdd(CustTreeNode<E> node) {

    }


    /**
     * 删除元素
     *
     * @param element
     * @return
     */
    @Override
    protected boolean remove(E element) {
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
            //来到这里说明  (delNode.left != null) 或者 (delNode.right != null) ,delNode 是度为1的节点
            // 删除度为1的节点,让子节点的parent指向自己的parent,让自己parent的right或者left指向自己的子节点
            child.parent = delNode.parent;
            if (delNode == delNode.parent.left) {
                delNode.parent.left = child;
            } else {
                delNode.parent.right = child;
            }
        } else {
            //删除度为0的节点 , 来到这里说明 (delNode.left == null && delNode.right == null) delNode 是叶子节点

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


    /**
     * 前序遍历  根 左 右，使用栈 后进先出
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(CustTreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Stack<CustTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            CustTreeNode top = stack.pop();
            result.add((int) top.element);
            if (top.right != null) {
                stack.push(top.right);
            }
            if (top.left != null) {
                stack.push(top.left);
            }
        }

        return result;
    }

    /**
     * 中序遍历  左 根 右 使用栈
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(CustTreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Stack<CustTreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {

            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                CustTreeNode pop = stack.pop();
                result.add((int) pop.element);
                root = pop.right;
            }
        }
        return result;
    }

    public List<Integer> postorderTraversal(CustTreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }
        CustTreeNode pre = null;

        Stack<CustTreeNode> stack = new Stack<>();

        stack.push(root);
        while (!stack.isEmpty()) {

            root = stack.peek();
            //当前节点的左右子树都是空的 ，出栈，当前节点的 左右子树不是空的，但是当前节点的左子树是上一次出栈的节点，或者当前节点的右子树是上一次出栈的节点，说明左右子树已经出栈了，那么当前父节点要出栈
            if ((root.right == null && root.left == null) || (pre != null && (pre == root.left || pre == root.right))) {
                stack.pop();
                result.add((int) root.element);
                pre = root;
            } else {
                if (root.right != null) {
                    stack.push(root.right);
                }
                if (root.left != null) {
                    stack.push(root.left);
                }
            }

        }
        return result;
    }

    /**
     * 层序遍历，从上到下，从左到右依次访问元素，使用队列
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(CustTreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<CustTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelNodeSize = queue.size();

        List<Integer> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            root = queue.poll();
            list.add((int) root.element);
            levelNodeSize--;
            if (root.left != null) {
                queue.offer(root.left);
            }
            if (root.right != null) {
                queue.offer(root.right);
            }
            if (levelNodeSize == 0) {
                result.add(0, new ArrayList<>(list));
                list.clear();
                levelNodeSize = queue.size();
            }

        }
        return result;
    }


    /**
     * 层序遍历
     *
     * @param root
     * @return
     */
    public int maxDepth(CustTreeNode root) {
        if (root == null) {
            return 0;
        }

        int maxDepth = 0;
        Queue<CustTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelNodeSize = queue.size();

        while (!queue.isEmpty()) {
            root = queue.poll();
            levelNodeSize--;

            if (root.left != null) {
                queue.offer(root.left);
            }
            if (root.right != null) {
                queue.offer(root.right);
            }
            if (levelNodeSize == 0) {
                maxDepth++;
                levelNodeSize = queue.size();
            }
        }
        return maxDepth;
    }


    public static void main(String[] args) {
        Integer data[] = new Integer[]{
                //7, 4, 9, 2, 5, 8, 11, 3, 12, 1
                //7, 4, 9, 2, 5, 8, 11, 3,  1
                // 7, 4, 9, 2,  1
                //1,3,2
                -2147483648, 2147483647
        };
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);
/*        System.out.println(bst.levelOrder(bst.root));
        System.out.println(bst.maxDepth(bst.root));*/

    }

}
