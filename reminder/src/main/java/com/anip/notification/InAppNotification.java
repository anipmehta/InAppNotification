package com.anip.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

/**
 * Created by anip on 21/06/17.
 */

public class InAppNotification {
    public static void send(Context context, Class nextActivity, int id, int icon){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(icon)
                .setContentTitle("Library Notification")
                .setContentText("Hello World");
        Intent intent = new Intent(context, nextActivity);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(nextActivity);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());
    }
}
