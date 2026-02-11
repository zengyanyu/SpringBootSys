package com.zengyanyu.system.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * Java 实现质数（素数）的判断与生成
 */
public class PrimeNumber {

    public static void main(String[] args) {
        // 测试1：判断单个数字是否为质数
        int num = 97;
        System.out.println(num + " 是否为质数：" + isPrime(num));

        // 测试2：生成 100 以内的所有质数（埃氏筛）
        int max = 1000;
        List<Integer> primesUnder100 = sieveOfEratosthenes(max);
        System.out.println(max + " 以内的所有质数：" + primesUnder100);

        // 测试3：生成 100 以内的所有质数（线性筛）
        List<Integer> primesLinear = linearSieve(max);
        System.out.println(max + " 以内的所有质数（线性筛）：" + primesLinear);

        // 测试4：获取前 N 个质数
        int count = 10;
        List<Integer> first10Primes = getFirstNPrimes(count);
        System.out.println("前 " + count + " 个质数：" + first10Primes);
    }

    /**
     * 方法1：判断单个数字是否为质数（基础版，时间复杂度 O(√n)）
     *
     * @param n 待判断的数字
     * @return true=质数，false=非质数
     */
    public static boolean isPrime(int n) {
        // 边界条件：小于2的数不是质数
        if (n <= 1) {
            return false;
        }
        // 2和3是质数
        if (n == 2 || n == 3) {
            return true;
        }
        // 能被2或3整除的数不是质数（提前过滤，减少循环次数）
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        // 核心逻辑：只需检查到√n，且步长为6（质数都在6x±1的位置）
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 方法2：埃拉托斯特尼筛法（批量生成 max 以内的所有质数，时间复杂度 O(n log log n)）
     *
     * @param max 范围上限
     * @return 范围内的所有质数列表
     */
    public static List<Integer> sieveOfEratosthenes(int max) {
        List<Integer> primes = new ArrayList<>();
        if (max < 2) {
            return primes;
        }

        // 初始化布尔数组：isPrime[i] 表示 i 是否为质数，初始默认 true（后续筛除合数）
        boolean[] isPrime = new boolean[max + 1];
        for (int i = 2; i <= max; i++) {
            isPrime[i] = true;
        }

        // 筛除过程：从2开始，将每个质数的倍数标记为合数
        for (int p = 2; p * p <= max; p++) {
            if (isPrime[p]) { // 如果 p 是质数
                // 从 p² 开始标记（更小的倍数已被之前的质数筛除）
                for (int i = p * p; i <= max; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        // 收集所有质数
        for (int i = 2; i <= max; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    /**
     * 方法3：线性筛（欧拉筛，优化版筛法，时间复杂度 O(n)）
     *
     * @param max 范围上限
     * @return 范围内的所有质数列表
     */
    public static List<Integer> linearSieve(int max) {
        List<Integer> primes = new ArrayList<>();
        if (max < 2) {
            return primes;
        }

        boolean[] isComposite = new boolean[max + 1]; // false=质数，true=合数

        for (int i = 2; i <= max; i++) {
            if (!isComposite[i]) { // i 是质数，加入列表
                primes.add(i);
            }
            // 筛除合数：用当前质数列表中的数，乘以 i 得到合数
            for (int p : primes) {
                if (p * i > max) { // 超出范围，终止
                    break;
                }
                isComposite[p * i] = true; // 标记为合数
                // 核心优化：保证每个合数只被其最小质因数筛除
                if (i % p == 0) {
                    break;
                }
            }
        }
        return primes;
    }

    /**
     * 方法4：获取前 N 个质数（基于单个质数判断）
     *
     * @param n 要获取的质数个数
     * @return 前 N 个质数列表
     */
    public static List<Integer> getFirstNPrimes(int n) {
        List<Integer> primes = new ArrayList<>();
        if (n <= 0) {
            return primes;
        }

        int count = 0; // 已找到的质数个数
        int num = 2;   // 从2开始检查
        while (count < n) {
            if (isPrime(num)) {
                primes.add(num);
                count++;
            }
            num++;
        }
        return primes;
    }
}