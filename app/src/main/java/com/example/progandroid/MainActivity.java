package com.example.progandroid;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progandroid.Home;
import com.example.progandroid.R;

public class MainActivity extends AppCompatActivity {
    private boolean isReciverReigtered = false;

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Email = "emailKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        System.out.println("available Login Email : "+ sharedpreferences.getString(Email,new String()));
        if(sharedpreferences.getString(Email,new String())!=""){
            Intent i = new Intent(MainActivity.this, Home.class);
            Bundle b = new Bundle();
            b.putString("Email", sharedpreferences.getString(Email,new String()));
            i.putExtras(b);
            startActivity(i);
        }
    }

    public void signup(View view){
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void login(View view){
        final EditText emailValidate = (EditText)findViewById(R.id.email);
        final TextView textView = (TextView)findViewById(R.id.text);
        String email = emailValidate.getText().toString().trim();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Email, email);
        editor.commit();


        // onClick of button perform this simplest code.
        if (email.matches(emailPattern))
        {
            Intent i = new Intent(MainActivity.this, Home.class);
            Bundle b = new Bundle();
            b.putString("email", email);
            i.putExtras(b);
            startActivity(i);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
        }
    }
}