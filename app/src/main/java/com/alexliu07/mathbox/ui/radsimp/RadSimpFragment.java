package com.alexliu07.mathbox.ui.radsimp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alexliu07.mathbox.R;
import com.alexliu07.mathbox.databinding.FragmentRadsimpBinding;

public class RadSimpFragment extends Fragment {
    private FragmentRadsimpBinding binding;
    //公式加载
    static void loadFormula(String txt,WebView webview){
        WebSettings websettings = webview.getSettings();
        websettings.setJavaScriptEnabled(true);
        websettings.setDefaultTextEncodingName("utf-8");
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(final WebView view, String url) {
                webview.loadUrl("javascript:changeData('" + txt + "')");
            }
        });
        webview.loadUrl("file:///android_asset/MathJax.html");
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // 顶部公式加载

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