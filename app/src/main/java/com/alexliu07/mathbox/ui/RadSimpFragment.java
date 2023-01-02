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
import com.alexliu07.mathbox.databinding.FragmentRadsimpBinding;
import com.alexliu07.mathbox.function.RadicalSimplification;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RadSimpFragment extends Fragment {
    private FragmentRadsimpBinding binding;

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //组件注册
        EditText inputX = getView().findViewById(R.id.radSimp_inputX);
        EditText inputN = getView().findViewById(R.id.radSimp_inputN);
        TextView resultText = getView().findViewById(R.id.radSimp_result_text);
        WebView resultDisplay = getView().findViewById(R.id.facDecomp_result_display);
        FloatingActionButton submitBtn = getView().findViewById(R.id.radSimp_submit);
        //初始化页面
        UIUtils.initFragment(resultText,resultDisplay);
        //确定键监听
        submitBtn.setOnClickListener(view1 -> {
            //存储数据
            String strX = inputX.getText().toString();
            String strN = inputN.getText().toString();
            //验证是否合规
            if(!(UIUtils.isCorrect(view,strX,getString(R.string.empty_text_alert),getString(R.string.int_digits_more_then_ten)) && UIUtils.isCorrect(view,strN,getString(R.string.empty_text_alert),getString(R.string.int_digits_more_then_ten)))){
                return;
            }
            //转换为数字
            int intX = Integer.parseInt(strX);
            int intN = Integer.parseInt(strN);
            //偶数不允许被开方数小于0
            if (intX < 0 && (intN % 2 == 0)) {
                UIUtils.showAlert(view,getString(R.string.radSimp_X_less_than_0_when_N_even));
                return;
            }
            //判断n的值是否大于等于2
            if(intN < 2){
                UIUtils.showAlert(view,getString(R.string.radSimp_N_less_than_two));
                return;
            }
            //开始计算
            String text;
            //如果是0直接显示
            if(intX == 0){
                text = "\\\\(0\\\\)";
            }else {
                int ans = RadicalSimplification.simp(intX, intN);
                if (Math.pow(ans, intN) == intX) {
                    //刚好开完
                    text = "\\\\(" + ans + "\\\\)";
                } else if (ans == 1) {
                    //无法化简
                    //如果是二次根式则隐藏次数
                    if(intN == 2){
                        text = "\\\\(\\\\sqrt{" + intX + "}\\\\)";
                    }else{
                        text = "\\\\(\\\\sqrt[" + intN + "]{" + intX + "}\\\\)";
                    }
                } else {
                    //其他化简情况
                    //如果是二次根式则隐藏次数
                    if(intN == 2){
                        text = "\\\\("+ ans +"\\\\sqrt{"+(int) (intX / Math.pow(ans, intN))+"}\\\\)";
                    }else{
                        text = "\\\\("+ ans +"\\\\sqrt["+ intN +"]{"+(int) (intX / Math.pow(ans, intN))+"}\\\\)";
                    }
                }
            }
            //显示结果
            UIUtils.showResult(text,resultText,resultDisplay);
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRadsimpBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}