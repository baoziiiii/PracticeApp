package com.example.a142soundpool;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    String[] title = new String[]{"布谷鸟叫声", "风铃声", "门铃声", "电话声", "鸟叫声",
            "水流声", "公鸡叫声"};
    SoundPool soundPool=new SoundPool(10, AudioManager.STREAM_SYSTEM,0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ListView listView=findViewById(R.id.listView);
        List<Map<String,String>> mapList=new ArrayList<>();

        for (int i = 0; i < title.length ; i++) {
            Map<String,String> map=new HashMap<>();
            map.put("name",title[i]);
            mapList.add(map);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,mapList,R.layout.main,new String[]{"name"},new int[]{R.id.title});
          listView.setAdapter(simpleAdapter);

        HashMap<Integer,Integer> soundmap=new HashMap<>();
        soundmap.put(0,soundPool.load(this,R.raw.cuckoo,1));
        soundmap.put(1,soundPool.load(this,R.raw.chimes,1));
        soundmap.put(2,soundPool.load(this,R.raw.notify,1));
        soundmap.put(3,soundPool.load(this,R.raw.ringout,1));
        soundmap.put(4,soundPool.load(this,R.raw.bird,1));
        soundmap.put(5,soundPool.load(this,R.raw.water,1));
        soundmap.put(6,soundPool.load(this,R.raw.cock,1));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                soundPool.play(position,1,1,0,0,1);
            }
        });
    }
}
