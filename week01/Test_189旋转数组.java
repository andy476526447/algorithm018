package com.example.javalib.arg;

//leetcode-189旋转数组
public class Test_189 {

    public static void main(String[] args) {
        Test_189 test_189 = new Test_189();
        int[] nums = {1, 2, 3, 4, 5, 6, 7};

    }

    //暴力法
    public void rotate(int[] nums, int k) {
        if (nums == null) {
            return;
        }
        if (k <= 0) {
            throw new IllegalArgumentException("k must more than 0");
        }
        int len = nums.length;
        int tmp;
        for (int i = 0; i < k; i++) {
            tmp = nums[len - 1];
            for (int j = len - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = tmp;
        }
    }

    /*public void rotate2(int[] nums, int k) {
        if (nums == null) {
            return;
        }
        if (k <= 0) {
            throw new IllegalArgumentException("k must more than 0");
        }
        int len = nums.length;
        k = k % len;
        int tmp = nums[len - 1];
        for (int i = len - 1; i > k; i--) {
            nums[i] = nums[i - k];
        }
    }*/
}