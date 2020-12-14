package com.example.javalib.arg.week08;

//leetcode-191.位1的个数:https://leetcode-cn.com/problems/number-of-1-bits/
public class Test_191 {


    //方法一：移位，判断奇偶确定最后一位是否为1。时间复杂度O(N),N表示二进制的位数;空间复杂度O(1)
    public int hammingWeight1(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            //如果是奇数，表示最后一位为1
            if ((n & 1) == 1) {
                count++;
            }
            //左移一位
            n = n >> 1;
        }
        return count;
    }

    //方法二：采用掩码的方式。时间复杂度O(N),N表示二进制的位数;空间复杂度O(1)
    public int hammingWeight2(int n) {
        int count = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            //如果从左往右第i位为1，& mask后不会为0
            if ((n & mask) != 0) {
                count++;
            }
            mask = mask << 1;
        }
        return count;
    }

    //方法三：利用 X & (X - 1) 可以将最后一位1清零。时间复杂度：O(N),N表示二进制数中1的数量。空间复杂度：O(1)
    public int hammingWeight3(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }


    //方法四：采用递归：时间复杂度：O(N),N表示二进制数中1的数量;空间复杂度：取决于递归的深度，这里也是O(N)
    public int hammingWeight4(int n) {
        return n == 0 ? 0 : hammingWeight4(n & (n - 1)) + 1;
    }
}