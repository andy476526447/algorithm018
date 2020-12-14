package com.example.javalib.arg.week08;


//leetcode-190.颠倒二进制位:https://leetcode-cn.com/problems/reverse-bits/
public class Test_190 {

    public static void main(String[] args) {
        Test_190 test_190 = new Test_190();
        System.out.println(test_190.reverseBits(-3));
    }

    //时间复杂度为O(N)，N为整数的二进制位数。空间复杂度O(1)
    public int reverseBits(int n) {
        //预先设定结果为32个0
        int result = 0;
        int x;
        for (long i = 0; i < 32; i++) {
            //低(i+1)位的值
            x = n & 1;
            int mask = 1;
            //如果n的第(i+1)位为1，则将result的低(32 - i)置为1
            if (x == 1) {
                mask = 1 << (31 - i);
                //将指定位设置为1
                result = result ^ mask;
            }
            //右移一位
            n = n >> 1;
        }
        return result;
    }
}