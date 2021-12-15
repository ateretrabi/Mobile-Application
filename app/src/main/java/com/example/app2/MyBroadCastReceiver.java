package com.example.app2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyBroadCastReceiver extends BroadcastReceiver {
    final String CHANNEL_ID= "simple_notification";
    final int NOTIFICATIONID=001;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name="personal_notification";
            String description="Include all personal notification";
            int importance= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,name,importance);
            NotificationManager notificationManager=(NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }



        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.bus)
                .setContentTitle("ישנה נסיעה חדשה")
                .setContentText("רצינו לעדכן אותך שישנן נסיעות חדשות עבורך")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        // builder.build();
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(NOTIFICATIONID,builder.build());
    }
}
