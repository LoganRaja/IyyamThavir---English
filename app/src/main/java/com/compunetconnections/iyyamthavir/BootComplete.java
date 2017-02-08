package com.compunetconnections.iyyamthavir;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootComplete extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

            Intent serviceIntent = new Intent(context, AutoStartUp.class);
            context.startService(serviceIntent);

    }

}