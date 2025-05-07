package com.example.test.ctf02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes.dex */
public class GetAndChange extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, (Class<?>) NextContent.class);
        context.startActivity(intent1);
    }
}
