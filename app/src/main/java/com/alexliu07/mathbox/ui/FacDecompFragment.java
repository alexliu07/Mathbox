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
import com.alexliu07.mathbox.databinding.FragmentFacdecompBinding;
import com.alexliu07.mathbox.function.FactorDecomposition;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FacDecompFragment extends Fragment {

    private FragmentFacdecompBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //组件注册
        EditText inputNumber = getView().findViewById(R.id.facDecomp_input_num);
        TextView resultText = getView().findViewById(R.id.facDecomp_result_text);
        WebView resultDisplay = getView().findViewById(R.id.facDecomp_result_display);
        FloatingActionButton submitBtn = getView().findViewById(R.id.facDecomp_submit);
        //初始化页面
        UIUtils.initFragment(resultText,resultDisplay);
        //确定键监听
        submitBtn.setOnClickListener(view1 -> {
            //获取数据
            String num = inputNumber.getText().toString();
            //验证是否合规
            if(!UIUtils.isCorrectInt(view,num,getString(R.string.empty_text_alert),getString(R.string.int_digits_more_then_ten))){
                return;
            }
            //验证是否为0
            int n = Integer.parseInt(num);
            if(n == 0){
                UIUtils.showAlert(view,getString(R.string.input_cant_be_zero));
                return;
            }
            //继续计算
            ArrayList<Integer> nums = FactorDecomposition.facDecomp(n);
            StringBuilder text = new StringBuilder();
            for(int i=0;i<nums.size();i+=2){
                text.append("\\\\(").append(nums.get(i)).append("*").append(nums.get(i + 1)).append("\\\\)<br>");
            }
            //显示结果
            UIUtils.showResult(text.toString(),resultText,resultDisplay);
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFacdecompBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}