package com.example.swipeup_frontend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.swipeup_frontend.home.HomeActivity;
import com.example.swipeup_frontend.Inbox.Notification.NotificationActivity;
import com.example.swipeup_frontend.post.CreateActivity;
import com.example.swipeup_frontend.profile.ProfileActivity;
import com.example.swipeup_frontend.search.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class bottom_navigation_menu extends AppCompatActivity {
    BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_menu);

        bnv=(BottomNavigationView)findViewById(R.id.bottomNavigation);




        getSupportFragmentManager().beginTransaction().replace(R.id.FrameConatiner,new HomeActivity()).commit();

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                Fragment temp=null;

                switch (item.getItemId())
                {
                    case R.id.menu_home : temp=new HomeActivity();

                        bnv.setBackgroundResource(R.drawable.bottom_shit_background_black);
                        break;
                 /*   case R.id.menu_search : temp=new Discover();
                        break;*/
                    case R.id.menu_search : temp=new SearchActivity();
                        bnv.setBackgroundResource(R.drawable.bottom_shit_background_white);
                        break;
                    case R.id.menu_create : temp=new CreateActivity();
                        bnv.setBackgroundResource(R.drawable.bottom_shit_background_white);
                        break;
                    case R.id.menu_notification : temp=new NotificationActivity();
                        bnv.setBackgroundResource(R.drawable.bottom_shit_background_white);
                        break;
                    case R.id.menu_myprofile : temp=new ProfileActivity();
                        bnv.setBackgroundResource(R.drawable.bottom_shit_background_white);
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.FrameConatiner,temp).commit();

                return true;
            }
        });

    }
}
