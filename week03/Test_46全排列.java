package com.example.javalib.arg.week03;

import java.util.ArrayList;
import java.util.List;

//leetcode-46.全排列 https://leetcode-cn.com/problems/permutations/
public class Test_46 {

    public static void main(String[] args) {
        Test_46 test_46 = new Test_46();
        int[] nums = {1, 2, 3};
        System.out.println(test_46.permute2(nums).size());
    }

    //=================================================================================
    //方法一：通过DFS的方式，遍历所有可能的组合
    //时间复杂度：长度为n时，会产生n!个结果，所以被访问的数字个数为 n*n!,每个数字判断重复为O(n)，时间复杂度为O(n^2*n!)
    //空间复杂度：递归的深度为n,每次递归中去重需要n的空间，所以空间复杂度为O(n^2)
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }

        if (nums.length == 1) {
            List<Integer> subList = new ArrayList<>();
            subList.add(nums[0]);
            result.add(subList);
            return result;
        }
        helper(nums, "", result);
        return result;
    }

    public void helper(int[] nums, String s, List<List<Integer>> result) {
        String[] strings = s.split(",");
        if (strings.length == nums.length) {
            List<Integer> subList = new ArrayList<>();
            for (String str : strings) {
                if (str.length() != 0) {
                    subList.add(Integer.valueOf(str));
                }
            }
            result.add(subList);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            //过滤重复的数字
            if (!isContans(s, nums[i])) {
                helper(nums, s + nums[i] + ",", result);
            }
        }
    }

    //判断目标整数对应的字符串，是否已经包含,时间复杂度O(n)，空间复杂度O(n)
    private boolean isContans(String s, int target) {
        String[] strings = s.split(",");
        for (String string : strings) {
            if (string.equals(String.valueOf(target))) {
                return true;
            }
        }
        return false;
    }

    //=================================================================================


    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, new ArrayList<Integer>(), result);
        return result;
    }

    private void dfs(int[] nums, List<Integer> subList, List<List<Integer>> result) {
        if (subList.size() == nums.length) {
            result.add(new ArrayList<>(subList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!subList.contains(nums[i])) {
                subList.add(nums[i]);
                dfs(nums, subList, result);
                subList.remove(subList.size() - 1);
            }
        }
    }
}