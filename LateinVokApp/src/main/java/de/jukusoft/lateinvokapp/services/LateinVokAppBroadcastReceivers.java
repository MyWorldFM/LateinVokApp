package de.jukusoft.lateinvokapp.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.util.Log;

import de.jukusoft.lateinvokapp.LateinVokAppService;

public class LateinVokAppBroadcastReceivers extends BroadcastReceiver {
    public LateinVokAppBroadcastReceivers() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.v("WhatTheDroidService", " Boot komplett.");

            Intent wtdSServiceIntent = new Intent(context,
                    LateinVokAppService.class);
            PendingIntent wtdSServicePendingIntent = PendingIntent.
                    getService(context, 0, wtdSServiceIntent, 0);

            long interval = DateUtils.DAY_IN_MILLIS / 2;
            long firstStart = System.currentTimeMillis() + interval;

            AlarmManager am = (AlarmManager) context
                    .getSystemService(Context.ALARM_SERVICE);
            am.setInexactRepeating(AlarmManager.RTC, firstStart,
                    interval, wtdSServicePendingIntent);

            Log.v("WhatTheDroidService", "AlarmManager gesetzt");
        }
    }
}
