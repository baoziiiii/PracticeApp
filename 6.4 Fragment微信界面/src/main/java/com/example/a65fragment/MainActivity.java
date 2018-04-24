package com.example.a65fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager fm=getFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            Fragment f=null;
            imageview1.setImageResource(R.drawable.bottom1_false);
            imageview2.setImageResource(R.drawable.bottom2_false);
            imageview3.setImageResource(R.drawable.bottom3_false);
            imageview4.setImageResource(R.drawable.bottom4_false);
            switch (v.getId()){
                case R.id.imageView:
                    f=new WechatFragment();
                    imageview1.setImageResource(R.drawable.bottom1_true);
                    break;
                case R.id.imageView2:
                    f=new MessageFragment();
                    imageview2.setImageResource(R.drawable.bottom2_true);
                    break;
                case R.id.imageView3:
                    f=new FindFragment();
                    imageview3.setImageResource(R.drawable.bottom3_true);
                    break;
                case R.id.imageView4:f=new MeFragment();
                    imageview4.setImageResource(R.drawable.bottom4_true);
                    break;
                default:break;
            }
            ft.replace(R.id.content,f);
            ft.commit();
        }
    };
    private ImageView imageview1;
    private ImageView imageview2;
    private ImageView imageview3;
    private ImageView imageview4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageview1 = findViewById(R.id.imageView);
        imageview2 = findViewById(R.id.imageView2);
        imageview3 = findViewById(R.id.imageView3);
        imageview4 = findViewById(R.id.imageView4);
        imageview1.setOnClickListener(onClickListener);
        imageview2.setOnClickListener(onClickListener);
        imageview3.setOnClickListener(onClickListener);
        imageview4.setOnClickListener(onClickListener);
    }
}
