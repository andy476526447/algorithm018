package com.example.javalib.arg.week02;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//leetcode-剑指Offer40.最小的k个数:https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
public class Test_offer40 {

    public static void main(String[] args) {
        Test_offer40 test_40 = new Test_offer40();
        int[] arr = {0, 0, 0, 2, 0, 5};
        test_40.getLeastNumbers3(arr, 0);
    }

    //方法一：先排序，再取前k个数，时间复杂度O(nlogn)，空间复杂度O(logn)
    public int[] getLeastNumbers1(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            return null;
        }
        Arrays.sort(arr);
        int[] result = new int[k];
        System.arraycopy(arr, 0, result, 0, k);
        return result;
    }

    //方法二：使用jdk自带的PriorityQueue实现小根堆，每个数都需要推入一次，每一次的时间复杂度为logn，所以时间复杂度为O(nlogn);空间复杂度为O(n)，因为维护了一个大小为n的堆。
    public int[] getLeastNumbers2(int[] arr, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i : arr) {
            priorityQueue.offer(i);
        }

        int[] result = new int[k];
        for (int j = 0; j < k; j++) {
            result[j] = priorityQueue.poll();
        }
        return result;
    }

    //方法三：建立大根堆;时间复杂度为O(nlogk)维护一个大小为k的大根堆，空间复杂度为O(k)
    public int[] getLeastNumbers3(int[] arr, int k) {
        int[] result = new int[k];
        if (k < 1) {
            return result;
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return t1 - integer;
            }
        });

        for (int i = 0; i < k; i++) {
            priorityQueue.offer(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (arr[i] < priorityQueue.peek()) {
                priorityQueue.poll();
                priorityQueue.offer(arr[i]);
            }
        }

        for (int i = 0; i < k; i++) {
            result[i] = priorityQueue.poll();
        }

        return result;
    }
}