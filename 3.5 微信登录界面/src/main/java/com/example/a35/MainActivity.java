package com.example.a35;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity{
    private EditText et_name;
    private EditText et_pass;
    private CheckBox cb_autologin;
    private Button bt_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SaveData sd=new SaveData(this);
        et_name= (EditText) findViewById(R.id.editText);
        et_pass= (EditText) findViewById(R.id.editText2);
        String autosave[]=sd.getLoginInfo();
        if(Boolean.parseBoolean(autosave[0]))
            et_name.setText(autosave[1]);
        cb_autologin= (CheckBox) findViewById(R.id.checkBox);
        bt_login= (Button) findViewById(R.id.button);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData sd= new SaveData(MainActivity.this);
                String username=et_name.getText().toString();
                String password=et_pass.getText().toString();
                if(TextUtils.isEmpty(username)||
                        TextUtils.isEmpty(password))
                    Toast.makeText(MainActivity.this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
                else {
                    if(sd.saveLoginInfo(username,password,cb_autologin.isChecked()?true:false)) {
                        Toast.makeText(MainActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, Main2Activity.class));
                    }
                    else
                        Toast.makeText(MainActivity.this,"保存失败！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
