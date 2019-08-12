package com.hakansoftware.hakantodolist.base;

import android.content.Context;
import android.widget.Toast;

public class BaseUtils {
    public static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
