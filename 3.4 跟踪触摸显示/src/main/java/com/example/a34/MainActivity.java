package com.example.a34;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout frameLayout= (FrameLayout) findViewById(R.id.mylayout);
        final RabbitView rabbitview=new RabbitView(this);
        rabbitview.setOnTouchListener(new View.OnTouchListener() { //为rabbitView添加触摸监听器
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                rabbitview.bitmapX=event.getX();
                rabbitview.bitmapY=event.getY();
                rabbitview.invalidate();//抹去小兔子，请求重画，调用rabbitview的onDraw
                return true;
            }
        });
        frameLayout.addView(rabbitview); //添加rabbitview
    }
}

