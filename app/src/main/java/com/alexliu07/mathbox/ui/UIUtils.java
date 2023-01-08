package com.alexliu07.mathbox.ui;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class UIUtils {
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

    //显示提示
    public static void showAlert(View view, String text){
        Snackbar.make(view,text,Snackbar.LENGTH_SHORT).show();
    }
    //初始化页面
    public static void initFragment(TextView text, WebView display){
        //隐藏结果
        text.setVisibility(View.INVISIBLE);
        display.setVisibility(View.INVISIBLE);
        //初始化公式
        loadFormula("\\\\(loading\\\\)",display);
    }
    //验证整数是否合规
    public static boolean isCorrectInt(View view, String text, String empty, String longer){
        //判空
        if(text.isEmpty()){
            showAlert(view,empty);
            return false;
        }
        //判断位数
        if(text.charAt(0) == '-'){
            if(text.length() >= 11){
                showAlert(view,longer);
                return false;
            }
        }else{
            if(text.length() >= 10){
                showAlert(view,longer);
                return false;
            }
        }
        return true;
    }
    //获取小数位数
    public static int getDoubleBits(String n){
        int bits=0;
        for(int i=0;i<n.length();i++){
            if(n.charAt(i) == '.'){
                bits = n.length()-i-1;
                break;
            }
        }
        return bits;
    }
    //验证小数是否合规
    public static boolean isCorrectDouble(View view, String text, String empty, String intLonger,String doubleLonger){
        //判空
        if(text.isEmpty()){
            showAlert(view,empty);
            return false;
        }
        //获取小数位数
        int doublebits = getDoubleBits(text);
        //判断位数
        if(text.charAt(0) == '-'){
            //整数
            int intbits = text.length()-doublebits-2;
            if(intbits >= 10){
                showAlert(view,intLonger);
                return false;
            }
            if(doublebits >= 17){
                showAlert(view,doubleLonger);
                return false;
            }
        }else{
            int intbits = text.length()-doublebits-1;
            if(intbits >= 10){
                showAlert(view,intLonger);
                return false;
            }
            if(doublebits >= 17){
                showAlert(view,doubleLonger);
                return false;
            }
        }
        return true;
    }
    //显示结果
    public static void showResult(String text,TextView rText,WebView rDisplay){
        //加载结果
        loadFormula(text,rDisplay);
        //显示结果
        rText.setVisibility(View.VISIBLE);
        rDisplay.setVisibility(View.VISIBLE);
    }
}
