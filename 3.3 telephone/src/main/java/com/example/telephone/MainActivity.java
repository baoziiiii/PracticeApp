package com.example.telephone;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView phone_text;
    private Button button_call;
    private Button button_rst;
    private int MY_PERMISSION_REQUEST_CALL_PHONE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phone_text = (TextView) findViewById(R.id.editText);
        button_call = (Button) findViewById(R.id.button);
        button_rst = (Button) findViewById(R.id.button2);
        button_call.setOnClickListener(this); //button_call为监听事件，this为Main_Activity，this.onClick覆写监听处理
        button_rst.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //View v参数传进来的是button_call控件
        switch (v.getId()) {
            //switch不能直接接收对象
            case R.id.button:
                String phonenumber = phone_text.getText().toString();
                if (TextUtils.isEmpty(phonenumber))
                    //向界面传输一个小提示
                    Toast.makeText(this, "电话号码不能为空", Toast.LENGTH_SHORT).show();
                else {
                    if (checkPermission())
                        callPhone(phonenumber);
                }
                break;
            case R.id.button2:
                phone_text.setText("");
                break;
        }
    }

    private void callPhone(String phone_string) {
        Intent intent = new Intent();
        //设置意图，ACTION_CALL是Intent类下    的常量
        intent.setAction(Intent.ACTION_CALL);
        //URL 统一资源定位符 http:// ftp:// https://
        //URI 统一资源标识符  可以自定义协议，URI是URL的父类
        //tel:是该api定义的电话协议
        intent.setData(Uri.parse("tel:" + phone_string));
        //开启打电话的新界面
        startActivity(intent);

    }

    private Boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //没有获得权限，申请权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CALL_PHONE)) {
                //如果app之前请求权限被用户拒绝，返回true
                //如果用户之前故选了don't ask again，那么返回false
                //如果设备策略禁止应用拥有这条权限，也返回false.
                //弹窗需要解释为何需要该权限
                Toast.makeText(MainActivity.this, "请授权！", Toast.LENGTH_LONG).show();
                //帮跳转到应用的设置界面，让用户手动授权
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            } else {
                //不需要解释为何需要该权限，直接请求权限
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSION_REQUEST_CALL_PHONE);
            }
            return false;
        } else {
            return true;
        }
    }
}
