package com.example.progandroid;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

//public class Home extends AppCompatActivity {


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.nav_host_fragment, new Fragment_chats());
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//
//
//
//
//    }


    public class Home extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);
            ViewPager vpgr = findViewById(R.id.view_pager);
            vpgr.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
            TabLayout tl = findViewById(R.id.tab_layout);
            tl.setupWithViewPager(vpgr);
        }
    }
