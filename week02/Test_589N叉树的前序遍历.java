package com.example.javalib.arg.week02;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//leetcode-589:N叉树的前序遍历
public class Test_589 {

    List<Integer> mNodeList = new ArrayList<>();

    //方法一：递归
    public List<Integer> preorder1(Node root) {
        if (root != null) {
            mNodeList.add(root.val);
            for (int i = 0; i < root.children.size(); i++) {
                preorder1(root.children.get(i));
            }
        }
        return mNodeList;
    }

    //方法二：迭代
    public List<Integer> preorder2(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            result.add(root.val);
            if (root.children == null) {
                continue;
            }
            for (int i = root.children.size() - 1; i >= 0; i--) {
                stack.push(root.children.get(i));
            }
        }
        return result;
    }

}

