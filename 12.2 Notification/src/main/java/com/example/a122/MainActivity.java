package com.example.a122;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder=new Notification.Builder(this);
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.drawable.packet);
        builder.setContentTitle("奖励百万红包！");
        builder.setContentText("点击查看详情！");
        builder.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE|Notification.DEFAULT_LIGHTS);
        builder.setWhen(System.currentTimeMillis());
        Intent intent=new Intent(MainActivity.this,DetailActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity.this,0,intent,0);
        builder.setContentIntent(pendingIntent);
        notificationManager.notify(0x001,builder.build());

    }
}
