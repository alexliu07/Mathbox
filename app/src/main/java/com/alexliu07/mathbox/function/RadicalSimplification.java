package com.alexliu07.mathbox.function;

public class RadicalSimplification {
    //判断是否整除
    public static boolean isEvenly(int a, int b, int times) {
        for (int i = 0; i < times; i++) {
            if (a % b != 0) {
                return false;
            }
            a /= b;
        }
        return true;
    }

    //判断质数
    public static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    //化简二次根式
    public static int simp(int n, int times) {
        int result = 1;
        //如果是质数直接返回1
        if (isPrime(Math.abs(n))) {
            return result;
        }
        //处理负数
        if (n < 0) {
            //在sqrt[times](n)~-2中寻找i^times能被n整除的数
            for (int i = -2; Math.pow(i, times) >= n; i--) {
                if (isEvenly(n, i, times)) {
                    //将当前n乘入结果
                    result *= i;
                    //将剩下的数表示出来，递归分解
                    n = (int) (n / Math.pow(i, times));
                    result *= simp(n, times);
                    //找到一个就停止
                    break;
                }
            }
        } else {
            //在2~sqrt[times](n)中寻找i^times能被n整除的数
            for (int i = 2; Math.pow(i, times) <= n; i++) {
                if (isEvenly(n, i, times)) {
                    //将当前n乘入结果
                    result *= i;
                    //将剩下的数表示出来，递归分解
                    n = (int) (n / Math.pow(i, times));
                    result *= simp(n, times);
                    //找到一个就停止
                    break;
                }
            }
        }
        return result;
    }
}
