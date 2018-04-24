package com.example.a113tablayout;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a113toolbar.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String tabname[]={"TAB1","TAB2","TAB3","TAB4","TAB5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<Fragment> fragmentList=new ArrayList<>();

        final TabLayout tabLayout=findViewById(R.id.tabLayout);
        final ViewPager viewPager=findViewById(R.id.content);

//        for (int i = 0; i < tabname.length; i++) {
//            try {
//                Fragment fragment=(Fragment)Class.forName("com.example.a113tablayout.Fragment_"+(i+1)).newInstance();
//                fragmentList.add(fragment);
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
        fragmentList.add(new Fragment_1());
        fragmentList.add(new Fragment_2());
        fragmentList.add(new Fragment_3());
        fragmentList.add(new Fragment_4());
        fragmentList.add(new Fragment_5());



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentAdapter fragmentAdapter=new FragmentAdapter(fragmentManager,fragmentList);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).select();
    }
}
