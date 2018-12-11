package com.example.personal.sutdbookingapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Build;

public class NotificationHelper extends ContextWrapper {
    public NotificationHelper ( Context base ) {
        super ( base );
        createChannels();
    }

    private void createChannels () {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel bookingappChannel = new NotificationChannel ( BOOKINGAPP_CHANNEL_ID,BOOKINGAPP_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            bookingappChannel.enableLights ( true );
            bookingappChannel.enableVibration ( true );
            bookingappChannel.setLightColor ( Color.GREEN );
            bookingappChannel.setLockscreenVisibility ( Notification.VISIBILITY_PRIVATE );


            getManager().createNotificationChannel ( bookingappChannel );
        }
    }

    public NotificationManager getManager()
    {
        if (manager == null )
            manager = (NotificationManager) getSystemService ( Context.NOTIFICATION_SERVICE );
        return manager;
    }

    public Notification.Builder getBOOKINGAPPChannelNotification(String title, String body)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new Notification.Builder ( getApplicationContext (),BOOKINGAPP_CHANNEL_ID )
                    .setContentText(body).setContentTitle ( title )
                    .setSmallIcon ( R.mipmap.ic_launcher_round )
                    .setAutoCancel ( true );
        }
        return null;
    }

    private static final String BOOKINGAPP_CHANNEL_ID = "com.example.personal.sutdbookingapp.BOOKINGAPP";
    private static final String BOOKINGAPP_CHANNEL_NAME = "SUTD BOOKING APP";
    private NotificationManager manager;
}
