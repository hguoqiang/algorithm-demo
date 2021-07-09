package org.hgq.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description: 二叉树前序遍历
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 * @author: huangguoqiang
 * @create: 2021-07-06 17:37
 **/
public class PreorderTraversal144 {

    public static void main(String[] args) {
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
                //7, 4, 9, 2, 5, 8, 11, 3,  1
                // 7, 4, 9, 2,  1
        };
    }

    /**
     * 前序遍历  根 左 右，使用栈 后进先出
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            result.add(root.val);
            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
        }

        return result;
    }

}

