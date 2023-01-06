package com.alexliu07.mathbox.function;

import java.util.ArrayList;
import java.util.Comparator;

public class CommonFactorFinding {
    public static ArrayList<Integer> findcomfac(int a,int b){
        //获取两数因数
        ArrayList<Integer> facA = FactorDecomposition.facDecomp(a);
        ArrayList<Integer> facB = FactorDecomposition.facDecomp(b);
        //公因数列表
        ArrayList<Integer> result = new ArrayList<>();
        //遍历
        for(int i=0;i < facA.size();i++){
            //寻找
            if(facB.contains(facA.get(i)) && (!result.contains(facA.get(i))) && facA.get(i) > 0){
                result.add(facA.get(i));
            }
        }
        //排序
        result.sort(Comparator.naturalOrder());
        //返回
        return result;
    }
}
