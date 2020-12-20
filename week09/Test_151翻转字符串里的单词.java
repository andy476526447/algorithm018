package com.example.javalib.arg.week09;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

//leetcode-151.翻转字符串里的单词 https://leetcode-cn.com/problems/reverse-words-in-a-string/submissions/
public class Test_151 {
    public static void main(String[] args) {
        Test_151 test_151 = new Test_151();
        System.out.println(test_151.reverseWords4("  leetcode    is     very     good!  "));

        String[] strArray = {"ab","cd","ef"};
        System.out.println(String.join(" ",strArray));
    }

    //方法一：把各个单词存入list，使用sdk自带的方法倒转，然后转为字符串。
    //时间复杂度：O(k),k表示单词的个数
    //空间复杂度：O(n)，n表示字符串的长度。java中的字符串需要n个空间来存储
    public String reverseWords(String s) {
        String[] strArray = s.trim().split(" ");
        List<String> list = new ArrayList<>();
        for (String str : strArray) {
            if (str.trim().length() != 0) {
                list.add(str);
            }
        }
        Collections.reverse(list);
        String result = "";
        for (String str : list) {
            result += str + " ";
        }
        return result.trim();
    }


    //方法二：把单词存入数组，倒置，再组装为字符字符串
    //时间复杂度：O(k),k表示word的数目
    //空间复杂度：O(n)
    public String reverseWords2(String s) {
        String resuslt = "";
        String[] strArray = s.split(" ");
        System.out.println(strArray.length);
        int start = 0;
        int end = strArray.length - 1;
        String tmp;
        while (start < end) {
            tmp = strArray[start];
            strArray[start++] = strArray[end];
            strArray[end--] = tmp;
        }
        for (String str : strArray) {
            if (str.trim().length() != 0) {
                resuslt += (str + " ");
            }
        }
        return resuslt.trim();
    }

    //方法三，采用Stack来实现。实际上和前面两种方法类似
    public String reverseWords3(String s) {
        String[] strArray = s.trim().split("\\s+");
        Deque<String> stack = new ArrayDeque<>();
        for (String str : strArray) {
            stack.push(str);
        }
        return String.join(" ", stack);
    }

    public String reverseWords4(String s) {
        List<String> list = Arrays.asList(s.trim().split(" +"));
        Collections.reverse(list);
        return String.join(" ",list);
    }
}