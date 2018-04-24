package com.example.a172intentservice;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class DownLoad extends IntentService {

    public DownLoad() {
        super("DownLoad");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent1=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent1,0);
        Notification notification=new Notification.Builder(this)
                .setContentTitle("下载中")
                .setContentText("下载中")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1,notification);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
         int progress=0;
         while(progress<100){
             progress+=Math.random()*10;
             try {
                 Thread.currentThread().sleep((long) (Math.random()*500));
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             if(progress>=100)
                 progress=100;
         }
        Intent intent1=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent1,0);
        Notification notification=new Notification.Builder(this)
                .setContentTitle("下载完成")
                .setContentText("下载完成")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(3,notification);
    }
}
