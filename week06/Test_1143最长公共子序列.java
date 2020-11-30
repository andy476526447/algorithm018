package com.example.javalib.arg.week06;

//leetcode-1143:最长公共子序列 https://leetcode-cn.com/problems/longest-common-subsequence/
public class Test_1143 {

    public static void main(String[] args) {
        Test_1143 test_1143 = new Test_1143();
        System.out.println(test_1143.longestCommonSubsequence("abcba", "abcbcba"));
    }

    //动态规划，借助于动态规划表来实现
    public int longestCommonSubsequence(String text1, String text2) {
        //列数
        int col = text1.length();
        //行数
        int row = text2.length();
        //动态规划表中第一行和第一列均先和空字符串进行比较，所以第一行和第一列的值均为0，该二维数组存储的值表示text1和text2的最长公共子字符串数
        int[][] result = new int[row + 1][col + 1];
        //从第2行和第2列开始
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                //如果text2在i-1处的字符和text1在j-1位置（都从0开始算）的字符一样，那么i行j列的值为其左上角顶点值+1，否则此处的值为左边值和上边值的较大者。
                if (text2.charAt(i - 1) == text1.charAt(j - 1)) {
                    result[i][j] = result[i - 1][j - 1] + 1;
                } else {
                    result[i][j] = Math.max(result[i][j - 1], result[i - 1][j]);
                }
            }
        }
        return result[row][col];
    }
}