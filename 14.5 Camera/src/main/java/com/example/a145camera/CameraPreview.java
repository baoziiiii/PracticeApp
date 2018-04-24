package com.example.a145camera;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by MSI on 2018/2/5.
 */

public class CameraPreview implements Camera.PictureCallback {
    Context context;

    public CameraPreview(Context context) {
        this.context = context;
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        SoundPool soundPool=new SoundPool(1, AudioManager.STREAM_SYSTEM,0);
        int notification=soundPool.load(context,R.raw.chimes,1);
        soundPool.play(notification,1,1,1,0,1);

        File dir=new File(Environment.getExternalStorageDirectory()+"/DCIM/Camera/");
        if(!dir.exists())
            dir.mkdirs();
        String filename=System.currentTimeMillis()+".jpg";
        Bitmap bitmap=BitmapFactory.decodeByteArray(data,0,data.length);
        File file=new File(dir,filename);

        try {
            FileOutputStream fos= new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),file.getAbsolutePath(),filename,null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE),file.getAbsolutePath());
        Toast.makeText(context,"照片保存至"+file,Toast.LENGTH_LONG).show();
    }


}
