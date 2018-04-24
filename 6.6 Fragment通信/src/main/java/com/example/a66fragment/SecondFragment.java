package com.example.a66fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by MSI on 2018/2/2.
 */
public class SecondFragment extends Fragment {
    @Nullable
    String data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            data =savedInstanceState.getString("string");
        }
        View view=inflater.inflate(R.layout.second_fragment,null);
        return view;
    }
    public void editText(String str){
        TextView text=getActivity().findViewById(R.id.textView);
        text.setText(str);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("string", data);
    }
}
