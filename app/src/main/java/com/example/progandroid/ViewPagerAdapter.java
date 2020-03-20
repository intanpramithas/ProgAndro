package com.example.progandroid;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] childFragments;
    private boolean isRecieverReigtered = false;

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

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(!isNetworkAvailable(context)){
                Notification(context, "Wifi Connection on");
            }
            else if(isNetworkAvailable(context)){
                Notification(context, "Wifi connection off");
            }
        }
    }

    public void Notification(Context context, String messages){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.)
                .setTicker(messages)
                .setContentTitle("Tugas Wifi Notification")
                .setContentText(messages)
                .setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }

    private boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }


protected void onResume(){
    super.onResume();
    if(!isRecieverReigtered){
        isRecieverReigtered = true;
        registerReceiver(receiver, new IntentFilter("android.net.wifi.STATE_CHANGE"));
        }
    }

protected void onPause(){
    super.onPause();
    if(isRecieverReigtered){

        }
    }

}