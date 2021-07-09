package org.hgq.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description: 94. 二叉树的中序遍历
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 * @author: huangguoqiang
 * @create: 2021-07-09 15:42
 **/
public class InorderTraversal94 {
    /**
     * 中序遍历  左 根 右 使用栈
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {

            if (root != null) {
                stack.push(root);
                root = root.left;
            }else {
                TreeNode pop = stack.pop();
                result.add(pop.val);
                root = pop.right;
            }
        }
        return result;
    }
}
