package com.jdk8.stream.iterativerecursive;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * 各种迭代、递归算法
 */
public class IterativeRecursiveDemo {

    public static void main(String[] args) {

        long r1 = factorialIterative(10);
        long r2 = factorialRecursive(10);
        long r3 = factorialTailRecursive(10);
        System.out.println(r1+","+r2+","+r3);
    }

    /**
     * 迭代式阶乘
     * @param n
     * @return
     */
    public static int factorialIterative(int n){
        int r = 1;
        for (int i = 1; i <= n ; i++) {
            r *= i;
        }
        return r;
    }

    /**
     * 递归式阶乘
     * @param n
     * @return
     */
    public static long factorialRecursive(long n){
        return n==1?1:n*factorialRecursive(n-1);
    }

    /**
     * 基于Stream的阶乘
     * @param n
     * @return
     */
    public static long factorialStream(long n){
        return LongStream.rangeClosed(1,n)
                .reduce(1,(long a,long b)->a*b);
    }

    /**
     * 使用【尾-递】的阶乘：基本的思想是你可以编写阶乘的一个迭代定义，不过迭代调用发生在函数的最后(所以我们说调用发生在尾部)。这种新型的迭代调用经过优化后执行的速度快很多。
     * @param n
     * @return
     */
    public static long factorialTailRecursive(long n){
        return factorialHelper(1,n);
    }

    public static long factorialHelper(long acc,long n){
        return n==1?acc:factorialHelper(acc*n,n-1);
    }
}
