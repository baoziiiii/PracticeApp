package com.example.a141mediaplayer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    Boolean isPause = false;
    private ImageButton play;
    private ImageButton stop;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        play = findViewById(R.id.btn_play);
        stop = findViewById(R.id.btn_stop);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CALL_PHONE})) {
                    if (mediaPlayer == null)
                        initialize();
                    else {
                        Buttonplay();
                    }
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null)
                    Buttonstop();
            }
        });

    }

    private void initialize() {
        file = new File(Environment.getExternalStorageDirectory() + "/music.mp3");
        if (file.exists()) {
            mediaPlayer = MediaPlayer.create(MainActivity.this, Uri.parse(file.getAbsolutePath()));
        } else {
            Toast.makeText(MainActivity.this, "找不到文件", Toast.LENGTH_SHORT);
            return;
        }
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Replay();
            }
        });
    }

    private void Buttonplay() {
        if (!mediaPlayer.isPlaying() && !isPause) { //停止状态
            Replay();
        } else {
            if (isPause) {   //暂停
                mediaPlayer.start();
                play.setImageResource(R.drawable.pause);
                isPause = false;
            } else {          //播放状态
                mediaPlayer.pause();
                play.setImageResource(R.drawable.play);
                isPause = true;
            }
        }
    }

    private void Buttonstop() {
        mediaPlayer.stop();
        play.setImageResource(R.drawable.play);
        isPause = false;
    }

    private void Replay() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(MainActivity.this, "请装载sd卡", Toast.LENGTH_LONG);
            Intent intent = new Intent(Settings.ACTION_MEMORY_CARD_SETTINGS);
            startActivity(intent);
        }
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(file.getAbsolutePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            play.setImageResource(R.drawable.pause);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Boolean checkPermission(final String[] permissions) {
        for (String permission:permissions) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, permissions, 0);
                return false;
            }
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 0:
                for (int grantResult:grantResults) {
                    if(grantResult!=PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "You denied the permission", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                play.performClick();
            default:
        }
    }

    @Override
    protected void onDestroy() {
        mediaPlayer.release();
        super.onDestroy();
    }
}
