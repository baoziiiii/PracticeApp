package com.example.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by MSI on 2018/2/4.
 */

//FruitAdapter.ViewHolder是当前类的内部类，存放的是子布局
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    //list存放 行布局参数的链表，由外部输入，由onBindViewHolder方法依次取出每一行的参数并加载到ViewHolder再返回
    //这样就得到每一行的布局
    private List<Fruit> fruitList;

    //内部类，存放每一行的布局
    static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View view) {
            //view代表每一行的布局容器，由onCreateViewHolder方法载入行布局容器的参数。
            super(view);
            //找到该子布局中的所有View组件
            this.fruitView=view;
            this.imageView = view.findViewById(R.id.fruit_image);
            this.textView = view.findViewById(R.id.fruit_name);
        }
    }

    public FruitAdapter(List<Fruit> fruitList) {
        this.fruitList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //parent代表父布局，即RecyclerView，viewType代表子布局的类型，即LinearLayout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent,false);
        //attachToRoot设为false，使fruit_item作为一个子类嵌入到RecyclerView，并且为false时，返回嵌入的view而不是返回root。
        final ViewHolder viewholder = new ViewHolder(view);
        //将子布局容器加载到ViewHolder中并返回，传递给onBindViewHolder

        //每行的子布局容器设置一个点击事件
        viewholder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=viewholder.getAdapterPosition();
                Fruit fruit=fruitList.get(position);
                Toast.makeText(v.getContext(),fruit.fruit_name,Toast.LENGTH_SHORT).show();
            }
        });
        return viewholder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //依次得到每一行的viewholder，position代表当前行位置。所以对应position，从list中取出相应行的参数，并加载到ViewHolder中
        Fruit fruit = fruitList.get(position);
        holder.imageView.setImageResource(fruit.fruit_image);
        holder.textView.setText(fruit.fruit_name);
    }

    @Override
    public int getItemCount() {
        return fruitList.size();
    }
}
