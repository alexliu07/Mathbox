package com.alexliu07.mathbox.function;

public class FractionApproximation {
    public static int gcd(int x,int y){
        return y==0?x:gcd(y,x%y);
    }
    public static int[] simp(double a,double b,int bitA,int bitB){
        int[] ints = new int[2];
        //乘一个大的位数
        if(bitA > bitB){
            ints[0] = (int) (a*Math.pow(10,bitA));
            ints[1] = (int) (b*Math.pow(10,bitA));
        }else{
            ints[0] = (int) (a*Math.pow(10,bitB));
            ints[1] = (int) (b*Math.pow(10,bitB));
        }
        //寻找最大公因数
        int maxcomfac = gcd(ints[0],ints[1]);
        //除以最大公因数
        ints[0] /= maxcomfac;
        ints[1] /= maxcomfac;
        //返回
        return ints;
    }
}
