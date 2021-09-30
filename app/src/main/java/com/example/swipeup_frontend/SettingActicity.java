package com.example.swipeup_frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SettingActicity extends AppCompatActivity {
   private TextView Manage_Account,Safety,Notifications,Help;
   public LinearLayout log_out;

    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing email.
//    public static final String EMAIL_KEY = "email_key";
    SharedPreferences sharedpreferences;
//   SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_acticity);

        Manage_Account=findViewById(R.id.ma);
        Notifications=findViewById(R.id.Notifications);
        Safety=findViewById(R.id.Safety);
        Help=findViewById(R.id.help);
        log_out = (LinearLayout) findViewById(R.id.logoutt);

//        sharedPrefManager = new SharedPrefManager(getApplicationContext());

        sharedpreferences = this.getApplicationContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String email = sharedpreferences.getString("username", null);

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sharedPrefManager.logout();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean("logged", false);
                editor.putString("id", null);
                editor.putString("username", null);
                editor.putString("name", null);
                editor.putString("img_url", null);
                editor.apply();
                Intent intent = new Intent(SettingActicity.this, Sign_in_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        Manage_Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), ManageAccount.class);
                startActivity(intent);
            }
        });
        Safety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Safety.class);
                startActivity(intent);
            }
        });
        Notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), SettingsNotification.class);
                startActivity(intent);
            }
        });

        Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpCenter.class);
                startActivity(intent);
            }
        });
    }
}