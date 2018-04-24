package com.example.a65fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Point;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Point point=new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        int height=point.y;
        int width=point.x;
       //得到长和宽
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        if(width>height)
            ft.replace(android.R.id.content,new FragmentHorizontal());
        else
            ft.replace(android.R.id.content,new FragmentVertical());
        ft.commit();
    }
}
