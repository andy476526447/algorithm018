package com.example.javalib.arg.week09;

import java.util.HashMap;
import java.util.Map;

//leetcode-387.字符串中的第一个唯一字符; https://leetcode-cn.com/problems/first-unique-character-in-a-string/
public class Test_387 {
    public static void main(String[] args) {
        Test_387 test_387 = new Test_387();
        System.out.println(test_387.firstUniqChar2("laaav"));
        //System.out.println(s.indexOf('b', 0));

    }

    //方法一：采用hashmap来保存每个字符出现的次数，然后找到第一个出现次数为1的字符。
    //时间复杂度：O(n),其中n为字符串的长度,不管怎样，都需要先将s遍历一次
    //空间复杂度O(1)，主要来源于hashmap分配的空间
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>(26);
        for (Character c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int index = -1;
        for (Character c : s.toCharArray()) {
            index++;
            if (map.get(c) == 1) {
                return index;
            }
        }
        return -1;
    }

    //方法二：遍历每个字符，如果该字符在前面没有出现过，在后面的字符串中也没有出现过，则满足条件
    //时间复杂度：O(n^2)，每个字符需要遍历一次，每个字符还需要和后面字符串做判断
    //空间复杂度：O(1)，主要来源于数组的空间
    public int firstUniqChar2(String s) {
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            //表示当前字符在前面字符串没有出现过，此时判断是否在后面字符中有出现
            if (arr[ch - 'a'] == 0) {
                //在后面的字符串也没有出现
                if (s.indexOf(ch, i + 1) == -1) {
                    return i;
                }
                //遍历过了，做标记
                arr[ch - 'a'] = 1;

            }
        }
        return -1;
    }
}