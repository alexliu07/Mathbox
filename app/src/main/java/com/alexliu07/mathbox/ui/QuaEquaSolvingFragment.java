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
import com.alexliu07.mathbox.databinding.FragmentQuaequasolvingBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class QuaEquaSolvingFragment extends Fragment {
    private FragmentQuaequasolvingBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //注册组件
        EditText inputA = getView().findViewById(R.id.quaEqua_inputA);
        EditText inputB = getView().findViewById(R.id.quaEqua_inputB);
        EditText inputC = getView().findViewById(R.id.quaEqua_inputC);
        TextView resultText = getView().findViewById(R.id.quaEqua_result_text);
        WebView resultDisplay = getView().findViewById(R.id.quaEqua_result_display);
        FloatingActionButton submitBtn = getView().findViewById(R.id.quaEqua_submit);
        //初始化显示
        UIUtils.initFragment(resultText,resultDisplay);
        //绑定事件
        submitBtn.setOnClickListener(view1 -> {

        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentQuaequasolvingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}