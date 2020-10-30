package com.example.javalib.arg.week02;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//leetcode-590:N叉树的后序遍历
public class Test_590 {

    List<Integer> mNodeList = new ArrayList<>();

    //方法一：递归法
    public List<Integer> postorder1(Node root) {
        if (root != null) {
            for (int i = 0; i < root.children.size(); i++) {
                postorder1(root.children.get(i));
            }
            mNodeList.add(root.val);
        }
        return mNodeList;
    }


    //方法二：使用迭代的方式
    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            result.add(root.val);
            List<Node> children = root.children;
            if (children == null) {
                continue;
            }
            for (int i = 0; i < children.size(); i++) {
                stack.push(children.get(i));
            }
        }
        Collections.reverse(result);
        return result;
    }
}
