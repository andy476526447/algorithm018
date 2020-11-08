package com.example.javalib.arg.week03;

import java.util.LinkedList;
import java.util.Queue;

//leetcode-104二叉树的最大深度:https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
public class Test_104 {
    //方法一：直接使用递归，比较左右子树的深度。时间复杂度：由于每个节点都会遍历一次，所以为O(n)；空间复杂度：取决于栈的深度，所以最坏为O(n)，最好为O(logn)
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDep = maxDepth(root.left);
        int rightDef = maxDepth(root.right);
        int max = Math.max(leftDep, rightDef);
        return max;
    }

    //方法二：使用BFS(即按层搜索)实现。时间复杂度：每个节点会遍历一次，所以为O(n)；空间复杂度：维护了一个队列，保存的是每层的节点，所以最好为O(1),最坏为O(n)(O(n/2)去掉系数1/2)
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int h = 0;//表示层数
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();//表示当前层的节点数
            //将当前层全部出队， 并将每个出队列的节点的孩子都加入队尾。这样执行完一次循环后，当前层遍历完成
            for (int i = 0; i < size; i++) {
                TreeNode tmpNode = queue.poll();
                if (tmpNode.left != null) {
                    queue.offer(tmpNode.left);
                }
                if (tmpNode.right != null) {
                    queue.offer(tmpNode.right);
                }
            }
            h++;//遍历完当前层，高度+1
        }
        return h;
    }
}