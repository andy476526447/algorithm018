package com.example.javalib.arg.week03;

import java.util.LinkedList;
import java.util.Queue;

//leetcode-111二叉树的最小深度:https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
public class Test_111 {

    //方法一：按层次遍历，当发现当前层中某个节点的孩子为null时，表示到达了该树的最小深度
    //时间复杂度：最好为O(1)，即第二层就有一个叶子节点，最坏为O(n)，需要遍历到最深的一层的第一个节点，尤其是该二叉树为一个单链表时，每个节点都会遍历一次。
    //空间复杂度：维护了一个队列，最好的时候是二叉树为单链表时，每次最多存储一个节点，为O(1)，或者在第二层的左孩子就是叶子节点，也只保存一个节点。最坏的时候，是完全二叉树时，保存所有倒数第二层的所有节点，为O(n)
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int h = 0;//记录深度
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();//表示当前层的节点数
            //将当前层全部出队， 并将每个出队列的节点的孩子都加入队尾。这样执行完一次循环后，当前层遍历完成
            for (int i = 0; i < size; i++) {
                TreeNode tmpNode = queue.poll();
                //左右孩子都为null时，表示已经到了叶子节点，此时可以表示最小深度
                if (tmpNode.left == null && tmpNode.right == null) {
                    return ++h;
                }
                if (tmpNode.left != null) {
                    queue.offer(tmpNode.left);
                }
                if (tmpNode.right != null) {
                    queue.offer(tmpNode.right);
                }
            }
            h++;
        }
        return h;
    }

    //方法二：采用递归的方式。时间复杂度：最好时为O(1)，最坏时是为(n)，层链状，每个节点都访问到。空间复杂度：取决于递归的深度，最好时为O(logn)，最坏时O(n)
    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //左右孩子均为null，为叶子节点，深度为1
        if (root.left == null && root.right == null) {
            return 1;
        }
        //左子树或者右子树不为null时，继续递归
        int leftDep = minDepth2(root.left);
        int rightDep = minDepth2(root.right);
        //如果左孩子为null，右孩子不为null，则最小深度为右子数的最小深度
        if (root.left == null) {
            return rightDep + 1;
        }
        //如果左节点不为null，右孩子为null，则最小深度为左子树的最小深度
        if (root.right == null) {
            return leftDep + 1;
        }
        //左孩子和右孩子都不为null，则比较左右子数深度
        return Math.min(leftDep, rightDep) + 1;
    }

    //方法三：对方法二的简化
    public int minDepth3(TreeNode root) {
        if (root == null) return 0;
        int l = minDepth3(root.left);
        int r = minDepth3(root.right);
        return (root.left == null || root.right == null) ? l + r + 1 : Math.min(l, r) + 1;
    }

}