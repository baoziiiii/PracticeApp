package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private ImageView img[]=new ImageView[12];
    private int[] imgPath=new int[]{R.mipmap.img01,R.mipmap.img02,R.mipmap.img03,R.mipmap.img04,
            R.mipmap.img05,R.mipmap.img06,R.mipmap.img07,R.mipmap.img08,R.mipmap.img09,
            R.mipmap.img10,R.mipmap.img11,R.mipmap.img12};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    //指定布局文件
        GridLayout grid=(GridLayout)findViewById(R.id.gridlayout);
        for (int i = 0; i <imgPath.length ; i++) {
            img[i]=new ImageView(this);
            img[i].setImageResource(imgPath[i]);
            img[i].setPadding(2,2,2,2);

            ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(100,50); //创建一个关于布局参数的对象
            img[i].setLayoutParams(params);  //设置布局宽度和高度
            grid.addView(img[i]);
        }
    }
}
