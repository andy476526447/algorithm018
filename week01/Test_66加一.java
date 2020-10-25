package com.example.javalib.arg;

//leetcode-66. 加一
public class Test_66 {
    public static void main(String[] args) {
        Test_66 test_66 = new Test_66();
        int[] nums = {0};
        test_66.plusOne(nums);
    }

    //对方法二的一种优化
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        for (int m = len - 1; m >= 0; m--) {
            digits[m]++;
            if (digits[m] / 10 == 1) {
                digits[m] = digits[m] % 10;
            } else {
                return digits;
            }
        }
        digits = new int[len + 1];
        digits[0] = 1;
        return digits;
    }

    //方法二
    public int[] plusOne2(int[] digits) {
        int len = digits.length;
        boolean isLargest = true;
        for (int i = 0; i < len; i++) {
            if (digits[i] != 9) {
                isLargest = false;
                break;
            }
        }
        if (isLargest) {
            int[] newDig = new int[len + 1];
            newDig[0] = 1;
            for (int i : newDig) {
                System.out.print(";" + i);
            }
            return newDig;
        }

        for (int m = len - 1; m >= 0; m--) {
            System.out.println("m=" + m);
            if (digits[m] >= 9) {
                digits[m] = (digits[m] + 1) % 10;
            } else {
                digits[m]++;
                break;
            }
        }
        for (int i : digits) {
            System.out.print(";" + i);
        }

        return digits;
    }


    //方法三：暴力法，无法解决整数太大导致溢出的问题，提交到leetcode没有通过
    public int[] plusOne1(int[] digits) {
        int len = digits.length;

        long value = 0;
        for (int i = 0; i < len; i++) {
            value = value + digits[i] * (long) Math.pow(10, len - i - 1);
            System.out.println("i=" + i + ";value=" + value);
        }
        value = value + 1;

        boolean isLargest = true;
        for (int i = 0; i < len; i++) {
            if (digits[i] != 9) {
                isLargest = false;
                break;
            }
        }
        System.out.println("isLarge=" + isLargest + ";value=" + value);

        if (isLargest) {
            int[] newDig = new int[len + 1];
            newDig[0] = 1;
            for (int i : newDig) {
                System.out.print(";" + i);
            }
            return newDig;
        }

        System.out.println((9876543211l % 10));

        for (int m = len - 1; m >= 0; m--) {
            digits[m] = (int) (value % 10);
            System.out.println("m=" + m + ";" + digits[m]);
            value = value / 10;
        }

        System.out.println();
        for (int i : digits) {
            System.out.print(";" + i);
        }
        System.out.println();
        return digits;
    }
}
