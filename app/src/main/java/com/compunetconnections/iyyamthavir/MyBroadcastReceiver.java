package com.compunetconnections.iyyamthavir;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static com.google.android.gms.cast.CastRemoteDisplayLocalService.startService;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context aContext, Intent aIntent) {

        // This is where you start your service
        //startService(new Intent(aContext, StartActivity.class);
        Intent serviceIntent = new Intent(aContext, StartActivity.class);
        aContext.startService(serviceIntent);
    }
}