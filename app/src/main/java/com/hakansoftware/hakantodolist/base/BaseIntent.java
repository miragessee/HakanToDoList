package com.hakansoftware.hakantodolist.base;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Map;

public class BaseIntent {

    public static void baseIntent(Context context, Class<?> t) {
        Intent intent = new Intent(context, t);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void baseIntent(Context context, Class<?> t, Map<String, String> dataMap) {
        Intent intent = new Intent(context, t);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("data", (Serializable) dataMap);
        context.startActivity(intent);
    }
}
