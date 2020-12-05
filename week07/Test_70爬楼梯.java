package com.example.javalib.arg.week01;

import java.security.InvalidParameterException;

//leetcode-70爬楼梯:https://leetcode-cn.com/problems/climbing-stairs/
public class Test_70 {
    public static void main(String[] args) {
        //1,2,3,5,8,13,21,34...
        Test_70 test_70 = new Test_70();
        System.out.println(test_70.climbStairs4(8, 1, 2));
    }

    //方法一：傻递归法，时间复杂度O(2^n),空间复杂度O(n)
    public int climbStairs1(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("invalid params");
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairs1(n - 1) + climbStairs1(n - 2);
    }

    //方法二：使用循环，并开辟新数组，保存每个节点的值，时间复杂度O(n),空间复杂度O(n)
    public int climbStairs2(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("invalid params");
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] result = new int[n];
        result[0] = 1;
        result[1] = 2;
        for (int i = 2; i < n; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
        return result[n - 1];
    }

    //方法三：对方法二的优化，只保存必要的3个数据，时间复杂度O(n),空间复杂度O(1)
    public int climbStairs3(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("invalid params");
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int first = 1;
        int second = 2;
        int result = first + second;
        for (int i = 2; i < n; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        return result;
    }

    //尾递归，时间复杂度O(n)，空间复杂度O(1)
    public int climbStairs4(int n, int first, int second) {
        if (n <= 0) {
            throw new IllegalArgumentException("invalid params");
        }
        if (n == 1) {
            return first;
        }
        if (n == 2) {
            return second;
        }
        return climbStairs4(n - 1, second, first + second);
    }
}
