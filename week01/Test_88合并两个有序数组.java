package com.example.javalib.arg;

import com.example.javalib.reflect.Test;

import java.util.Arrays;

//leetcode-88合并两个有序数组
public class Test_88 {
    public static void main(String[] args) {
        Test_88 test_88 = new Test_88();
        int[] num1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] num2 = {2, 5, 6};
        int n = 3;
        test_88.merge(num1, m, num2, n);
    }

    //方法一：双指针法，对方法二的优化，从num1的右边往左边填入较大的元素
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1, p = m + n - 1;
        while (p1 >= 0 && p2 >= 0) {
            nums1[p--] = nums1[p1] > nums2[p2] ? nums1[p1--] : nums2[p2--];
        }
        //表示num1的首部元素还没有比较完成，不用再做操作
        //if (p1 > 0) {
        //}

        //表示num2的元素还没有移动完成
        if (p2 > 0) {
            System.arraycopy(nums2, 0, nums1, 0, n - p2);
        }
    }

    //方法二：双指针法，开辟新数组copyNums1，复制nums1，比较开辟新数组copyNums1和nums2的元素，从左往右往nums1中填入
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int[] bakNums1 = new int[m];
        System.arraycopy(nums1, 0, bakNums1, 0, m);
        int p = 0, p1 = 0, p2 = 0;
        //nums1中保存较小的那个值
        while (p1 < m && p2 < n) {
            nums1[p++] = bakNums1[p1] < nums2[p2] ? bakNums1[p1++] : nums2[p2++];
        }
        //表示nums2中的元素已经移完，但bakNums1还没有，则从p1开始全部移入
        if (p1 < m) {
            System.arraycopy(bakNums1, p1, nums1, p, m - p1);
        }

        //表示bakNums1中的元素已经移完，但nums2还没有，则从p2开始全部移入
        if (p2 < n) {
            System.arraycopy(nums2, p2, nums1, p, n - p2);
        }

        for (int i : nums1) {
            System.out.println(i);
        }
    }

    //方法三:对方法四的优化，使用系统提供的排序方法
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    //方法四:先合并，再排序(目前还只会写冒泡排序)
    public void merge4(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        int len = m + n;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (nums1[j] > nums1[j + 1]) {
                    int tmp = nums1[j];
                    nums1[j] = nums1[j + 1];
                    nums1[j + 1] = tmp;
                }
            }
        }
    }
}