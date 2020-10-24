package com.example.javalib.arg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//leetcode-1两数之和
public class Test_1 {
    //暴力算法
    public int[] twoSum1(int[] nums, int target) {
        int[] arr = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = nums.length - 1; i < j; j--) {
                if (nums[i] + nums[j] == target) {
                    arr[0] = i;
                    arr[1] = j;
                    return new int[]{1, 2};
                }
            }
        }
        return new int[2];
    }

    //hash映射:采用该思路的时候，需要避免数组中存在相同元素时覆盖的问题
    public int[] twoSum2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>(len - 1);
        for (int i = 0; i < len; i++) {
            int otherNum = target - nums[i];
            if (!map.containsKey(otherNum)) {
                map.put(nums[i], i);
            } else {
                return new int[]{map.get(target - nums[i]), i};
            }
        }
        return null;
    }


    //排序+双指针法：该算法需要注意考虑存在两个target/2的情况，这样在根据值获取其在原始数组中的下标时容易出错
    public int[] twoSum3(int[] nums, int target) {

        if (nums == null || nums.length == 0) {
            return null;
        }
        int firstIndex = 0, secondIndex = 0;
        int[] result = new int[2];
        int len = nums.length;
        int[] bakNum = new int[len];
        System.arraycopy(nums, 0, bakNum, 0, len);
        Arrays.sort(nums);
        for (int i = 0, j = len - 1; i < j; ) {
            if (nums[i] + nums[j] == target) {
                firstIndex = i;
                secondIndex = j;
                break;
            } else if (nums[i] + nums[j] < target) {
                i++;
            } else {
                j--;
            }
        }
        if (firstIndex == 0 && secondIndex == 0) {
            return null;
        }
        for (int m = 0; m < len; m++) {
            if (bakNum[m] == nums[firstIndex]) {
                result[0] = m;
                break;
            }
        }

        for (int n = len - 1; n > 0; n--) {
            if (bakNum[n] == nums[secondIndex]) {
                result[1] = n;
                break;
            }
        }
        return result;
    }
}