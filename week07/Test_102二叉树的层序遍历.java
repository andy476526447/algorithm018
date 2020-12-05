package com.example.javalib.arg.week07;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//leecode-102二叉树的层序遍历:https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
public class Test_102 {
    public static void main(String[] args) {
        Test_102 test_102 = new Test_102();
    }

    //采用队列实现迭代，时间复杂度:O(n)，因为每个节点都要遍历一遍。空间复杂度：O(n)，开辟了一个队列，保存某一层的节点数，最坏的情况会存储(n+1)/2个节点，如果算上用于保存结果的list，再额外开O(n)个空间
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> subList = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                subList.add(root.val);
                if (root.left != null) {
                    queue.offer(root.left);
                }

                if (root.right != null) {
                    queue.offer(root.right);
                }
            }
            result.add(subList);
        }
        return result;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}