package com.alexliu07.mathbox.function;


import java.util.ArrayList;

public class FactorDecomposition {
    public static void judgeAdd(ArrayList<Integer> nums,int n,int i){
        if(n % i == 0){
            nums.add(i);
            nums.add(n/i);
        }
    }
    public static ArrayList<Integer> facDecomp(int n) {
        ArrayList<Integer> nums = new ArrayList<>();
        int ntop=0;
        //正数
        for(int i=1;i*i<=Math.abs(n);i++){
            judgeAdd(nums,n,i);
        }
        //负数
        for(int i=-1;i*i<=Math.abs(n);i--){
            judgeAdd(nums,n,i);
        }
        return nums;
    }
}
