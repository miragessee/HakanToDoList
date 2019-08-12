package com.hakansoftware.hakantodolist.base;

import android.content.Context;
import android.content.Intent;

public class BaseIntent {

    public static void baseIntent(Context context, Class<?> t) {
        Intent intent = new Intent(context, t);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
