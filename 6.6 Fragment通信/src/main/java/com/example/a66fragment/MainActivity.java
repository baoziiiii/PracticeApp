package com.example.a66fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
public String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt1=findViewById(R.id.button);
        Button bt2=findViewById(R.id.button2);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.ll1,new FirstFragment());
                ft.commit();
                fm.executePendingTransactions(); //commit()是异步的，如有需要调用这个方法立刻执行commit
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.ll1,new SecondFragment(),"second");
                ft.commit();
                fm.executePendingTransactions();
                ((SecondFragment)fm.findFragmentByTag("second")).editText(data);
            }
        });
        bt1.performClick();
    }
}
