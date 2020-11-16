package com.example.javalib.arg.week04;

import java.util.HashMap;
import java.util.Map;

//leetCode-860.柠檬水找零:https://leetcode-cn.com/problems/lemonade-change/
public class Test_860 {
    public static void main(String[] args) {
        Test_860 test_860 = new Test_860();
        int[] bills = {20};
        System.out.println(test_860.lemonadeChange2(bills));
    }

    //方法一：贪心算法（感觉就是暴力法）。每收到一笔新的钱，就判断剩余的零钱是否满足找零：先使用大额面值，再使用小额面值
    //时间复杂度：最好的场景是收到第一笔钱就能判断出结果，时间复杂度为O(1);最坏的情况是遍历完才能出结果，时间复杂度为：O(n)。
    //空间复杂度：O(1)
    public boolean lemonadeChange(int[] bills) {
        Map<Integer, Integer> moneyMap = new HashMap<>();
        for (int money : bills) {
            if (money == 5) {
                moneyMap.put(money, moneyMap.getOrDefault(money, 0) + 1);
            } else if (money == 10) {
                if (moneyMap.getOrDefault(5, 0) == 0) {
                    return false;
                }
                moneyMap.put(5, moneyMap.getOrDefault(5, 0) - 1);
                moneyMap.put(money, moneyMap.getOrDefault(money, 0) + 1);
            } else {
                if (moneyMap.getOrDefault(10, 0) > 0) {
                    if (moneyMap.getOrDefault(5, 0) > 0) {
                        moneyMap.put(10, moneyMap.get(10) - 1);
                        moneyMap.put(5, moneyMap.get(5) - 1);
                    } else {
                        return false;
                    }
                } else {
                    if (moneyMap.getOrDefault(5, 0) >= 3) {
                        moneyMap.put(5, moneyMap.getOrDefault(5, 0) - 3);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //方法二：对方法一的优化，不需要使用HashMap，直接使用变量来表示数量
    public boolean lemonadeChange2(int[] bills) {
        int five = 0, ten = 0;
        for (int money : bills) {
            if (money == 5) {
                five++;
            } else if (money == 10) {
                if (five == 0) {
                    return false;
                }
                five--;
                ten++;
            } else {
                if (ten > 0) {
                    if (five > 0) {
                        ten--;
                        five--;
                    } else {
                        return false;
                    }
                } else {
                    if (five >= 3) {
                        five = five - 3;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}