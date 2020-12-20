package com.example.javalib.arg.week09;

//leetcode-541.反转字符串 II;https://leetcode-cn.com/problems/reverse-string-ii/submissions/
public class Test_541 {
    public static void main(String[] args) {
        Test_541 test_541 = new Test_541();
        int i = 3;
        System.out.println(++i);
        System.out.println(test_541.reverseStr2("abcdefghijklmn", 3));
    }

    //方法一：暴力法，碰到需要倒转的子字符串，就倒转
    //时间复杂度：O(n),空间复杂度O(k)
    public String reverseStr(String s, int k) {
        int len = s.length();
        String result = "";
        //先看看可以分为多少组合
        int subCount = len / k;
        //偶数组需要倒转，奇数组无需倒转
        for (int i = 0; i < subCount; i++) {
            String subStr = s.substring(i * k, i * k + k);
            result += (i & 1) == 0 ? convert(subStr) : subStr;
        }
        String endStr = s.substring(len - (len % k), len);
        return result + ((subCount & 1) == 0 ? convert(endStr) : endStr);
    }

    //将字符串倒转，时间复杂度O(k)，空间复杂度O(k)，需要k个字符串，和k个数组空间
    private String convert(String s) {
        String result = "";
        int len = s.length();
        char[] chars = s.toCharArray();
        for (int i = len - 1; i >= 0; i--) {
            result += chars[i];
        }
        return result;
    }

    //方法二：将string转为数组，然后直接在数组内交换位置
    //时间复杂度：O(n),最外层循环 s.length/2k，每个循环内部再循环k/2次
    //空间复杂度：O(n),开辟大小为n的数组保存每个字符
    public String reverseStr2(String s, int k) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i += 2 * k) {
            int start = i;
            int end = Math.min(i + k - 1, chars.length - 1);
            while (start < end) {
                char tmp = chars[start];
                chars[start++] = chars[end];
                chars[end--] = tmp;
            }
        }
        return new String(chars);
    }

}

