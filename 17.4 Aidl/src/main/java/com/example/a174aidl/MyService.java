package com.example.a174aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    public class MyBinder extends IService.Stub {
            public void externalaccess(){
                Log.i("bwq","EXTERNALACCESS");
            }
            public void localaccess(){
                Log.i("bwq","LOCALACCESS");
            }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
}
