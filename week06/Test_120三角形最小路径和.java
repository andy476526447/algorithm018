package com.example.javalib.arg.week06;

import java.util.ArrayList;
import java.util.List;

//leetcode-120.三角形最小路径和:https://leetcode-cn.com/problems/triangle/description/
public class Test_120 {

    public static void main(String[] args) {
        Test_120 test_120 = new Test_120();
        List<Integer> list1 = new ArrayList<>();
        list1.add(2);

        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);

        List<Integer> list3 = new ArrayList<>();
        list3.add(6);
        list3.add(5);
        list3.add(7);

        List<Integer> list4 = new ArrayList<>();
        list4.add(4);
        list4.add(1);
        list4.add(8);
        list4.add(3);

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        lists.add(list4);
        System.out.println(test_120.minimumTotal(lists));
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        //观察三角形数组的结构可知，第i行元素的个数也是i，最后一行数组中的元素个数即所在行数
        int n = triangle.size();//即表示三角形和行数，也表示最后一行数组的大小
        int[] result = new int[n];
        //初始保存第一行第一列的值
        result[0] = triangle.get(0).get(0);
        //三角形中每个位置的最短路劲，有三种不同的情形：
        // 1、左边界，即第一列中，每个位置的最短路径值 = 上一行第一个位置最短路径值 + 该行第一列位置的值，即：result[0] = result[i - 1] + triangle[i][0];
        // 2、右边界，即每一行的最后一个位置的最短路径值 = 上一行最右边的最短路径值 + 当前位置的值，即：result[i] = result [i -1] + triangle[i][i];
        // 3、中间元素，最短路径值 = min{上一行上一列最短路径值，上一行同一列最短路径值}，即：result[index] = min{result[j - 1], result[j]} + triangle[i][j];
        for (int i = 1; i < n; i++) {
            //这里从第i个元素开始保存，因为每个计算第i个位置的值时需要依赖上一轮第i-1位置的值，每一轮计算时不能先修改
            result[i] = result[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; j--) {
                //中间元素
                result[j] = Math.min(result[j - 1], result[j]) + triangle.get(i).get(j);
            }
            //左边界元素
            result[0] += triangle.get(i).get(0);
        }
        //计算完毕最后一行每个位置的最短路径后，获取最小值
        int min = result[0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, result[i]);
        }
        return min;
    }
}