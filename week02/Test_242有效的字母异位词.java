package com.example.javalib.arg.week02;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Test_242 {

    public static void main(String[] args) {
        char[] c1 = {'a', 'b'};
        char[] c2 = {'a', 'b'};
        System.out.println(Arrays.equals(c1, c2));
    }

    //方法一：将字符串转为数组，数组排序，然后依次比较这两个数组。由于使用了排序，时间复杂度应该是O(nlogn);额外开辟了数组空间，空间复杂度为O(n)
    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        Arrays.sort(sArr);
        Arrays.sort(tArr);
        return Arrays.equals(sArr, tArr);
       /* for (int i = 0; i < s.length(); i++) {
            if (sArr[i] != tArr[i]) {
                return false;
            }
        }
        return true;*/
    }

    //方法二：和方法一思路一样，只是采用了堆排序。时间复杂度和空间复杂度和方法一一样，但由于入堆、出堆操作比较多，实际上慢了很多。
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Queue<Character> pq1 = new PriorityQueue<>();
        for (int i = 0; i < s.length(); i++) {
            pq1.offer(s.charAt(i));
        }
        Queue<Character> pq2 = new PriorityQueue<>();
        for (int j = 0; j < t.length(); j++) {
            pq2.offer(t.charAt(j));
        }
        while (!pq1.isEmpty()) {
            if (pq1.poll() != pq2.poll()) {
                return false;
            }
        }
        return true;
    }

    //方法三：借助HashMap来实现。需要把每个字符插入到hashmap中，时间复杂度最好是O(n),最坏是O(nlogn)；由于维护了一个容量为n的HashMap，空间复杂度O（n）
    public boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (int j = 0; j < t.length(); j++) {
            char c = t.charAt(j);
            if (!map.containsKey(c) || map.get(c) == 0) {
                return false;
            } else {
                map.put(c, map.get(c) - 1);
            }
        }

        return true;
    }

    //方法四：和hashmap法类似，只不过用数组代替hashmap
    public boolean isAnagram4(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] tmp = new int[26];
        for (int i = 0; i < s.length(); i++) {
            tmp[s.charAt(i) - 'a']++;
        }
        for (int j = 0; j < t.length(); j++) {
            tmp[t.charAt(j) - 'a']--;
            if (tmp[t.charAt(j) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
