package com.example.javalib.arg.week03;

import java.util.ArrayList;
import java.util.List;

//leetcode-77组合:https://leetcode-cn.com/problems/combinations/
public class Test_77 {
    public static void main(String[] args) {
        Test_77 test_77 = new Test_77();
        System.out.println(test_77.combine3(4, 2));
    }

    //===================================================
    //方法一：采用递归的方式。
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        helper(n, k, new ArrayList<Integer>(), result);
        return result;
    }

    //递归获取所有满足条件的组合
    public void helper(int n, int k, List<Integer> compation, List<List<Integer>> result) {
        //终止条件，当字符串中包含了k个数字时满足结果条件，终止，并保存结果
        if (compation.size() == k) {
            result.add(new ArrayList<Integer>(compation));
            return;
        }
        //每个subList中的每个位置都有n个选择
        for (int i = 1; i <= n; i++) {
            //过滤掉不满足条件的数字：如果是第一个元素，可以插入；如果不是，后面的数字必须比前面的大
            if (compation.isEmpty() || i > compation.get(compation.size() - 1)) {
                compation.add(i);
                helper(n, k, compation, result);
                compation.remove(compation.size() - 1);
            }
        }
    }

    //===================================================
    //方法二：对方法一的优化
    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        helper2(n, k, new ArrayList<Integer>(), result, 1);
        return result;
    }

    public void helper2(int n, int k, List<Integer> combination, List<List<Integer>> result, int begin) {
        //每个combination的长度达到k，表示满足条件了，终止递归
        if (combination.size() == k) {
            result.add(new ArrayList<>(combination));
            return;
        }
        //从begin~n中选一个数字来作为组合中某个位置的元素，begin是组合中已有元素的下一个可选数字，比如组合中的上一个元素是1，这里begin就是2。
        for (int i = begin; i <= n; i++) {
            //在combination中添加一个元素
            combination.add(i);
            //combination中下一个元素的就是从i+1~n找出另外一个数
            helper2(n, k, combination, result, i + 1);
            // 重点理解这里：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
            combination.remove(combination.size() - 1);
        }
    }

    //===================================================
    //方法三:在方法二的基础上进行"剪枝"
    public List<List<Integer>> combine3(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        helper3(n, k, new ArrayList<Integer>(), result, 1);
        return result;
    }

    public void helper3(int n, int k, List<Integer> combination, List<List<Integer>> result, int begin) {
        //每个combination的长度达到k，表示满足条件了，终止递归
        if (combination.size() == k) {
            result.add(new ArrayList<>(combination));
            return;
        }
        //从begin~n中选一个数字来作为组合中某个位置的元素，begin是组合中已有元素的下一个可选数字，比如组合中的上一个元素是1，这里begin就是2。
        for (int i = begin; i <= combination.size() + (n - k) + 1; i++) {
            //在combination中添加一个元素
            combination.add(i);
            //combination中下一个元素的就是从i+1~n找出另外一个数
            helper3(n, k, combination, result, i + 1);
            // 重点理解这里：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
            combination.remove(combination.size() - 1);
        }
    }
}