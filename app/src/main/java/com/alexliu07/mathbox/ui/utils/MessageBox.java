package com.alexliu07.mathbox.ui.utils;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class MessageBox {
    //显示提示
    public static void showAlert(View view, String text){
        Snackbar.make(view,text,Snackbar.LENGTH_SHORT).show();
    }
}
