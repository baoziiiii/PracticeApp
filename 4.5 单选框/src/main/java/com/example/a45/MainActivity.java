package com.example.a45;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt_commit= (Button) findViewById(R.id.button);
        rg= (RadioGroup) findViewById(R.id.radioGroup);
        //切换选项时获取：
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb= (RadioButton) findViewById(checkedId);
                Toast.makeText(MainActivity.this,rb.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        //提交后获取：
        bt_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i <rg.getChildCount() ; i++) {
                    RadioButton rb= (RadioButton) rg.getChildAt(i);
                    if(rb.isChecked()) {
                        Toast.makeText(MainActivity.this,"提交成功！"+rb.getText(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
