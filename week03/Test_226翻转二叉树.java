package com.example.javalib.arg.week03;

public class Test_226 {

    //方法一：递归，叶子节点左右交换，依次从下往上，依次和自己的兄弟节点交换
    //时间复杂度：每个节点都需要遍历一次，为O(n)。空间复杂度为：每次变换都开辟一个新节点辅助，每两个节点交换一次，为O(n);递归深度为O(logn),空间复杂度为O(n) + O(logn)
    public TreeNode invertTree(TreeNode root) {
        //递归终止条件
        if (root == null) {
            return null;
        }
        //进入下一层递归
        invertTree(root.left);
        invertTree(root.right);
        //处理结果：左右交换节点
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }

    //方法二：递归，类似于方法一，不同的是，方法一是先交换叶子节点，本方法是先交换跟节点，再依次交换叶子节点
    //
    public TreeNode invertTree2(TreeNode root) {
        //递归终止条件
        if (root == null) {
            return null;
        }
        //处理结果：左右交换节点
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        //进入下一层递归
        invertTree2(root.left);
        invertTree2(root.right);
        return root;
    }
}
