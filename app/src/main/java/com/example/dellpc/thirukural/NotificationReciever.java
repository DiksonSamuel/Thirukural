package com.example.dellpc.thirukural;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Dell Pc on 05-11-2016.
 */
public class NotificationReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notiman =(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent repeat = new Intent(context,Today.class);
        repeat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pre = PendingIntent.getActivity(context,100,repeat,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context).setContentIntent(pre)
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentTitle("CLICK HERE")
                .setContentText("GET TODAY'S THIRUKKURAL")
                .setAutoCancel(true);


        notiman.notify(100, builder.build());

    }
}

