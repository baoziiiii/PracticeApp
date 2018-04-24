package com.example.a93;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by MSI on 2018/2/3.
 */
public class HatView extends View {
    public float BitmapX;
    public float BitmapY;
    Bitmap bitmap=BitmapFactory.decodeResource(this.getResources(),R.drawable.hat);

    public HatView(Context context) {
        super(context);
        BitmapX=65+bitmap.getWidth()/2;
        BitmapY=bitmap.getHeight()/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap,BitmapX-bitmap.getWidth()/2,BitmapY-bitmap.getHeight()/2,new Paint());
    }
}
