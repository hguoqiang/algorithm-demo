package org.hgq.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：一个二叉树 每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 * @author: huangguoqiang
 * @create: 2021-07-13 19:31
 **/
public class IsBalanced110 {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        //分别求出两个子树的高度
        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);

    }

    private int height(TreeNode node) {
        //按照层序遍历 队列
        if (node == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        int levelNodeSize = queue.size();
        int height = 0;
        while (!queue.isEmpty()) {
            node = queue.poll();
            levelNodeSize--;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }

            if (levelNodeSize == 0) {
                height++;
                levelNodeSize = queue.size();
            }
        }
        return height;
    }


}
