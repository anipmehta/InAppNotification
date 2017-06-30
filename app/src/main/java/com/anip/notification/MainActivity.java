package com.anip.notification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.anip.library.InAppNotification;

/**
 * Created by anip on 21/06/17.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        InAppNotification inAppNotification = new InAppNotification();
        inAppNotification.with(this).nextActivity(MainActivity.class).icon(R.drawable.ic_launcher)
                .title("Scheduled Reminder").id(1).text("THis a scheduled notification").when("2017/06/30 19:54:00");
        inAppNotification.setDate("2017/06/30 19:54:00");
//        inAppNotification.send();


    }
}
