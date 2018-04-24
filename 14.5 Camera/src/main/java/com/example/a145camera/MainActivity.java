package com.example.a145camera;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Camera camera;
    private Boolean isPreview = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SurfaceView surfaceView = findViewById(R.id.surfaceView);
        final SurfaceHolder surfaceHolder = surfaceView.getHolder();
        ImageButton preview = findViewById(R.id.preview);
        ImageButton takepic = findViewById(R.id.takephoto);




        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkCameraHardware(MainActivity.this))
                    return;
                if (checkPermission(Manifest.permission.CAMERA)
                        &&checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (!isPreview) {
                        isPreview=true;
                        camera = getCameraInstance();

                        adjustPreviewParams(camera,surfaceView);
                        try {
                            camera.setPreviewDisplay(surfaceHolder);
                            camera.startPreview();
                            camera.autoFocus(null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        isPreview=false;
                        camera.stopPreview();
                        camera.release();
                    }
                }
            }
        });
        takepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.stopPreview();
                isPreview=false;
                if(camera!=null)
                    camera.takePicture(null,null,new CameraPreview(MainActivity.this));
            }
        });
    }

    /** Check if this device has a camera */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    public void adjustPreviewParams(Camera camera,SurfaceView surfaceView){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int display_height=metrics.heightPixels;
        int display_width=metrics.widthPixels;
        FrameLayout.LayoutParams params= (FrameLayout.LayoutParams) surfaceView.getLayoutParams();

        params.width=metrics.widthPixels;
        params.height=camera.getParameters().getPreviewSize().height*metrics.widthPixels/camera.getParameters().getPreviewSize().width;
        params.gravity= Gravity.CENTER_VERTICAL;
        surfaceView.setLayoutParams(params);

        camera.getParameters().setPreviewFpsRange(750,1000);
    }

    public Boolean checkPermission(final String permission) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, 0);
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void onPause() {
        if(camera!=null){
            camera.stopPreview();
            camera.release();
        }
        super.onPause();
    }
}
