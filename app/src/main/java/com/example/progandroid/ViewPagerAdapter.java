package com.example.progandroid;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] childFragments;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        childFragments = new Fragment[] {
                new Fragment_chats(),
                new Fragment_status(),
                new Fragment_calls()
        };
    }

    @Override
    public Fragment getItem(int position) {
        return childFragments[position];
    }

    @Override
    public int getCount() {
        return childFragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String tab ;
        if(position==0){
            tab="Chats";
        }
        else if(position==1){
            tab="Status";
        }
        else{
            tab="Calls";
        }
        return tab;
    }
}
