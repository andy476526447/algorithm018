package com.example.javalib.arg.week06;

import java.util.Arrays;

//leetcode-53最大子序和:https://leetcode-cn.com/problems/maximum-subarray/
public class Test_53 {
    public static void main(String[] args) {
        Test_53 test_53 = new Test_53();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(test_53.maxSubArray5(nums));
    }

    //方法一：暴力法，枚举所有的子数组，计算出每个子数组的值，从中选取一个最大的值。时间复杂度O(n^3);空间复杂度O(n),主要是调用Arrays.copyOfRange时产生的
    //该方法在leetcode上运行提示“超出时间限制”
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int len = nums.length;
        int max = nums[0];
        //确定开头和结尾的索引
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                //每个子数组的表达式为：Arrays.copyOfRange(nums, i, j + 1)
                int sum = 0;
                for (int num : Arrays.copyOfRange(nums, i, j + 1)) {
                    sum += num;
                }
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    //方法二：也是暴力法，对方法一的优化。时间复杂度O(n^2)，空间复杂度O(n)
    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int len = nums.length;
        //定义一个数组，保存以当前位置元素开头的子数组的和
        int[] res = new int[len];
        Arrays.fill(res, nums[0]);
        int max = nums[0];
        for (int i = 0; i < len; i++) {
            //以nums[i]开头的子数组
            res[i] = nums[i];
            for (int j = i + 1; j < len; j++) {
                res[j] = res[j - 1] + nums[j];
            }
            //获取res数组中的最大值
            for (int r : res) {
                max = Math.max(max, r);
            }
        }
        return max;
    }

    //方法三：在方法二的基础上，对空间复杂度做进一步的优化。在方法二中可以发现，每次迭代的时只与上一个数组元素相关，所以可以定义若干变量来代替数组。
    //时间复杂度O(n^2)，空间复杂度O(1)
    public int maxSubArray3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        int max = nums[0];
        int sum = 0;
        for (int i = 0; i < len; i++) {
            max = Math.max(max, nums[i]);
            sum = nums[i];
            for (int j = i + 1; j < len; j++) {
                sum += nums[j];
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    //方法四：动态规划。这里我们以{-2, 1, -3, 4, -1, 2, 1, -5, 4}为例，看看递推公式：f(i)表示以第i个位置为结尾的子数组的最大值
    // f(1) = -2;
    // f(2) =  1; 因为f(1) < 0，所以这里不用再算上前面的子数组，此时最大值的子数组为[1]
    // f(3) = -2; 因为f(2) > 0, 所以这里就需要算上f(2)对应的子数组，此时最大值对应的子数组为[1,-2]
    // ......
    // f(i) = f(i-1) > 0? f(i - 1) + nums[i]:nums[i]; //通过上面的递推，可以得到通项公式
    // 这样 f(1)~f(n)就分别存储了以第i个位置结尾时，其子数组能得到的最大值
    // 时间复杂度为O(n),空间复杂度为O(n)
    public int maxSubArray4(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        //存储了以第i个位置结尾时，其子数组能得到的最大值
        int[] res = new int[len];
        res[0] = nums[0];
        for (int i = 1; i < len; i++) {
            res[i] = res[i - 1] > 0 ? res[i - 1] + nums[i] : nums[i];
        }
        int max = res[0];
        for (int r : res) {
            max = Math.max(max, r);
        }
        return max;
    }

    //方法五：动态规划。根据经验，方法四的空间复杂度还可以继续优化。优化后时间复杂度为O(n)，空间复杂度为O(1)
    public int maxSubArray5(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        int preRes = nums[0], curRes = 0, max = nums[0];

        for (int i = 1; i < len; i++) {
            curRes = preRes > 0 ? preRes + nums[i] : nums[i];
            preRes = curRes;
            max = Math.max(max, curRes);
        }
        return max;
    }
}