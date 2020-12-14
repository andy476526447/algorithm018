package com.example.javalib.arg.week08;

//leetcode-231.2的幂:https://leetcode-cn.com/problems/power-of-two/
public class Test_231 {

    //方法一：通过判断整数n的二进制数，非符号位中1的个数是否超过1确定。时间复杂度: O(N),N为二进制数中1的个数。空间复杂度：O(1)
    public boolean isPowerOfTwo(int n) {
        //这里防止最小负整数-2147483648误判(1000 ... 0000,31个0)
        long num = (long) n;
        if (num == 0) {
            return false;
        }
        int count = 0;
        while (num != 0) {
            count++;
            if (count > 1) {
                return false;
            }
            //将最后一位1清零
            num &= (num - 1);
        }
        return true;
    }

    //对方法一的优化
    public boolean isPowerOfTwo2(int n) {
        if (n <= 0) {
            return false;
        }
        int count = 0;
        while (n != 0) {
            count++;
            if (count > 1) {
                return false;
            }
            n &= (n - 1);
        }
        return true;
    }

    //通过判断 x & -x的值是否与 x相同来判断。时间复杂度O(1)，空间复杂度：O(1)
    public boolean isPowerOfTwo3(int n) {
        if (n < 0) {
            return false;
        }
        //n & -n 用于得到最低位1
        return (n & -n) == n;
    }
}