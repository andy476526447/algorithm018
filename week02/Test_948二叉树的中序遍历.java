package com.example.javalib.arg.week02;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//leetcode-94二叉树的中序遍历
public class Test_94 {

    public static void main(String[] args) {
        Test_94 test_94 = new Test_94();
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);

        root.left = null;
        root.right = node2;
        node2.left = node3;

        List<Integer> list = test_94.inorderTraversal2(root);
        for (int i : list) {
            System.out.print(i);
        }
    }

    private List<Integer> mNodeList = new ArrayList<>();

    //方法一：递归，时间复杂度O(n)；空间复杂度,最好时为O(logn)，最坏时为O(n)
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            mNodeList.add(root.val);
            inorderTraversal(root.right);
        }
        return mNodeList;
    }

    //方法二：方法一的另外一种形式
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }

    //方法三：迭代法
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        while (root != null || !deque.isEmpty()) {
            while (root != null) {
                deque.push(root);
                root = root.left;
            }
            root = deque.pop();
            result.add(root.val);
            root = root.right;
        }
        return result;
    }
}
