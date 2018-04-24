package com.example.a71intent;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.security.Permission;

public class MainActivity extends Activity {

    public String phonenumber="13801381380";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton sms=findViewById(R.id.imageButton_sms);
        ImageButton phone=findViewById(R.id.imageButton_phone);
        Button home=findViewById(R.id.button);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(intent.ACTION_MAIN);
                intent.addCategory(intent.CATEGORY_HOME);
                startActivity(intent);
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("短信");
                builder.setMessage("是否给"+phonenumber+"发短信？");
                builder.setNegativeButton("否",null);
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (checkPermission(Manifest.permission.SEND_SMS)) {
                            Intent intent = new Intent();
                            intent.setAction(intent.ACTION_SENDTO);
                            intent.setData(Uri.parse("smsto:"+phonenumber));
                            intent.putExtra("sms_body","Hello World!");
                            startActivity(intent);
                        }
                    }
                });
               builder.show();
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("电话");
                builder.setMessage("是否给"+phonenumber+"打电话？");
                builder.setNegativeButton("否",null);
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (checkPermission(Manifest.permission.CALL_PHONE)) {
                            Intent intent = new Intent();
                            intent.setAction(intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:"+phonenumber));
                            startActivity(intent);
                        }
                    }
                });
                builder.show();
            }
        });
    }

    public Boolean checkPermission(final String permission){
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, 0);
            return false;
        }
        else{
            return true;
        }
    }

}
