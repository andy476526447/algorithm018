package com.example.javalib.arg;

//leetcode-283移动零
public class Test_283 {
    public static void main(String[] args) {
        Test_283 test_283 = new Test_283();
        int[] arr = {0, 1, 0, 3, 12, 0, 0, 2, 5, 0};

        test_283.moveZeroes(arr);
    }

    //1、采用双指针法，指针i表示目标数组的下标，指针j表示原数组下标。
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i++;
            }
        }
    }

    //2、发现当前位置是0，将后面第一个不为0的数来填充，且将填充的地方设置为0。
    public void moveZeroes2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] != 0) {
                        nums[i] = nums[j];
                        nums[j] = 0;
                        break;
                    }
                }
            }

        }
    }

    //3、开辟一个新数组,空间复杂度O(n)
    public void moveZeroes3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int[] result = new int[nums.length];
        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            result[i] = 0;
            if (nums[i] != 0) {
                result[p] = nums[i];
                p++;
            }
        }
    }
}