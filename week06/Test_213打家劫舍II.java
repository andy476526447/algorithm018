package com.example.javalib.arg.week06;

import java.util.Arrays;

//leetcode-213打家劫舍II:https://leetcode-cn.com/problems/house-robber-ii/description/
public class Test_213 {

    public static void main(String[] args) {
        Test_213 test_213 = new Test_213();
        int[] nums = {1, 2, 3, 1};
        System.out.println(test_213.rob(nums));
    }

    //动态规划：时间复杂度O(n),空间复杂度O(n)，空间复杂度主要来源于需要使用数组保存子数组
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        //由于房屋是环状，最后一间和第一间只能偷取一家，所以可以将该问题拆解为两种情况：
        //1、最后一间不偷取，即第1~len-1间房屋最多能偷取的总金额
        //2、最后一间偷取，那么第一间就不能偷取，即第2~len间房屋最多能偷取的总金额
        //这两种情况下分别获取最大金额，再取其中较大者。这里需要考虑只有一间房屋时的特殊情况，只有一间时肯定要偷
        return len == 1 ? nums[0] : Math.max(helper(Arrays.copyOfRange(nums, 1, len)), helper(Arrays.copyOf(nums, len - 1)));
    }

    //参考leetcode-198打家劫舍:https://leetcode-cn.com/problems/house-robber/，一排间房屋时，能获取到的最大金额
    //时间复杂度O(n)，空间复杂度O(1)
    private int helper(int[] nums) {
        int res = 0;
        int len = nums.length;
        int first = res = nums[0];
        if (len == 1) {
            return res;
        }
        int second = res = Math.max(first, nums[1]);

        for (int i = 2; i < len; i++) {
            res = Math.max(first + nums[i], second);
            first = second;
            second = res;
        }

        return res;
    }
}