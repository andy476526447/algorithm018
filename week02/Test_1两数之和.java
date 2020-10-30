package com.example.javalib.arg.week02;

import java.util.HashMap;
import java.util.Map;

//leetcode-1两数之和
public class Test_1 {
    //方法一：暴力法，双重循环判断
    public int[] twoSum1(int[] nums, int target) {
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    //方法二：hashcode法
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int otherNum = target - nums[i];
            if (map.containsKey(otherNum)) {
                map.put(nums[i], i);
            } else {
                return new int[]{map.get(otherNum), i};
            }
        }
        return null;
    }
}