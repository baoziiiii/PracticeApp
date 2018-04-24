package com.example.a62bundle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by MSI on 2018/2/1.
 */



public class HeadActivity extends Activity {
    public int img[]=new int[]{R.drawable.touxiang,R.drawable.touxiang1,R.drawable.touxiang2,R.drawable.touxiang3,
            R.drawable.touxiang4,R.drawable.touxiang5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);
        final BaseAdapter baseadapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return img.length;
            }

            @Override
            public Object getItem(int position) {  //不会自动调用，所以你想不想覆写决定于你想不想用这个方法
                return img[position];
            }

            @Override
            public long getItemId(int position) {//不会自动调用，所以你想不想覆写决定于你想不想用这个方法
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageview;
                if(convertView == null){
                    imageview=new ImageView(HeadActivity.this);
                    imageview.setMaxWidth(158);
                    imageview.setMaxHeight(150);
                    imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
                else{
                    imageview=(ImageView)convertView;
                }
                imageview.setImageResource(img[position]);
                return imageview;
            }
        };
        GridView gridview=findViewById(R.id.gridView);
        gridview.setAdapter(baseadapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog alertDialog=new AlertDialog.Builder(HeadActivity.this).create();
                alertDialog.setIcon((Integer) baseadapter.getItem(position));
                alertDialog.setTitle("选择头像");
                alertDialog.setMessage("确定选择这张图片为头像吗？");
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle bundle=new Bundle();
                        Intent intent=getIntent();
                        bundle.putInt("imgID",img[position]);
                        intent.putExtras(bundle);
                        setResult(0x01,intent);
                        finish();
                    }
                });
                alertDialog.show();
            }
        });
    }
}
