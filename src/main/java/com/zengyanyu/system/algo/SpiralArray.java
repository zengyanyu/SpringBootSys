package com.zengyanyu.system.algo;

public class SpiralArray {

    public static void main(String[] args) {
        // 定义数组的大小（n x n），你可以修改这个值测试不同大小的数组
        int n = 10;
        // 生成螺旋数组
        int[][] spiralMatrix = generateSpiralArray(n);
        // 打印数组，方便查看结果
        printMatrix(spiralMatrix);
    }

    /**
     * 生成 n x n 的螺旋数组
     *
     * @param n 数组的边长
     * @return 填充好的螺旋二维数组
     */
    public static int[][] generateSpiralArray(int n) {
        // 校验输入合法性
        if (n <= 0) {
            throw new IllegalArgumentException("数组边长必须大于0");
        }

        // 创建 n x n 的二维数组
        int[][] matrix = new int[n][n];
        // 定义四个边界
        int top = 0;          // 上边界（起始行）
        int bottom = n - 1;   // 下边界（结束行）
        int left = 0;         // 左边界（起始列）
        int right = n - 1;    // 右边界（结束列）
        int currentNum = 1;   // 当前要填充的数字

        // 循环填充，直到所有数字（n*n）都填充完毕
        while (currentNum <= n * n) {
            // 1. 从左到右填充上边界行
            for (int i = left; i <= right; i++) {
                matrix[top][i] = currentNum++;
            }
            top++; // 上边界向下收缩

            // 2. 从上到下填充右边界列
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = currentNum++;
            }
            right--; // 右边界向左收缩

            // 3. 从右到左填充下边界行（需判断上下边界是否重合，避免重复填充）
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    matrix[bottom][i] = currentNum++;
                }
                bottom--; // 下边界向上收缩
            }

            // 4. 从下到上填充左边界列（需判断左右边界是否重合，避免重复填充）
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    matrix[i][left] = currentNum++;
                }
                left++; // 左边界向右收缩
            }
        }

        return matrix;
    }

    /**
     * 打印二维数组（格式化输出，方便查看）
     *
     * @param matrix 要打印的二维数组
     */
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                // 格式化输出，占3个字符宽度，对齐更美观
                System.out.printf("%3d ", num);
            }
            System.out.println();
        }
    }
}