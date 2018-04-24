package com.example.a62bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by MSI on 2018/2/1.
 */
public class MainActivity extends Activity {
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt=findViewById(R.id.btn);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,HeadActivity.class);
                startActivityForResult(intent,0x01);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==0x01&&resultCode==0x01){
            Bundle bundle=data.getExtras();
            int imgID=bundle.getInt("imgID");
            ImageView imageview=findViewById(R.id.imageView);
            imageview.setImageResource(imgID);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
