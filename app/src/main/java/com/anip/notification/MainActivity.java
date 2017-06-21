package com.anip.notification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by anip on 21/06/17.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        InAppNotification inAppNotification = new InAppNotification();
        inAppNotification.send(this, MainActivity.class, 1, R.drawable.ic_launcher);
    }
}
