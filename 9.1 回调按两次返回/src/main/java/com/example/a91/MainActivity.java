package com.example.a91;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private long back_clock=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
           if(System.currentTimeMillis()-back_clock>2000){
               back_clock=System.currentTimeMillis();
               Toast.makeText(MainActivity.this,"连续按两次返回键退出程序",Toast.LENGTH_SHORT).show();
               return true; //拦截返回键
           }
            else{
               finish();
           }
        }
        return super.onKeyDown(keyCode, event);
    }
}
