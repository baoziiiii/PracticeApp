package com.example.a73intentfilter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ByteArrayOutputStream baos=null;
        FileInputStream fis=null;
        byte[] buf=new byte[10];
        byte[] data=null;
        try {
            baos=new ByteArrayOutputStream();
            fis=new FileInputStream(new File(getIntent().getExtras().getString("image")));
            int i;
            while((i=fis.read(buf))!=-1) {
                baos.write(buf, 0, i);
            }
            data=baos.toByteArray();
            fis.close();
            baos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap= BitmapFactory.decodeByteArray(data,0,data.length);
        ImageView imageView= (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }
}
