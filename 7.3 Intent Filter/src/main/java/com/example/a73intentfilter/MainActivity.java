package com.example.a73intentfilter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FileOutputStream fos=null;
        ByteArrayOutputStream baos=null;
        try {
            fos=openFileOutput("hehua.jpg",MODE_PRIVATE);
            baos=new ByteArrayOutputStream();
            Bitmap bitmap=BitmapFactory.decodeResource(MainActivity.this.getResources(),R.drawable.hehua);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
            byte[] data=baos.toByteArray();
            fos.write(data);
        } catch (FileNotFoundException e) {
            try {
                fos.close();
                baos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (IOException e) {
            try {
                fos.close();
                baos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        Button bt= (Button) findViewById(R.id.btn);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(intent.ACTION_VIEW);
                intent.addCategory(intent.CATEGORY_DEFAULT);
                intent.putExtra("image",getFilesDir()+"/hehua.jpg");
                startActivity(intent);
            }
        });

    }
}
