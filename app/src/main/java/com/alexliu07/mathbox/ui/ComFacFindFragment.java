package com.alexliu07.mathbox.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alexliu07.mathbox.R;
import com.alexliu07.mathbox.function.CommonFactorFinding;
import com.alexliu07.mathbox.databinding.FragmentComfacfindBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ComFacFindFragment extends Fragment {

    private FragmentComfacfindBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //组件注册
        EditText inputFirstNum = getView().findViewById(R.id.comFacFind_input_first_num);
        EditText inputSecondNum = getView().findViewById(R.id.comFacFind_input_second_num);
        TextView resultText = getView().findViewById(R.id.comFacFind_result_text);
        WebView resultDisplay = getView().findViewById(R.id.comFacFind_result_display);
        FloatingActionButton submitBtn = getView().findViewById(R.id.comFacFind_submit);
        //初始化界面
        UIUtils.initFragment(resultText,resultDisplay);
        //确定键监听
        submitBtn.setOnClickListener(view1 -> {
            //获取数据
            String num1 = inputFirstNum.getText().toString();
            String num2 = inputSecondNum.getText().toString();
            //判断是否合规
            if(!(UIUtils.isCorrectInt(view,num1,getString(R.string.empty_text_alert),getString(R.string.int_digits_more_then_ten)) && UIUtils.isCorrectInt(view,num2,getString(R.string.empty_text_alert),getString(R.string.int_digits_more_then_ten)))){
                return;
            }
            //转换成数字
            int a = Integer.parseInt(num1);
            int b = Integer.parseInt(num2);
            //验证是否为0
            if(a == 0 || b == 0){
                UIUtils.showAlert(view,getString(R.string.input_cant_be_zero));
                return;
            }
            //获取公因数
            ArrayList<Integer> commonFactors = CommonFactorFinding.findcomfac(a,b);
            //准备显示
            StringBuilder text = new StringBuilder();
            boolean flag = true;
            text.append("\\\\(");
            for(int i=0;i < commonFactors.size();i++){
                if(commonFactors.get(i)<0 && flag){
                    text.append("\\\\)<br>\\\\(");
                    flag = false;
                }
                text.append(commonFactors.get(i)).append("\\\\,");
            }
            text.append("\\\\)");
            //显示结果
            UIUtils.showResult(text.toString(),resultText,resultDisplay);
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentComfacfindBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}