package org.hgq.tree;

/**
 * @description: 701. 二叉搜索树中的插入操作
 * https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/
 * @author: huangguoqiang
 * @create: 2021-07-09 19:51
 **/
public class InsertIntoBST701 {


    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null) {

            if (val == root.val) {
                return root;
            } else if (val > root.val) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return null;
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {

        if (root == null) {
            root = new TreeNode(val);
            return root;
        }

        TreeNode node = root;
        TreeNode parent = root;
        int cmp = 0;
        while (node != null) {
            parent = node;
            cmp = this.compare(val, node.val);
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                return root;
            }
        }

        if (cmp > 0) {
            parent.right = new TreeNode(val);
        } else if (cmp < 0) {
            parent.left = new TreeNode(val);
        }
        return root;

    }

    private int compare(int val0, int val1) {
        return val0 - val1;
    }


}
