package com.zengyanyu.system.algo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * Java 实现斐波那契数列的多种算法
 */
public class Fibonacci {

    // 用于记忆化递归的缓存
    private static Map<Integer, Long> memo = new HashMap<>();

    public static void main(String[] args) {
        // 测试计算第n项斐波那契数
        int n = 40;
        System.out.println("计算第 " + n + " 项斐波那契数：");

        // 1. 基础递归法
        long start1 = System.currentTimeMillis();
        long result1 = fibRecursion(n);
        long end1 = System.currentTimeMillis();
        System.out.println("递归法：" + result1 + "，耗时：" + (end1 - start1) + "ms");

        // 2. 记忆化递归法
        long start2 = System.currentTimeMillis();
        long result2 = fibMemoRecursion(n);
        long end2 = System.currentTimeMillis();
        System.out.println("记忆化递归：" + result2 + "，耗时：" + (end2 - start2) + "ms");

        // 3. 迭代法
        long start3 = System.currentTimeMillis();
        long result3 = fibIteration(n);
        long end3 = System.currentTimeMillis();
        System.out.println("迭代法：" + result3 + "，耗时：" + (end3 - start3) + "ms");

        // 4. 公式法（比内公式）
        long start4 = System.currentTimeMillis();
        BigDecimal result4 = fibFormula(n);
        long end4 = System.currentTimeMillis();
        System.out.println("公式法：" + result4 + "，耗时：" + (end4 - start4) + "ms");

        // 扩展：打印前n项斐波那契数列
        System.out.println("\n前 " + 10 + " 项斐波那契数列：");
        printFibonacciSeries(10);
    }

    /**
     * 1. 基础递归法（效率低，O(2ⁿ)，n较大时会栈溢出/超时）
     *
     * @param n 第n项
     * @return 第n项的值
     */
    public static long fibRecursion(int n) {
        // 边界校验
        if (n < 0) {
            throw new IllegalArgumentException("n不能为负数");
        }
        // 基准条件
        if (n == 0) return 0;
        if (n == 1) return 1;
        // 递归调用
        return fibRecursion(n - 1) + fibRecursion(n - 2);
    }

    /**
     * 2. 记忆化递归法（优化递归，O(n)，用缓存存储已计算结果）
     *
     * @param n 第n项
     * @return 第n项的值
     */
    public static long fibMemoRecursion(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n不能为负数");
        }
        // 基准条件 + 缓存命中
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        // 计算并缓存结果
        long result = fibMemoRecursion(n - 1) + fibMemoRecursion(n - 2);
        memo.put(n, result);
        return result;
    }

    /**
     * 3. 迭代法（最优解，O(n)时间，O(1)空间）
     *
     * @param n 第n项
     * @return 第n项的值
     */
    public static long fibIteration(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n不能为负数");
        }
        if (n == 0) return 0;
        if (n == 1) return 1;

        // 初始化前两项
        long prevPrev = 0; // F(n-2)
        long prev = 1;     // F(n-1)
        long current = 0;  // F(n)

        // 从第2项开始迭代计算
        for (int i = 2; i <= n; i++) {
            current = prev + prevPrev;
            prevPrev = prev;
            prev = current;
        }
        return current;
    }

    /**
     * 4. 公式法（比内公式，O(1)时间，但受限于浮点数精度）
     *
     * @param n 第n项
     * @return 第n项的值（BigDecimal保证精度）
     */
    public static BigDecimal fibFormula(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n不能为负数");
        }
        if (n == 0) return BigDecimal.ZERO;
        if (n == 1) return BigDecimal.ONE;

        // 黄金分割比 φ = (1+√5)/2
        BigDecimal sqrt5 = new BigDecimal(Math.sqrt(5));
        BigDecimal phi = BigDecimal.ONE.add(sqrt5).divide(new BigDecimal(2), 20, RoundingMode.HALF_UP);
        // 共轭数 ψ = (1-√5)/2
        BigDecimal psi = BigDecimal.ONE.subtract(sqrt5).divide(new BigDecimal(2), 20, RoundingMode.HALF_UP);

        // 比内公式：F(n) = (φⁿ - ψⁿ) / √5
        BigDecimal phiPower = phi.pow(n);
        BigDecimal psiPower = psi.pow(n);
        BigDecimal result = phiPower.subtract(psiPower).divide(sqrt5, 0, RoundingMode.HALF_UP);
        return result;
    }

    /**
     * 扩展功能：打印前count项斐波那契数列
     *
     * @param count 要打印的项数
     */
    public static void printFibonacciSeries(int count) {
        if (count <= 0) {
            System.out.println("请输入大于0的项数");
            return;
        }
        long a = 0, b = 1;
        System.out.print(a);
        if (count > 1) {
            System.out.print(", " + b);
        }
        for (int i = 2; i < count; i++) {
            long c = a + b;
            System.out.print(", " + c);
            a = b;
            b = c;
        }
        System.out.println();
    }
}