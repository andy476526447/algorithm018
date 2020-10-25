package com.example.javalib.arg;

//leetcode-26删除排序数组中的重复项
public class Test_26 {

    public static void main(String[] args) {
        Test_26 test_26 = new Test_26();
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(test_26.removeDuplicates1(nums));
    }


    public int removeDuplicates1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int dupCount = 0;
        for (int i = 0; i < nums.length - dupCount - 1; i++) {
            //有重复
            while (nums[i] == nums[i + 1]) {
                for (int j = i; j < nums.length; j++) {
                    nums[j] = nums[j + 1];
                }
            }
        }
        return nums.length - dupCount;
    }

    //双指针法
    public int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 1;
        for (int j = 0; j < nums.length - 1; j++) {
            if (nums[j + 1] != nums[j]) {
                nums[i] = nums[j + 1];
                i++;
            }
        }
        return i;
    }
}
