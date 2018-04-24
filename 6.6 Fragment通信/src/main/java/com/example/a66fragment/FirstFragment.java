package com.example.a66fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by MSI on 2018/2/2.
 */
public class FirstFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.first_fragment,null);
        final EditText editText=view.findViewById(R.id.editText);
        Button commit=view.findViewById(R.id.button3);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               MainActivity activity= (MainActivity)getActivity();
                activity.data=editText.getText().toString();
                Toast.makeText(getActivity(),"提交成功",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
