package com.example.javalib.arg.week03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode-47.全排列II:https://leetcode-cn.com/problems/permutations-ii/
public class Test_47 {
    public static void main(String[] args) {
        Test_47 test_47 = new Test_47();
        int[] nums = {};
        long t1 = System.currentTimeMillis();
        System.out.println(test_47.permuteUnique2(nums));
        System.out.println(System.currentTimeMillis() - t1);
    }

    //=================================================================
    //方法一：暴力法，先列举出各种组合，然后去掉重复项目。该方法在leetcode上提示“超出时间限制”
    public List<List<Integer>> permuteUnique(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, new ArrayList<Integer>(), map, result);
        return result;
    }

    private void dfs(int[] nums, List<Integer> combination, Map map, List<List<Integer>> result) {
        if (combination.size() == nums.length) {
            if (!isContains(result, combination)) {
                result.add(new ArrayList<>(combination));
            }
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (needAdd(combination, nums[i], map)) {
                combination.add(nums[i]);
                dfs(nums, combination, map, result);
                combination.remove(combination.size() - 1);
            }
        }
    }

    private boolean needAdd(List<Integer> list, int target, Map map) {
        if (!list.contains(target)) {
            return true;
        }
        int count = 0;
        for (int i : list) {
            if (i == target) {
                count++;
                if (count == (Integer) map.get(target)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isContains(List<List<Integer>> result, List<Integer> subList) {
        for (List<Integer> list : result) {
            if (list.equals(subList)) {
                return true;
            }
        }
        return false;
    }

    //=================================================================
    //方法二：在方法一的基础上，优化了判断重复添加的条件，并进行了剪枝，避免重复添加数据。
    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        //该数组用于记录nums某元素是否已经使用了，避免重复使用
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        dfs2(nums, new ArrayList<Integer>(), used, result);
        return result;
    }

    private void dfs2(int[] nums, List<Integer> combination, boolean[] used, List<List<Integer>> result) {
        //遇到符合条件的结果，添加到结果集中，并终止本次递归。
        if (combination.size() == nums.length) {
            result.add(new ArrayList<>(combination));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            //在当前的递归中，如果i位置的元素已经使用过了，后面就不再添加该元素，避免同一个元素重复添加
            if (used[i]) {
                continue;
            }

            //剪枝条件，如果本元素和上一个是重复的元素，且本次组合中nums[i-1]因为递归被撤销了（也就是num[i-1]还没有出现在本次组合中），那么本次递归产生的结果就和上一次重复了
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            combination.add(nums[i]);
            used[i] = true;
            dfs2(nums, combination, used, result);
            used[i] = false;
            //回溯算法，前面添加，这里就移除，回到上一步状态。
            combination.remove(combination.size() - 1);
        }
    }
}
