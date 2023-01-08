package com.alexliu07.mathbox.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import com.alexliu07.mathbox.R;
import com.alexliu07.mathbox.databinding.FragmentFracapprBinding;
import com.alexliu07.mathbox.function.FractionApproximation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class FracApprFragment extends Fragment {

    private FragmentFracapprBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //注册组件
        EditText inputA = getView().findViewById(R.id.fracAppr_inputA);
        EditText inputB = getView().findViewById(R.id.fracAppr_inputB);
        TextView resultText = getView().findViewById(R.id.fracAppr_result_text);
        WebView resultDisplay = getView().findViewById(R.id.fracAppr_result_display);
        FloatingActionButton submitBtn = getView().findViewById(R.id.fracAppr_submit);
        //初始化显示
        UIUtils.initFragment(resultText,resultDisplay);
        //绑定事件
        submitBtn.setOnClickListener(view1 -> {
            String strA = inputA.getText().toString();
            String strB = inputB.getText().toString();
            int bitA=0,bitB=0;
            boolean isIntA = false,isIntB = false,isNegative = true,isEven = false;
            //判断是否合规
            if(!(UIUtils.isCorrectDouble(view,strA,getString(R.string.empty_text_alert),getString(R.string.int_digits_more_then_ten),getString(R.string.double_digits_more_than_17)) && UIUtils.isCorrectDouble(view,strB,getString(R.string.empty_text_alert),getString(R.string.int_digits_more_then_ten),getString(R.string.double_digits_more_than_17)))){
                return;
            }
            //转为小数
            double numA = Double.parseDouble(strA);
            double numB = Double.parseDouble(strB);
            //判断是否整除
            if(numA % numB == 0){
                int result = (int) (numA / numB);
                String text = "\\\\("+result+"\\\\)";
                UIUtils.showResult(text,resultText,resultDisplay);
                return;
            }
            //判断正数
            if((numA > 0 && numB > 0) || (numA < 0 && numB < 0)){
                isNegative = false;
            }
            numA = Math.abs(numA);
            numB = Math.abs(numB);
            //判断是否为整数
            if(numA % (int)numA == 0){
                isIntA = true;
            }
            if(numB % (int)numB == 0){
                isIntB = true;
            }
            //判断是否为小数及小数位数
            if (strA.contains(".") && (!isIntA)) {
                String fmtA = String.valueOf(numA);
                bitA = UIUtils.getDoubleBits(fmtA);
            }
            if (strB.contains(".") && (!isIntB)) {
                String fmtB = String.valueOf(numB);
                bitB = UIUtils.getDoubleBits(fmtB);
            }
            //判断是否为0
            if(numA == 0){
                UIUtils.showResult("\\\\(0\\\\)",resultText,resultDisplay);
                return;
            }
            if(numB == 0){
                UIUtils.showAlert(view,getString(R.string.input_b_cant_be_zero));
                return;
            }
            //化简运算
            int[] nums = FractionApproximation.simp(numA,numB,bitA,bitB);
            //显示结果
            String text;
            if(isNegative){
                text = "\\\\(-\\\\frac{"+nums[0]+"}{"+nums[1]+"}\\\\)";
            }else{
                text = "\\\\(\\\\frac{"+nums[0]+"}{"+nums[1]+"}\\\\)";
            }
            UIUtils.showResult(text,resultText,resultDisplay);
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFracapprBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}