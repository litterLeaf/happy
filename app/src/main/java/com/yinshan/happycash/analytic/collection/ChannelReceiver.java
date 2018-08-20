package com.yinshan.happycash.analytic.collection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;


public class ChannelReceiver extends BroadcastReceiver {
    private Intent intent1;

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                intent1 = new Intent(context, AlyService.class);
                context.startForegroundService(intent);
            } else {
                intent1 = new Intent(context, AlyService.class);
                context.startService(intent1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
