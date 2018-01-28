package com.example;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){ //为View设置监听器
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this).setTitle("系统提示") //弹出提示框
                        .setMessage("真的要开始吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() { //为提示框设置监听器
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("桌面台球", "进入游戏");//控制台输出
                            }
                        }).setNegativeButton("退出",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("桌面台球", "退出游戏");
                        finish(); //退出应用
                    }
                }).show();
            }});
    }
}
