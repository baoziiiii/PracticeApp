package com.example.a93;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final HatView hatView=new HatView(this);
        hatView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hatView.BitmapX=event.getX();
                hatView.BitmapY=event.getY();
                hatView.invalidate();
                return true;
            }
        });
        RelativeLayout relativeLayout=findViewById(R.id.relativeLayout);
        relativeLayout.addView(hatView);
    }
}


