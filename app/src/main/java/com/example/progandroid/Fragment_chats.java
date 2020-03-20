package com.example.progandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import static android.content.ContentValues.TAG;
import static android.content.Context.JOB_SCHEDULER_SERVICE;


public class Fragment_chats extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      //super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_fragment_chats, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button logout = view.findViewById(R.id.Button_Logout);
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SharedPreferences preference = getActivity().getSharedPreferences("masuk", Context.MODE_PRIVATE);
                //SharedPreferences preference = getSharedPreferences("masuk", MODE_PRIVATE);
                SharedPreferences.Editor editor = preference.edit();
                editor.putString("ingat", "false");
                editor.apply();
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }

        });


        Button btnScheduleJob = view.findViewById(R.id.schedulejob);
        Button btnCancelJob = view.findViewById(R.id.canceljob);

        btnScheduleJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ComponentName componentName = new ComponentName(getActivity(), MyJobService.class);
                JobInfo info = new JobInfo.Builder(123, componentName)
                        .setPersisted(true)
                        .setPeriodic(15 * 60 * 1000)
                        .build();

                JobScheduler scheduler = (JobScheduler) getActivity().getSystemService(JOB_SCHEDULER_SERVICE);
                int resultCode = scheduler.schedule(info);
                if (resultCode == JobScheduler.RESULT_SUCCESS) {
                    Log.i(TAG, "Job scheduled");
                } else {
                    Log.i(TAG, "Job scheduling failed");
                }

            }
        });

        btnCancelJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JobScheduler scheduler = (JobScheduler) getActivity().getSystemService(JOB_SCHEDULER_SERVICE);
                scheduler.cancel(123);
                Log.i(TAG, "Job canceled");
            }
        });
    }
}