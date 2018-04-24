package com.example.a143videoview;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        VideoView videoView=findViewById(R.id.video);
        File file=new File(Environment.getExternalStorageDirectory()+"/video.mp4");
        MediaController mediaController=new MediaController(this);
        if(file.exists()){
            videoView.setVideoPath(file.getAbsolutePath());
            videoView.setMediaController(mediaController);
            videoView.requestFocus();
            videoView.start();
        }else{
            Toast.makeText(this,"找不到视频",Toast.LENGTH_LONG).show();
        }


    }
}
