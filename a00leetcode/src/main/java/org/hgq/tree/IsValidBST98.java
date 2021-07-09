package org.hgq.tree;

import java.util.Stack;

/**
 * @description: 98. 验证二叉搜索树
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 * <p>
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * @author: huangguoqiang
 * @create: 2021-07-09 20:08
 **/
public class IsValidBST98 {

    // 中序遍历 （左 根 右 ） 看是否是从小到大排列
    public boolean isValidBST(TreeNode root) {

        if (root == null) {
            return false;
        }

        Stack<TreeNode> stack = new Stack<>();

        Integer preVal = null;

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode top = stack.pop();
                if (preVal != null && preVal >= top.val) {
                    return false;
                }
                preVal = top.val;
                root = top.right;
            }
        }
        return true;
    }


}
