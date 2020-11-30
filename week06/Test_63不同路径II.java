package com.example.javalib.arg.week06;

//leetcode-63.不同路径II:https://leetcode-cn.com/problems/unique-paths-ii/
public class Test_63 {

    public static void main(String[] args) {
        Test_63 test_63 = new Test_63();
        int[][] nums = {{0, 1, 0}, {0, 0, 0}};
        System.out.println(test_63.uniquePathsWithObstacles2(nums));
    }

    //方法一：时间复杂度O(m * n),空间复杂度O(m * n)
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //行数
        int row = obstacleGrid.length;
        //列数
        int col = obstacleGrid[0].length;
        //用于存储到达指定位置的路径数
        int[][] result = new int[row][col];
        //第一行，如果某个位置有障碍物，那么从这个障碍物开始，到达该行后面的所有位置的路径数都是0
        for (int i = 0; i < col; i++) {
            if (obstacleGrid[0][i] == 1) {
                for (int j = i; j < col; j++) {
                    result[0][j] = 0;
                }
                break;
            } else {
                result[0][i] = 1;
            }
        }

        //第一列，如果某个位置有障碍物，那么从这个障碍物开始，到达该列后面的所有位置的路径数都是0
        for (int i = 0; i < row; i++) {
            if (obstacleGrid[i][0] == 1) {
                for (int j = i; j < row; j++) {
                    result[j][0] = 0;
                }
                break;
            } else {
                result[i][0] = 1;
            }
        }

        //从上往下依次遍历每一行
        for (int i = 1; i < row; i++) {
            //从左往右依次遍历每一列
            for (int j = 1; j < col; j++) {
                //如果该处有障碍物，那么result对应的地方的值为0
                if (obstacleGrid[i][j] == 1) {
                    result[i][j] = 0;
                } else {
                    //如果该处没有障碍物,那么指定位置的值 = 同一行前一个位置的值 + 同一列上一个位置的值
                    result[i][j] = result[i][j - 1] + result[i - 1][j];
                }
            }
        }

        return result[row - 1][col - 1];
    }

    //方法二：在方法一的基础上，对空间复杂度做了优化，空间复杂度为O(n),n为二维数组的列数
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[] result = new int[col];
        boolean flag = false;
        for (int i = 0; i < col; i++) {
            if (obstacleGrid[0][i] == 1) {
                flag = true;
                break;
            }
            if (!flag) {
                result[i] = 1;
            }
        }

        for (int i = 1; i < row; i++) {
            //当前行的第一个元素为障碍物
            if (obstacleGrid[i][0] == 1) {
                result[0] = 0;
            }
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] == 1) {
                    result[j] = 0;
                } else {
                    result[j] += result[j - 1];
                }
            }
        }

        return result[col - 1];
    }
}