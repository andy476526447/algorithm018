package com.example.javalib.arg.week02;

import java.util.PriorityQueue;
import java.util.Queue;

//leetcode-剑指Offer49丑数:https://leetcode-cn.com/problems/chou-shu-lcof/
public class Test_offer49 {

    public static void main(String[] args) {
        Test_offer49 test_offer49 = new Test_offer49();
        System.out.println();
        System.out.println(test_offer49.nthUglyNumber3(4));
    }


    //方法一：使用小顶堆来实现，时间复杂度为O(nk)；空间上主要是维护了一个堆，复杂度为O(n)
    public int nthUglyNumber1(int n) {
        if (n == 0) {
            return 0;
        }
        int count = 0;//用于计数
        int[] ugly = {2, 3, 5};
        Queue<Long> heap = new PriorityQueue<>();
        heap.offer(1L);
        //当前最小值出堆，如果已经是第n个了就返回，否则就把该值*2/3/5，并插入堆中
        while (!heap.isEmpty()) {
            long top = heap.poll();
            count++;
            if (count == n) {
                return (int) top;
            }
            //所有的的丑数，都是在前面丑数的基础上*2/3/5
            for (int i = 0; i < 3; i++) {
                //去重，由于是一边pop，一边offer，heap里面的元素数量会在n附近，时间复杂度为O(n)
                if (!heap.contains(top * ugly[i])) {
                    heap.offer(top * ugly[i]);
                }
            }
        }
        return 0;
    }

    //方法二：将前面n个丑数都列举出来并保存在数组中。时间复杂度为O(n),空间复杂度为O(n)
    public int nthUglyNumber2(int n) {
        int[] result = new int[n];
        result[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0; //除1以外，所有的丑数都是2/3/5的基础上再乘以2/3/5，所以除1以外这些丑数可以归为3类：*2，*3，*5三队，所以这里设定3个指针
        for (int i = 1; i < n; i++) {
            result[i] = Math.min(Math.min(result[p2] * 2, result[p3] * 3), result[p5] * 5);
            //由于每轮循环时是取最小数，而且最小数不能重复插入，所以3个对列中只要满足条件了，指针都需要后移
            if (result[i] == result[p2] * 2) {
                p2++;
            }

            if (result[i] == result[p3] * 3) {
                p3++;
            }

            if (result[i] == result[p5] * 5) {
                p5++;
            }

        }
        return result[n - 1];
    }

    //方法三：暴力法，时间复杂度为O(nLogk),空间复杂度O(1)
    public int nthUglyNumber3(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("args invalid");
        }
        int counter = 0;
        for (int i = 1; ; i++) {
            if (isUglyNum(i)) {
                counter++;
                if (counter == n) {
                    return i;
                }
            }

        }
    }

    //判断某个数是否为丑数，时间复杂度为 O(logn)
    private boolean isUglyNum(int num) {
        while (num % 2 == 0) {
            num /= 2;
        }
        while (num % 3 == 0) {
            num /= 3;
        }
        while (num % 5 == 0) {
            num /= 5;
        }
        return num == 1;
    }
}