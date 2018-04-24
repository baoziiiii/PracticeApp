package com.example.a173telephonyrecorder;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class RecordService extends Service {
    private final IBinder iBinder = new LocalBinder();
    private String path;

    public class LocalBinder extends Binder {
        public void play() {
            File file = new File(path);
                if (file.exists()) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(RecordService.this, Uri.fromFile(file));
                    Log.i("bwq", file.getAbsolutePath());
                        mediaPlayer.start();
                } else {
                    Toast.makeText(RecordService.this, "File NOT FOUND", Toast.LENGTH_SHORT).show();
                }
            }
    }

    public RecordService() {
    }

    @Override
    public void onCreate() {

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        telephonyManager.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);

    }

    private class MyPhoneStateListener extends PhoneStateListener {

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            MediaRecorder recorder=null;

            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE: // Recording is now started
                    if (recorder != null) {
                        recorder.stop();
                        recorder.reset();   // You can reuse the object by going back to setAudioSource() step
                        recorder.release(); // Now the object cannot be reused
                    }
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    Log.i("bwq","RING");
                    recorder=new MediaRecorder();
                    recorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);//MIC只能录自己的声音，VOICE_CALL录双方的声音
                    recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    path = getCacheDir()+incomingNumber+".3gp";
                    Log.i("bwq",path);
                    recorder.setOutputFile(path);
                    try {
                        recorder.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:

                    Log.i("bwq","OFFHOOK");
                    if(recorder!=null) {
                        recorder.start();   // Recording is now started
                    }
                    break;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

}
