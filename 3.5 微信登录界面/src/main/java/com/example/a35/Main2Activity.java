package com.example.a35;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        TextView tv_totalspace= (TextView) findViewById(R.id.textView4);
        TextView tv_freespace= (TextView) findViewById(R.id.textView5);
        TextView tv_occupyspace= (TextView) findViewById(R.id.textView7);
        ProgressBar pb= (ProgressBar) findViewById(R.id.progressBar);
        File file=this.getFilesDir();
        long totalspace=file.getTotalSpace();
        long freespace=file.getFreeSpace();

        tv_totalspace.setText(Formatter.formatFileSize(this,totalspace));
        tv_freespace.setText(Formatter.formatFileSize(this,freespace));
        tv_occupyspace.setText(Formatter.formatFileSize(this,totalspace-freespace));
        pb.setProgress(100-(int)((double)freespace/(double)totalspace*100));
    }
}
