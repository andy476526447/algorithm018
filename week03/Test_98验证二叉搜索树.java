package com.example.javalib.arg.week03;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//leetcode-98验证二叉搜索树 https://leetcode-cn.com/problems/validate-binary-search-tree/
public class Test_98 {

    //===================================================================================================================
    //方法一：利用BST的中序遍历结果是一个递增的有序数列，先得到中序遍历的结果，再判断这个结果是否是递增的数列即可。
    //时间复杂度：递归为O(n)， 判断是否升序为 O(n)，最终结果为O(n)；
    //空间复杂度：递归为O(logn)，保存遍历结果使用了长度为n的list，时间复杂度为O(n)，判断是否升序为 O(1)，最终时间复杂度为O(n)。
    public boolean isValidBST(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        recursion(root, resultList);
        int size = resultList.size();
        if (size == 0 || size == 1) {
            return true;
        }
        int last = resultList.get(0);
        for (int i = 1; i < size; i++) {
            if (resultList.get(i) < last) {
                return false;
            } else {
                last = resultList.get(i);
            }
        }
        return true;
    }

    private void recursion(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        recursion(root.left, list);
        list.add(root.val);
        recursion(root.right, list);
    }
//===================================================================================================================

    //方法二：在方法一的基础上进行了优化，不用每次等到遍历结束后再判断，在遍历时就比较上一个数大小即可。
    //时间复杂度：O(n)，空间复杂度O(1)

    private boolean flag = true;
    double preVal = -Double.MAX_VALUE;

    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        recursion2(root);
        return flag;
    }

    private void recursion2(TreeNode root) {
        if (root == null || !flag) {
            return;
        }
        recursion2(root.left);
        if (root.val <= preVal) {
            flag = false;
            return;
        }
        preVal = root.val;
        recursion2(root.right);
    }

    //======================================================================================================================
    //方法三：通过迭代的方式来实现中序遍历，是方法二思路的另外一种实现
    //时间复杂度O(n),空间复杂度O(n)
    public boolean isValidBST3(TreeNode root) {
        double pre = -Double.MAX_VALUE;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = new TreeNode();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= pre) {
                return false;
            }
            pre = root.val;
            root = root.right;
        }
        return true;
    }

}
