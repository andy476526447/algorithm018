package com.example.javalib.arg.week02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

////leetcode-49字母异位词分组
public class Test_49 {

    public static void main(String[] args) {
        String[] s = {"eat", "tea", "tan", "ate", "nat", "bat"};
        Test_49 test_49 = new Test_49();
        System.out.println(test_49.groupAnagrams3(s));
    }

    //方法一：暴力法,时间复杂度为O(kn^2)
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        List<String> srcList = Arrays.asList(strs);
        for (int i = 0; i < srcList.size(); i++) {
            if (srcList.get(i) == null) {
                continue;
            }
            List<String> subList = new ArrayList<>();
            subList.add(srcList.get(i));
            for (int j = i + 1; j < srcList.size(); j++) {
                if (isAnagram(srcList.get(i), srcList.get(j))) {
                    subList.add(srcList.get(j));
                    srcList.set(j, null);
                }
            }
            result.add(subList);
        }
        return result;
    }

    //辅助函数，用于判断两个字符串是否为字母异位词。时间复杂度O(k),k表示字符串的长度
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
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


    //方法二：时间复杂度O(NKlogK)(N为数组的长度，K表示每一个字符串的长度)；数组的每个元素都拆为字符，所以空间复杂度O(KN)，
    public List<List<String>> groupAnagrams3(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String key = String.valueOf(arr);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<String>());
            }
            map.get(key).add(str);
        }

        /*for (String string : map.keySet()) {
            result.add(map.get(string));
        }
        return result;*/
        result.addAll(map.values());
        return result;
    }
}