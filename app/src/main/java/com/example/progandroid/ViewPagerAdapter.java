package com.example.progandroid;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] childFragments;
    private boolean isRecieverReigtered = false;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        childFragments = new Fragment[]{
                new Fragment_chats(),
                new Fragment_status(),
                new Fragment_calls()
        };
    }

    public Fragment getItem(int position) {
        return childFragments[position];
    }


    public int getCount() {
        return childFragments.length;
    }


    public CharSequence getPageTitle(int position) {
        String tab;
        if (position == 0) {
            tab = "Chats";
        } else if (position == 1) {
            tab = "Status";
        } else {
            tab = "Calls";
        }
        return tab;
    }
}

