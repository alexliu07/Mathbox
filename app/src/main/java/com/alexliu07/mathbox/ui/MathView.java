package com.alexliu07.mathbox.ui;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MathView {
    //公式加载
    public static void loadFormula(String text, WebView webview){
        WebSettings websettings = webview.getSettings();
        websettings.setJavaScriptEnabled(true);
        websettings.setDefaultTextEncodingName("utf-8");
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(final WebView view, String url) {
                webview.loadUrl("javascript:changeData('" + text + "')");
            }
        });
        webview.loadUrl("file:///android_asset/MathJax.html");
    }
}
