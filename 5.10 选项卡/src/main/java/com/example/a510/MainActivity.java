package com.example.a510;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost tabhost=findViewById(android.R.id.tabhost);
        tabhost.setup();  //建立tabhost

        LayoutInflater inflater=LayoutInflater.from(this); //将tab1.xml和tab2.xml嵌套在tabhost的tabcontent下
        inflater.inflate(R.layout.tab1,tabhost.getTabContentView());
        inflater.inflate(R.layout.tab2,tabhost.getTabContentView());

        //
        tabhost.addTab(tabhost.newTabSpec("tab1").setIndicator("精选表情").setContent(R.id.linearlayout1));
        tabhost.addTab(tabhost.newTabSpec("tab2").setIndicator("投稿表情").setContent(R.id.linearlayout2));
    }
}
