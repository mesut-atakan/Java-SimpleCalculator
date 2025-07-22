package com.aventra.calculator;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class UIHelper {
    public static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    public static  void log(String tag, String message){
        Log.d(tag, message);
    }
}
