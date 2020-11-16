package com.example.javalib.arg.week04;

//leetcode-122.买卖股票的最佳时机II:https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
public class Test_122 {

    public static void main(String[] args) {
        Test_122 test_122 = new Test_122();
        int[] nums = {7, 6, 4, 3, 1};
        System.out.println(test_122.maxProfit(nums));
    }

    //贪心算法：时间复杂度O(n)，空间复杂度O(1)
    public int maxProfit(int[] prices) {
        int total = 0;
        int len = prices.length;
        for (int i = 0; i < len - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                total = total + prices[i + 1] - prices[i];
            }
        }
        return total;
    }
}
