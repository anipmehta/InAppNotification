package com.anip.library.interfaces;

import android.app.Activity;

/**
 * Created by anip on 22/06/17.
 */

public interface Notification {
    Notification with(Activity activity);
    Notification icon(int resource);
    Notification title(String title);
    Notification nextActivity(Class next);
    Notification text(String text);
    Notification id(int id);
    Notification send();
    Notification schedule(boolean flag);
    Notification delay(long milli);
    Notification setTime(long time);
    android.app.Notification getNotification();
    Notification when(String date);
}
