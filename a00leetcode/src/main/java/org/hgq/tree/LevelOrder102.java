package org.hgq.tree;

import java.util.*;

/**
 * @description: 层序遍历
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * @author: huangguoqiang
 * @create: 2021-07-09 16:30
 **/
public class LevelOrder102 {
    /**
     * 层序遍历，从上到下，从左到右依次访问元素，使用队列
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelNodeSize = queue.size();

        List<Integer> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            root = queue.poll();
            list.add(root.val);
            levelNodeSize--;
            if (root.left != null) {
                queue.offer(root.left);
            }
            if (root.right != null) {
                queue.offer(root.right);
            }
            if (levelNodeSize == 0) {
                result.add(new ArrayList<>(list));
                list.clear();
                levelNodeSize = queue.size();
            }
        }
        return result;
    }


    /**
     * 107. 二叉树的层序遍历 II
     * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
     * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelNodeSize = queue.size();

        List<Integer> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            root = queue.poll();
            list.add(root.val);
            levelNodeSize--;
            if (root.left != null) {
                queue.offer(root.left);
            }
            if (root.right != null) {
                queue.offer(root.right);
            }
            if (levelNodeSize == 0) {
                result.add(0,new LinkedList<>(list));
                list.clear();
                levelNodeSize = queue.size();
            }
        }
        return result;
    }


}
