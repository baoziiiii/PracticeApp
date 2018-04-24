package com.example.msi.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckBox cb1=findViewById(R.id.checkBox);
        CheckBox cb2=findViewById(R.id.checkBox2);
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Toast.makeText(MainActivity.this,"选中"+buttonView.getText(),Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"取消"+buttonView.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Toast.makeText(MainActivity.this,"选中"+buttonView.getText(),Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"取消"+buttonView.getText(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
