package com.example.a52;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView volumn_text;
    SeekBar skb;
    RatingBar rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volumn_text= (TextView) findViewById(R.id.volumn_text);
        skb= (SeekBar) findViewById(R.id.seekBar);
        rb= (RatingBar) findViewById(R.id.ratingBar);

        skb.setProgress(0);
        volumn_text.setText(skb.getProgress()+"");

        skb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {  //实时跟踪用户拖动情况
               volumn_text.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {  //用户放手后再收集数据
                Toast.makeText(MainActivity.this,"设置成功！音量："+seekBar.getProgress(),Toast.LENGTH_SHORT);
            }
        });

        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(MainActivity.this,rating+"颗星",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
