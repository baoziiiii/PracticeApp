package com.example.a101values;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

public class MainActivity extends Activity {
    //显示的图片数组
//    private Integer[] picture = {R.mipmap.img01, R.mipmap.img02, R.mipmap.img03,
//            R.mipmap.img04, R.mipmap.img05, R.mipmap.img06, R.mipmap.img07,
//            R.mipmap.img08, R.mipmap.img09, R.mipmap.img10, R.mipmap.img11,
//            R.mipmap.img12,R.mipmap.ic_launcher};
    TypedArray array_pictures;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView= (GridView) findViewById(R.id.gridView);  //获取布局文件中的GridView组件
        array_pictures=getResources().obtainTypedArray(R.array.pictures); ///res/values/arrays/图片资源数组
        gridView.setAdapter(new ImageAdapter(this));                //调用ImageAdapter
    }
    //创建ImageAdapter
    public class ImageAdapter extends BaseAdapter {
        private Context mContext;  //获取上下文
        public ImageAdapter(Context c){
            mContext=c;
        }
        @Override
        public int getCount() {
            return array_pictures.length();//图片数组的长度
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if(convertView==null){        //判断传过来的值是否为空，代表没有图片移出当前页面
                imageView=new ImageView(mContext);  //创建ImageView组件
                imageView.setLayoutParams(new GridView.LayoutParams(100, 90));   //为组件设置宽高
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);        //选择图片铺设方式
                Log.i("bwq","aaaa");
            }else{
                imageView= (ImageView) convertView; //重用从页面移出的旧View
            }
            imageView.setImageDrawable(array_pictures.getDrawable(position));
//            imageView.setImageResource(R.);    //设置新的View
            return imageView; //返回ImageView
        }
    }
}

