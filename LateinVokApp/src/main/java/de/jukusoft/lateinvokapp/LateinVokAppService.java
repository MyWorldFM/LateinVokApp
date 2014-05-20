package de.jukusoft.lateinvokapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import java.io.FileDescriptor;

public class LateinVokAppService extends Service {
    public LateinVokAppService() {
    }

    @Override
    public void onCreate () {
        Log.v("WhatTheDroidService", System.currentTimeMillis()
                + ": WhatTheDroidService erstellt.");
    }

    public void onDestroy () {
        Log.v("WhatTheDroidService", System.currentTimeMillis()
                + ": WhatTheDroidService zerstoert.");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO do something useful

        Log.v("WhatTheDroidService", System.currentTimeMillis()
                + ": WhatTheDroidService gestartet.");

        // Unsere auszufuehrende Methode.
        updateData();

        // Nachdem unsere Methode abgearbeitet wurde, soll sich der Service
        // selbst stoppen.
        //stopSelf();

        // Um den Service laufen zu lassen, bis er explizit gestoppt wird,
        // geben wir "START_STICKY" zurueck.
        //return START_STICKY;

        return Service.START_STICKY;
    }

    public void updateData () {
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                //Auf Updates prüfen

                //Datenbank aktualisieren

                //Feedback senden

                //Punktzahl übertragen

                LateinVokAppService.this.stopSelf();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceBinder();
    }

    class ServiceBinder extends Binder {

        public ServiceBinder() {
        }

        public LateinVokAppService getInstance () {
            return LateinVokAppService.this;
        }
    }
}
