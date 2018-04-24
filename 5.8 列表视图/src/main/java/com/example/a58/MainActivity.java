package com.example.a58;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listview= (ListView) findViewById(R.id.listview);

        String[] titles=getResources().getStringArray(R.array.titles);
        int[] pictures={R.mipmap.img01, R.mipmap.img02, R.mipmap.img03,
                R.mipmap.img04, R.mipmap.img05, R.mipmap.img06,
                R.mipmap.img07, R.mipmap.img08, R.mipmap.img09,
        };

        List<Map<String,Object>> list=new ArrayList<>();
        for (int i = 0; i < pictures.length; i++) {
            Map<String,Object> map=new HashMap<>();
            map.put("名字",titles[i]);
            map.put("图片",pictures[i]);
            list.add(map);
        }
        SimpleAdapter sa=new SimpleAdapter(this,list,R.layout.main,new String[]{"名字","图片"},
                new int[]{R.id.title,R.id.image});
        listview.setAdapter(sa);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,Object> map= (Map<String, Object>) parent.getItemAtPosition(position);
                String title= map.get("名字").toString();
                Toast.makeText(MainActivity.this,title,Toast.LENGTH_SHORT).show();
            }
        });
    }

}
