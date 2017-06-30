package com.anip.library;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.anip.library.interfaces.Notification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by anip on 21/06/17.
 */

public class InAppNotification implements Notification {
    private Context context;
    private Class nextActivity;
    private int id;
    private PendingIntent pendingIntent;
    private boolean scheduled_flag=false;
    private NotificationCompat.Builder builder;
    @Override
    public Notification send() {
        Intent intent = new Intent(context, nextActivity);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(nextActivity);
        stackBuilder.addNextIntent(intent);
        pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());
        return this;
    }

    @Override
    public Notification schedule(boolean flag) {
        scheduled_flag = flag;
        return this;
    }

    @Override
    public Notification delay(long milli) {
        long futureInMillis = SystemClock.elapsedRealtime() + milli;
        Intent notificationIntent = new Intent(context, NotificationReceiver.class);
        notificationIntent.putExtra("id", id);
        notificationIntent.putExtra("notification", getNotification());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
        return this;
    }

    @Override
    public Notification setTime(long time) {
        Log.i("hell", String.valueOf(time));
        Intent notificationIntent = new Intent(context, NotificationReceiver.class);
        notificationIntent.putExtra("id", id);
        notificationIntent.putExtra("notification", getNotification());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
        return this;
    }
    public Notification setDate(String dateString){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long millis = date.getTime();
        setTime(millis);
        return this;
    }

    @Override
    public android.app.Notification getNotification() {
        return builder.build();
    }

    @Override
    public Notification when(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long millis = date.getTime();
        builder.setWhen(millis);
        return this;
    }

    @Override
    public Notification with(Activity activity) {
        context = activity;
        builder = new NotificationCompat.Builder(context);
        return this;
    }

    @Override
    public Notification icon(int resource) {
        builder.setSmallIcon(resource);
        return this;
    }

    @Override
    public Notification title(String title) {
        builder.setContentTitle(title);
        return this;
    }

    @Override
    public Notification nextActivity(Class next) {
        nextActivity = next;
        return this;
    }

    @Override
    public Notification text(String text) {
        builder.setContentText(text);
        return this;
    }

    @Override
    public Notification id(int id) {
        this.id = id;
        return this;
    }
}