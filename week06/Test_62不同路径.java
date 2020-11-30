package com.example.javalib.arg.week06;

import java.util.Arrays;

//leetcode-62.不同路径https://leetcode-cn.com/problems/unique-paths/
public class Test_62 {

    public static void main(String[] args) {
        Test_62 test_62 = new Test_62();
        System.out.println(test_62.uniquePaths6(7, 3));
    }

    //方法一：动态规划；时间复杂度O(m * n),空间复杂度O(m * n)
    public int uniquePaths(int m, int n) {
        //该数组用于保存到达指定点的路径数
        int[][] nums = new int[m][n];
        //到达第一排的所有位置的路径均只有一种
        for (int i = 1; i < n; i++) {
            nums[0][i] = 1;
        }
        //到达第一列的所有位置的路径均只有一种
        for (int i = 1; i < m; i++) {
            nums[i][0] = 1;
        }
        //到达每一个位置的路径数 = 到达其左边位置的路径数 + 到达其上面位置的路径数
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                nums[i][j] = nums[i - 1][j] + nums[i][j - 1];
            }
        }
        //到达目的位置的路径数
        return nums[m - 1][n - 1];
    }

    //方法二：对方法一代码的优化，使代码更简洁，时间复杂度和空间复杂度没有变化，都是O(m * n)
    public int uniquePaths2(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        //该数组用于保存到达指定点的路径数
        int[][] nums = new int[m][n];
        for (int i = 1; i < m; i++) {
            nums[i][0] = 1;
            for (int j = 1; j < n; j++) {
                nums[0][j] = 1;
                nums[i][j] = nums[i - 1][j] + nums[i][j - 1];
            }
        }
        //到达目的位置的路径数
        return nums[m - 1][n - 1];
    }

    //方法三：在方法一的基础上优化了空间，空间复杂度为O(n)
    public int uniquePaths3(int m, int n) {
        //用于保存上一行每个位置的结果
        int[] pre = new int[n];
        //用于保存当前行每个位置的结果
        int[] cur = new int[n];
        //先赋初始值，到达第一行每个位置的路径均只有1个
        Arrays.fill(pre, 1);
        Arrays.fill(cur, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //当前行某个位置的值 = 当前行前一个位置的值 + 上一行该列的值
                cur[j] = cur[j - 1] + pre[j];
            }
            //当前列计算完毕后，保存到pre数组中，进入到下一行
            pre = cur.clone();
        }
        return cur[n - 1];
    }

    //方法四：在方法三的基础上再次优化，通过观察可知，pre数组中很多元素是可以重复使用的，空间复杂度O(n)
    public int uniquePaths4(int m, int n) {
        int[] result = new int[n];
        Arrays.fill(result, 1);
        for (int i = 1; i < m; i++) {
            //每一轮循环后，result都是保存的到达第i行每个位置的路径数。
            for (int j = 1; j < n; j++) {
                //已经修改过的值表示当前行位置的值，未修改过的值对应的是上一行位置的值。所以数组中的某个位置的新值(当前行某个位置的新值) = 数组当前位置的原值（上一行同列位置的值） + 数组中上一个位置的值（当前行前一个位置的值）
                result[j] += result[j - 1];
            }
        }
        return result[n - 1];
    }

    //方法五：公式法，排列组合问题，从起点终点，一共要走(m + n - 1)步，但都是向下或者向右走，从其中选择(m-1)步来向下走。时间复杂度为O(m + n)，空间复杂度为O(1)
    //该方法提交时导致了数组越界
    public int uniquePaths5(int m, int n) {
        //根据公式，计算出来的公式结果 = (m + n - 2)! / (m - 1)! / (n - 1)!
        return (int) (f(m + n - 2) / f(m - 1) / f(n - 1));
    }

    //辅助方法，计算阶乘
    public long f(int m) {
        long result = 1;
        for (int i = 1; i <= m; i++) {
            result = result * i;
        }
        return result;
    }

    //方法六：递归法
    public int uniquePaths6(int m, int n) {
        return helper(1, 1, m, n);
    }

    public int helper(int i, int j, int m, int n) {
        //边界条件的判断
        if (i > m || j > n)
            return 0;
        if ((i == m && j == n))
            return 1;
        //从右边走有多少条路径
        int right = helper(i + 1, j, m, n);
        //从下边走有多少条路径
        int down = helper(i, j + 1, m, n);
        //返回总的路径
        return right + down;
    }
}