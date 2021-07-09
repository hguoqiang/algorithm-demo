package org.hgq.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description:后序遍历 https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 * @author: huangguoqiang
 * @create: 2021-07-09 15:53
 **/
public class PostorderTraversal145 {
    /**
     * 后序遍历  左 右 根
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);
        while (!stack.isEmpty()) {

            root = stack.peek();
            //当前节点的左右子树都是空的 ，出栈，当前节点的 左右子树不是空的，但是当前节点的左子树是上一次出栈的节点，或者当前节点的右子树是上一次出栈的节点，说明左右子树已经出栈了，那么当前父节点要出栈
            if ((root.right == null && root.left == null) || (pre != null && (pre == root.left || pre == root.right))) {
                stack.pop();
                result.add(root.val);
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
}
