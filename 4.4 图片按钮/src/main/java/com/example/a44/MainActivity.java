package com.example.a44;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {  //将AppCompatActivity换成Activity，就没有最上面的app标题了

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //设置全屏显示
        ImageButton st= (ImageButton) findViewById(R.id.start);      //通过ID获取布局开始游戏图片按钮
        ImageButton sw = (ImageButton) findViewById(R.id.switch1);   //通过ID获取布局切换账号图片按钮
        //为开始游戏图片按钮添加单击事件监听器
        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"您单击了开始游戏按钮",Toast.LENGTH_SHORT).show();
            }
        });
        //为切换账号图片按钮添加单击事件监听器
        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"您单击了切换账号按钮",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
