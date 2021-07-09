package org.hgq.tree;

/**
 * @description: 450. 删除二叉搜索树中的节点
 * https://leetcode-cn.com/problems/delete-node-in-a-bst/
 * @author: huangguoqiang
 * @create: 2021-07-09 17:30
 **/
public class BSTDeleteNode {
    public TreeNode deleteNode(TreeNode root, int key) {
        // 首先找到需要删除的节点；
        TreeNode delNode = getNode(root, key);

        if (delNode == null) {
            return root;
        }
        //如果找到了，删除它。

        //删除度为2的节点
        if (delNode.left != null && delNode.right != null) {
            //找到后继节点
            TreeNode post = getSuccessor(delNode);

            if (post.left == null && post.right == null) {
                TreeNode parentNode = getParentNode(root, delNode);
                if (delNode == parentNode.left) {
                    parentNode.left = null;
                } else {
                    parentNode.right = null;
                }
                delNode.val = post.val;
                return root;
            }

            delNode.val = post.val;
            delNode = post;
        }

        TreeNode child = delNode.left != null ? delNode.left : delNode.right;

        //删除度为1的节点
        if (child != null) {
            delNode.val = child.val;
            if (child == delNode.left) {
                delNode.left = null;
            } else {
                delNode.right = null;
            }
        } else {
            //删除度为0的节点
            delNode = null;
            TreeNode parentNode = getParentNode(root, delNode);
            if (delNode == parentNode.left) {
                parentNode.left = null;
            } else {
                parentNode.right = null;
            }
        }

        return root;

    }

    /**
     * 找到node的后继节点
     *
     * @param node
     * @return
     */
    private TreeNode getSuccessor(TreeNode node) {
        if (node == null) {
            return null;
        }

        TreeNode post = node.right;
        while (post.left != null) {
            post = post.left;
        }
        return post;
    }

    /**
     * 找到指定的节点
     *
     * @param root
     * @param key
     * @return
     */
    private TreeNode getNode(TreeNode root, int key) {
        while (root != null) {

            if (key == root.val) {
                return root;
            } else if (key > root.val) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return null;
    }


    /**
     * 找到父节点
     *
     * @param root
     * @param child
     * @return
     */
    private TreeNode getParentNode(TreeNode root, TreeNode child) {

        if (child.val == root.val) {
            return null;
        }

        TreeNode parent = null;

        while (root != null) {

            if (child.val > root.val) {
                parent = root;
                root = root.right;
            } else if (child.val < root.val) {
                parent = root;
                root = root.left;
            } else {
                return parent;
            }
        }
        return parent;
    }

}
