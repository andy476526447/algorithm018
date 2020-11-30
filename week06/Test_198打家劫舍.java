package com.example.javalib.arg.week06;

//leetcode-198打家劫舍:https://leetcode-cn.com/problems/house-robber/
public class Test_198 {
    public static void main(String[] args) {
        Test_198 test_198 = new Test_198();
        int[] nums = {2, 7, 9, 3, 1};
        System.out.println(test_198.rob(nums));
    }

    //方法一：动态规划，用一个2行n列的二维数组来存储每个房屋偷和不偷时能得到的最大金额。时间复杂度：O(n)，空间复杂度:O(n)
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        //每一个房屋有偷和不偷两种选择，定义一个只有两行的二维数组，第一行用于保存不偷取当前房屋时能得到的最大金额，第二行用于保存偷取当前房屋时能得到的最大金额
        int[][] res = new int[len][2];

        //初始时，不偷取第一个房间时，得到的最大金额
        res[0][0] = 0;
        //偷取第一个房间时，得到的最大金额
        res[0][1] = nums[0];

        //从第二个房屋开始，会根据前面一个房屋的偷取情况来决定当前房屋偷取与不偷取时的最大金额
        for (int i = 1; i < len; i++) {
            //第i个房间不偷时，最大金额 = 前一间房屋偷取和不偷取时对应的金额的较大者
            res[i][0] = Math.max(res[i - 1][0], res[i - 1][1]);
            //第i个房间偷取时，最大金额 = 前一间房屋不偷取时的金额 + 当前房屋的金额
            res[i][1] = res[i - 1][0] + nums[i];
        }

        //整个过程的最大金额 = 最后一个房屋偷取和不偷取时的较大者
        return Math.max(res[len - 1][0], res[len - 1][1]);
    }


    //方法二：动态规划。定义一个一维数组，用于保存在每间房屋时能偷取的最大总金额。时间复杂度：O(n)，空间复杂度:O(n)。空间复杂度上，比方法一少一半。
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        //该数组中的存储的值是，走到第n间房屋时能够偷取到的最大金额（即总金额）
        int[] res = new int[len];
        //如果只有一间房屋，能获得的最大金额就是偷取第一家
        res[0] = nums[0];
        //如果有两间房屋，能获得的最大金额 = max{偷第一家，偷第二家
        res[1] = Math.max(res[0], nums[1]);
        //如果有三间房屋，能获得的最大金额 = max{第1间房屋时能偷得的最大金额 + 第3间房屋的金额，第二间房屋时能偷得的最大金额}，即res[2] = max{res[0]+nums[2], res[1]}
        //以此类推，res[i] = max {res[i-2] + num[i], res[i - 1]}
        for (int i = 2; i < len; i++) {
            res[i] = Math.max(res[i - 2] + nums[i], res[i - 1]);
        }
        return res[len - 1];
    }


    //方法三：对方法一的优化，每间房屋时能获得的最大金额只和前面两间房屋能获得的最大金额有关，所以只需要保存前两间的最大金额即可
    public int rob3(int[] nums) {
        int res = 0;
        if (nums == null || nums.length == 0) {
            return res;
        }
        int len = nums.length;
        int first = res = nums[0];
        if (len == 1) {
            return res;
        }
        int second = res = Math.max(first, nums[1]);

        for (int i = 2; i < len; i++) {
            res = Math.max(first + nums[i], second);
            first = second;
            second = res;
        }
        return res;
    }
}