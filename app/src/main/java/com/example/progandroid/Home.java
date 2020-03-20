package com.example.progandroid;

import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.Timer;
import java.util.TimerTask;

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
        private boolean isRecieverReigtered = false;
        private static final String TAG = "MainActivity";
        public static final long INTERVAL=3000;//variable to execute services every 10 second
        private Handler mHandler = new Handler();


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);
            ViewPager vpgr = findViewById(R.id.view_pager);
            vpgr.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
            TabLayout tl = findViewById(R.id.tab_layout);
            tl.setupWithViewPager(vpgr);
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
        };

        public void Notification(Context context, String messages){
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
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

//        public void scheduleJob(View view){
//            ComponentName componentName = new ComponentName(this, MyJobService.class);
//            JobInfo info = new JobInfo.Builder(123, componentName)
//                    .setPersisted(true)
//                    .setPeriodic(15 * 60 * 1000)
//                    .build();
//
//            JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//            int resultCode = scheduler.schedule(info);
//            if(resultCode == JobScheduler.RESULT_SUCCESS){
//                Log.d(TAG, "Job scheduled");
//            } else {
//                Log.d(TAG, "Job scheduling failed");
//            }
//
//            if (mTimer!=null)
//                mTimer.cancel();
//            else
//                mTimer = new Timer();
//
//            mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(),0,INTERVAL);
//
//            }
//
//
//            public void cancelJob(View view){
//            JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//            scheduler.cancel(123);
//            Log.d(TAG, "Job canceled");
//            mTimer.cancel();
//            }
//
//            class TimeDisplayTimerTask extends TimerTask{
//            @Override
//            public void run(){
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(), "3detik", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//            }

        }



