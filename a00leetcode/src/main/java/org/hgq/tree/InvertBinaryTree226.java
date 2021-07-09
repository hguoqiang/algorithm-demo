package org.hgq.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 反转二叉树
 * https://leetcode-cn.com/problems/invert-binary-tree/
 * @author: huangguoqiang
 * @create: 2021-07-06 17:37
 **/
public class InvertBinaryTree226 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        TreeNode temp = null;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

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

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() { }
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
