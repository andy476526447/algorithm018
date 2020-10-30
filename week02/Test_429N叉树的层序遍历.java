package com.example.javalib.arg.week02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//leetcode-429N叉树的层序遍历
public class Test_429 {
    public static void main(String[] args) {
        Test_429 test_429 = new Test_429();


        Node node5 = new Node(5);
        Node node6 = new Node(6);
        List<Node> list2 = new ArrayList<>();
        list2.add(node5);
        list2.add(node6);
        Node node3 = new Node(3, list2);
        Node node2 = new Node(2);
        Node node4 = new Node(4);
        List<Node> list = new ArrayList<>();
        list.add(node3);
        list.add(node2);
        list.add(node4);
        Node root = new Node(1, list);

        List<List<Integer>> result = test_429.levelOrder(root);
        for (List<Integer> subList : result) {
            System.out.println();
            for (int i : subList) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    //结合BFS算法。时间复杂度O(n),每个元素都会遍历一遍。空间复杂度，最好的情况是呈链状，每层一个元素，队首每出列一个元素，队尾就添加一个孩子节点，空间复杂度为O(1);最坏的情况是一个根节点下面N-1个孩子，空间复杂度O(n)。
    //另外，由于是使用了List来保存所有的结果，如果算上这个空间的话，也额外新增了n个空间，空间复杂度为O(n);
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                list.add(node.val);
                if (node.children != null) {
                    queue.addAll(node.children);
                }
            }
            result.add(list);
        }
        return result;
    }
}