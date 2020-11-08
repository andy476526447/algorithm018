package com.example.javalib.arg.week03;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//leetcode-22.括号生成https://leetcode-cn.com/problems/generate-parentheses/
public class Test_22 {
    public static void main(String[] args) {
        Test_22 test_22 = new Test_22();
        long t1 = System.currentTimeMillis();
        System.out.println(test_22.generateParenthesis(10));
        System.out.println(System.currentTimeMillis() - t1);
    }

    //=================================================================
    //方法一：暴力法，先用递归，将所有可能的情况列出来，然后判断该每种情况是否是合法的；
    //递归的时间复杂度是O(2^2n)，判断是否合法的时间复杂度是O(n)，所以时间复杂的是O(n*2^2n);
    //由于递归的深度为2n，所以递归的空间复杂度为O(n),而每一次判断是否合法的空间复杂度是O(n)，所以空间复杂度为O(n^2)
    List<String> result = new ArrayList<>();
    int max = 0;

    public List<String> generateParenthesis(int n) {
        max = 2 * n;
        recursion(0, "");
        return result;
    }

    public void recursion(int level, String s) {
        if (level >= max) {
            if (isValid2(s)) {
                result.add(s);
            }
            return;
        }

        recursion(level + 1, s + "(");
        recursion(level + 1, s + ")");
    }

    //判断给定是括号字符串是否合法，由于要遍历每个字符，所以时间复杂度为O(n)；由于维护了一个大小为n的栈，所以空间复杂度为O(n)
    private boolean isValid(String kuohaos) {
        Deque<Character> stack = new ArrayDeque<>();
        char[] chars = kuohaos.toCharArray();
        //对于每一个括号，如果是"("，就入栈；如果是")"，如果此时栈为空，说明非法，如果栈不为空，则pop一个"("
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                stack.push(chars[i]);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();

            }
        }
        //最后栈为空，表示正好配对完成，是合法的；如果不是空，说明不合法。
        return stack.isEmpty();
    }

    //判断是否合法的方法可以优化，不需要维护栈，只需要用一个变量来表示，如果是"("，就+1；如果是")"，如果此时变量为0，说明非法，如果变量不为0，则减1
    private boolean isValid2(String kuohaos) {
        int counter = 0;
        for (int i = 0; i < kuohaos.length(); i++) {
            if (kuohaos.charAt(i) == '(') {
                counter++;
            } else {
                if (counter == 0) {
                    return false;
                }
                counter--;
            }
        }
        return counter == 0;
    }

    //=================================================================
    //方法二，对方法一的优化，添加"("的规则是只要其数量小于n即可，添加")"的规则是当前")"的数量比"("少即可。
    //时间复杂度是O(2^n),空间复杂度是O(n)
    public List<String> generateParenthesis2(int n) {
        max = n;
        recursion2(0, 0, "");
        return result;
    }

    public void recursion2(int left, int right, String s) {
        if (left >= max && right >= max) {
            //System.out.println(s);
            result.add(s);
            return;
        }
        if (left < max) {
            recursion2(left + 1, right, s + "(");
        }

        if (right < left) {
            recursion2(left, right + 1, s + ")");
        }
    }
}
