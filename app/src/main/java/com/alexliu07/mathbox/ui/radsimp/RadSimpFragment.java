package com.alexliu07.mathbox.ui.radsimp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alexliu07.mathbox.R;
import com.alexliu07.mathbox.databinding.FragmentRadsimpBinding;
import com.alexliu07.mathbox.function.RadicalSimplification;
import com.alexliu07.mathbox.ui.MathView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class RadSimpFragment extends Fragment {
    private FragmentRadsimpBinding binding;
    //显示提示
    static void showAlert(View view,String text){
        Snackbar.make(view,text,Snackbar.LENGTH_SHORT).show();
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //组件注册
        EditText inputX = (EditText) getView().findViewById(R.id.radSimp_inputX);
        EditText inputN = (EditText) getView().findViewById(R.id.radSimp_inputN);
        TextView resultText = (TextView) getView().findViewById(R.id.radSimp_result_text);
        WebView resultDisplay = (WebView) getView().findViewById(R.id.radSimp_result_display);
        FloatingActionButton radSimpSubmit = (FloatingActionButton) getView().findViewById(R.id.radSimp_submit);
        //隐藏结果
        resultText.setVisibility(View.INVISIBLE);
        resultDisplay.setVisibility(View.INVISIBLE);
        //先加载公式以便直接显示
        MathView.loadFormula("\\\\(a\\\\sqrt[n]{x}\\\\)",resultDisplay);
        //确定键监听
        radSimpSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                //是否继续的标志
                boolean isContinue = true;
                //存储数据
                String strX = inputX.getText().toString();
                String strN = inputN.getText().toString();
                //判断是否为空
                if(strX.isEmpty() || strN.isEmpty()){
                    showAlert(view,getString(R.string.empty_text_alert));
                    return;
                }
                //转换为数字
                int intX = Integer.parseInt(strX);
                int intN = Integer.parseInt(strN);
                //判断x的值是否大于等于0
                if(intX < 0){
                    showAlert(view,getString(R.string.radSimp_X_less_than_zero));
                    return;
                }
                //判断n的值是否大于等于2
                if(intN < 2){
                    showAlert(view,getString(R.string.radSimp_N_less_than_two));
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
                        text = "\\\\(\\\\sqrt[" + intN + "]{" + intX + "}\\\\)";
                    } else {
                        //其他化简情况
                        text = "\\\\("+ ans +"\\\\sqrt["+ intN +"]{"+(int) (intX / Math.pow(ans, intN))+"}\\\\)";
                    }
                }
                //准备结果
                MathView.loadFormula(text,resultDisplay);
                //显示结果
                resultText.setVisibility(View.VISIBLE);
                resultDisplay.setVisibility(View.VISIBLE);
                return;
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRadsimpBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}