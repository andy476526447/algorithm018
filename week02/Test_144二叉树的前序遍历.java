package com.example.javalib.arg.week02;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//leetcode-144二叉树的前序遍历
public class Test_144 {
    public static void main(String[] args) {
        Test_144 test_144 = new Test_144();
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);

        root.left = null;
        root.right = node2;
        node2.left = node3;

        List<Integer> list = test_144.preorderTraversal3(root);
        for (int i : list) {
            System.out.print(i);
        }
    }

    //方法一：递归的方式。每个元素都会遍历一次，所以时间复杂度：O(n)；空间复杂度和深度相关，最坏的情况为一个链表，空间复杂度为O(n)，最好的情况O(logn)
    //优点：代码简单清晰；缺点：如果层次太深会抛出栈溢出异常
    private List<Integer> mNodeList = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root != null) {
            mNodeList.add(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
        return mNodeList;
    }

    //方法二：迭代的方式,DFS算法。每个元素都需要遍历(入栈和出栈)到，时间复杂度为O(n)；空间复杂度，最好的情况是push一个，pop一个，空间复杂度O(1),最坏的情况是存储将近一半的元素，所以为O(n),平均时间复杂度为O(logn)
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }

    //方法三：也是迭代，套用模板。时间复杂度O(n);空间复杂度最坏的情况是左子树的链状结构，为O(n)，最好的情况是都只有右子树，一边入栈一边出栈，时间复杂度为O（n）,平均时间复杂度为O(logn)。
    public List<Integer> preorderTraversal3(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                result.add(root.val);
                stack.push(root);
                root = root.left;
            }
            TreeNode tmp = stack.pop();
            root = tmp.right;
        }
        return result;
    }
}