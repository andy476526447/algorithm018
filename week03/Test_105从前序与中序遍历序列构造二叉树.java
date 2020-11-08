package com.example.javalib.arg.week03;

import java.util.HashMap;
import java.util.Map;

//leetcode-105.从前序与中序遍历序列构造二叉树:https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
public class Test_105 {

    public static void main(String[] args) {
        Test_105 test_105 = new Test_105();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        test_105.buildTree(preorder, inorder);
    }

    private Map<Integer, Integer> inMap = new HashMap<>();
    private int[] preorder;
    private int[] inorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        int len = preorder.length;
        //把inorder的值与对应的index保存在HashMap中，方便在后面确定inorder中root的index
        for (int i = 0; i < len; i++) {
            inMap.put(inorder[i], i);
        }
        return buildTreeByRecursion(0, len - 1, 0, len - 1);
    }

    //preorder数组因为是前序遍历的结果，所有容易找到root节点。然后根据root的值找到其在inorder数组中的index，进而确定左右子树的长度，然后再到preorder数组中确定新的子树以及新子树的root。
    private TreeNode buildTreeByRecursion(int preLeft, int preRight, int inLeft, int inRight) {
        //1、递归终止条件：给定的数组的左边界>右边界时，二叉树不成立
        if (preLeft > preRight) {
            return null;
        }
        //2、处理逻辑
        //创建根节点：先序遍历时，数组的第一个值即为root
        TreeNode root = new TreeNode(preorder[preLeft]);
        //root节点在inorder数组中的位置
        int inRootIndex = (int) inMap.get(preorder[preLeft]);
        //左子树的长度
        int leftSubSize = inRootIndex - inLeft;
        //3、深入一层递归。在进行深一层递归的时候，各个参数会根据递归的深度进行调整
        root.left = buildTreeByRecursion(preLeft + 1, preLeft + leftSubSize, inLeft, inRootIndex - 1);
        root.right = buildTreeByRecursion(preLeft + leftSubSize + 1, preRight, inRootIndex + 1, inRight);
        //4、后续处理
        return root;
    }
}