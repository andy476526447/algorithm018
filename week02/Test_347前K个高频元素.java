package com.example.javalib.arg.week02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

//leetcode-347前K个高频元素
public class Test_347 {
    //方法一：借助HashMap和堆排序来实现。时间复杂度：建立HashMap为O(n);建立堆O(X)~O(XlogX),X为不重复数的个数；从堆中找出前k个数O(klogX)
    //所以总时间复杂度最坏为O(NlogN)。空间复杂度：存储每个元素O(n)
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        for (int key : nums) {
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        Queue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry t0, Map.Entry t1) {
                return (int) t1.getValue() - (int) t0.getValue();
            }
        });

        for (Map.Entry entry : map.entrySet()) {
            pq.offer(entry);
        }

        for (int i = 0; i < k; i++) {
            result[i] = pq.poll().getKey();
        }
        return result;
    }

    //方法二：对方法一的优化，使用堆的时候只需要维护一个大小为k的堆即可。另外合理使用api自带的一些功能可以减少代码
    public int[] topKFrequent2(int[] nums, int k) {
        int[] result = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        for (int key : nums) {
            map.put(key, map.getOrDefault(key, 0) + 1); //这里使用getOrDefault方法可以减少判断是否已经存在
        }
        //建立一个小根堆，使用Map自带的Entry来存储K,V
        Queue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry t0, Map.Entry t1) {
                return (int) t0.getValue() - (int) t1.getValue();
            }
        });
        List<Map.Entry> entryList = new ArrayList<Map.Entry>(map.entrySet());
        for (int i = 0; i < k; i++) {
            pq.offer(entryList.get(i));
        }
        for (int i = k; i < map.size(); i++) {
            if ((int) entryList.get(i).getValue() >= pq.peek().getValue()) {
                pq.poll();
                pq.offer(entryList.get(i));
            }
        }
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll().getKey();
        }
        return result;
    }


}
