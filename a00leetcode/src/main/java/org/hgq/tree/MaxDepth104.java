package org.hgq.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 二叉树的最大深度
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 * @author: huangguoqiang
 * @create: 2021-07-09 17:00
 **/
public class MaxDepth104 {

    /**
     * 层序遍历
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int maxDepth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
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


}
