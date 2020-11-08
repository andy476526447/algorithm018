package com.example.javalib.arg.week03;

//leetcode-236.二叉树的最近公共祖先:https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
public class Test_236 {


    //时间复杂度：最坏的情况是所有节点都遍历一次，O(n);空间复杂度:取决于递归的深度，最坏的情况是O(n)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 递归终止条件：root == null表示到了叶子节点，或者该节点的左右树中都不含有p、q节点
        if (root == null || p == root || q == root) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        if (left != null) {
            return left;
        }
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //1、左右子树均不为null，表示p，q分别在root的左右两侧
        if (left != null && right != null) {
            return root;
        }
        //2、p、q均在左子树中
        if (left != null) {
            return left;
        }
        //3、p、q均在右子树中
        if (right != null) {
            return right;
        }
        //4、左右子树都不存在p，q
        return null;
    }
}