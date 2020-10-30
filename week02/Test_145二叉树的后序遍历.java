package com.example.javalib.arg.week02;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

//leetcode-145二叉树的后序遍历
public class Test_145 {
    public static void main(String[] args) {
        Test_145 test_145 = new Test_145();
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);

        root.left = null;
        root.right = node2;
        node2.left = node3;

        List<Integer> list = test_145.postorderTraversal3(root);
        for (int i : list) {
            System.out.print(i);
        }
    }

    private List<Integer> mNodeList = new ArrayList<>();

    //方法一：递归
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root != null) {
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            mNodeList.add(root.val);
        }
        return mNodeList;
    }

    //方法二：参照前序遍历，先按照根-右-左遍历，最后将结果反转
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        while (root != null || !deque.isEmpty()) {
            while (root != null) {
                result.add(root.val);
                deque.push(root);
                root = root.right;
            }
            root = deque.pop();
            root = root.left;
        }
        Collections.reverse(result);
        return result;
    }

    //方法三：迭代法
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode preNode = new TreeNode();//该节点用于保存前一个出栈的节点
        while (root != null || !stack.isEmpty()) {
            //将当前节点的左子树节点一次入栈
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.peek();
            //如果当前节点没有右孩子了，或者其右孩子已经出栈了，则当前节点也出栈
            if (root.right == null || root.right == preNode) {
                root = stack.pop();
                result.add(root.val);//保存结果
                preNode = root; //记录本次刚输出的节点
                root = null;
            } else {
                //如果当前节点还有右孩子，且其右孩子还没有出栈，则先处理其右孩子
                root = root.right;
            }
        }
        return result;
    }
}
