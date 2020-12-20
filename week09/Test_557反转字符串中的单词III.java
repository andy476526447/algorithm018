package com.example.javalib.arg.week09;

import java.util.Arrays;
//leetcode-557.反转字符串中的单词III https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/
public class Test_557 {

    public static void main(String[] args) {
        Test_557 test_557 = new Test_557();
        System.out.println(test_557.reverseWords3("Let"));
    }

    //方法一：对字符串拆分-反转-组合。
    //时间复杂度：O(n),n为字符串的长度
    //空间复杂度：O(n),开辟一个数组存储每个单词，在java中每个单词又占用word长度的空间
    public String reverseWords(String s) {
        //对字符串进行拆分
        String[] strArray = s.split(" ");
        for (int i = 0; i < strArray.length; i++) {
            //对每个word进行反转
            char[] chars = strArray[i].toCharArray();
            int start = 0;
            int end = chars.length - 1;
            while (start < end) {
                char tmp = chars[start];
                chars[start++] = chars[end];
                chars[end--] = tmp;
            }
            StringBuilder sb = new StringBuilder();
            for (char ch : chars) {
                sb.append(ch);
            }
            strArray[i] = new String(sb);
        }
        //组合字符串
        return String.join(" ", strArray);
    }

    public String reverseWords2(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        int i = 0;
        while (i < len) {
            int start = i;
            while (i < len && s.charAt(i) != ' ') {
                i++;
            }
            for (int end = i; end > start; end--) {
                sb.append(s.charAt(end - 1));
            }
            sb.append(" ");
            i++;
        }
        return new String(sb).trim();
    }

    public String reverseWords3(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        int i = 0;
        while (i < len) {
            int start = i;
            while (i < len && s.charAt(i) != ' ') {
                i++;
            }
            for (int end = i; end > start; end--) {
                sb.append(s.charAt(end - 1));
            }
            if (i < len) {
                sb.append(" ");
                i++;
            }
        }
        return sb.toString();
    }
}
