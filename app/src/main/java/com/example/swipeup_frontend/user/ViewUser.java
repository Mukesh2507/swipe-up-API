package com.example.swipeup_frontend.user;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.swipeup_frontend.R;
import com.example.swipeup_frontend.addfriends.addfriends_model;
import com.example.swipeup_frontend.api.RetrofitClient;
import com.example.swipeup_frontend.load_website;
import com.example.swipeup_frontend.profile.follower;
import com.example.swipeup_frontend.profile.following;
import com.example.swipeup_frontend.videos.ViewUserVideo;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewUser extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView name, Username;
   ViewUserVideo viewUserVideo;
   ViewUserFavourite viewUserFavourite;
   TextView follow_friend;
   LinearLayout webviewer;
   public CardView followw;
   Boolean state;

   TextView followerCount;
   TextView followingCount;
   TextView bio;
   TextView website;

   SharedPreferences sharedPreferences;
   public static final String SHARED_PREFS = "shared_prefs";

    private int[] imageResId = {
            R.drawable.video_icon,
            R.drawable.heart_icon
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
        tabLayout = findViewById(R.id.viewuser_tab);
        viewPager = findViewById(R.id.viewuser_slider);

        name = findViewById(R.id.t50);
        Username = findViewById(R.id.textView2);
        website = findViewById(R.id.websitelink);
        bio = findViewById(R.id.textView4);
        followw = findViewById(R.id.Pagename_button);

        follow_friend = findViewById(R.id.tvColor_page);

        webviewer = (LinearLayout) findViewById(R.id.linearLayout10);


        followerCount = findViewById(R.id.followerCount);
        followingCount = findViewById(R.id.followingCount);

        bio.setText(getIntent().getStringExtra("bio"));
        website.setText(getIntent().getStringExtra("website"));

        String id = getIntent().getStringExtra("_id");
        System.out.println(id);
        System.out.println("@@@@@");

        followerCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), follower.class);
                intent.putExtra("idd", id);
                startActivity(intent);
            }
        });


        followingCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), following.class);
                intent.putExtra("idd", id);
                startActivity(intent);
            }
        });



        Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().followerfollowingcount(id);

        call.enqueue(new Callback<addfriends_model>() {
            @Override
            public void onResponse(Call<addfriends_model> call, Response<addfriends_model> response) {
                System.out.println("sucessfull");
                System.out.println(response);
                System.out.println('*');
//                userList= response.body().getUsers();
//                System.out.println(userList);
//                adapters = new addFriendsonclick_adapter(getApplication(),userList);
//                recyclerView.setAdapter(new addFriendsonclick_adapter(getApplication(), (ArrayList<User>) userList));
                if(response.isSuccessful()){

                    String followercount = response.body().getFollower_count();
                    String followingcount = response.body().getFollowing_count();
                    followerCount.setText(followercount);
                    followingCount.setText(followingcount);
                }
            }

            @Override
            public void onFailure(Call<addfriends_model> call, Throwable t) {

            }
        });



        name.setText(getIntent().getStringExtra("name"));
        Username.setText(getIntent().getStringExtra("username"));
        System.out.println(getIntent().getStringExtra("_id"));
        System.out.println(getIntent().getStringExtra("username"));
        viewUserVideo=new ViewUserVideo();
        viewUserFavourite=new ViewUserFavourite();


        String abc = getIntent().getStringExtra("username");
        System.out.println(abc);
        System.out.println("abc");

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("viewUserName", abc);
        editor.apply();






        Bundle bundle = new Bundle();
        viewUserVideo = new ViewUserVideo();
        bundle.putString("key", getIntent().getStringExtra("username"));
        viewUserVideo.setArguments(bundle);
        getSupportFragmentManager().setFragmentResult("datafrom1", bundle);

        String user_profile_id = getIntent().getStringExtra("_id");

        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        String user_id = sharedPreferences.getString("id", null);
        System.out.println(user_id);

        checkfollower(user_profile_id, user_id);

        webviewer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewUser.this, load_website.class);
//                System.out.println(bioo);
                intent.putExtra("urll", "https://www.feardogmusic.com");
                startActivity(intent);
                System.out.println("webviews");
            }
        });

        followw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state == true){
                    System.out.println(state);
                    followw.setCardBackgroundColor(Color.parseColor("#FF5B71"));
                    follow_friend.setText("Follow");
                    follow_friend.setTextColor(Color.WHITE);
                    System.out.println("Follow");
//                    System.out.println("Unfriends");
//                    followw.setCardBackgroundColor(Color.parseColor("#F3F5F6"));
//                    follow_friend.setText("Friend");
//                    follow_friend.setTextColor(Color.BLACK);
//                    System.out.println("Friend");
//                    state = true;
//                    System.out.println(state);
                    unfriend(user_profile_id, user_id);


                }
                else if(state == false){
                    followw.setCardBackgroundColor(Color.parseColor("#F3F5F6"));
                    follow_friend.setText("Friend");
                    follow_friend.setTextColor(Color.BLACK);
                    System.out.println("Friend");
//                    System.out.println("friend");
//                    followw.setCardBackgroundColor(Color.parseColor("#FF5B71"));
//                    follow_friend.setText("Follow");
//                    follow_friend.setTextColor(Color.WHITE);
//                    System.out.println("Follow");
                    addfriend(user_profile_id, user_id);

                }

            }
        });


//        if(getIntent().hasExtra("nameeee")){
//            System.out.println("yes have intent");
//            System.out.println(getIntent().getStringExtra("name"));
//            System.out.println(getIntent().getStringExtra("nameeee"));
//        }


      MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(myPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }

    private void unfriend(String user_profile_id, String user_id){
        Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().unfollowie(user_id, user_profile_id);

        call.enqueue(new Callback<addfriends_model>() {
            @Override
            public void onResponse(Call<addfriends_model> call, Response<addfriends_model> response) {

                System.out.println("sucessfull");
                System.out.println(response);
                System.out.println('*');
//                userList= response.body().getUsers();
//                System.out.println(userList);
//                adapters = new addFriendsonclick_adapter(getApplication(),userList);
//                recyclerView.setAdapter(new addFriendsonclick_adapter(getApplication(), (ArrayList<User>) userList));
                if(response.isSuccessful()){

                    String check = response.body().getResponse();
                    System.out.println(check);
                    if(check.equals("unfollowed")){
                        System.out.println("unfollowed");
                        state = false;
                        System.out.println(state);
                        followw.setCardBackgroundColor(Color.parseColor("#FF5B71"));
                        follow_friend.setText("Follow");
                        follow_friend.setTextColor(Color.WHITE);
                        System.out.println("Follow");
//                        addfriend(user_profile_id, user_id);
//                        checkfollower(user_profile_id, user_id);
//                        final ColorStateList cardBackgroundColor = followw.getCardBackgroundColor(Color.parseColor("#ffff"));
                    }
                    else{

//                        followw.setCardBackgroundColor(Color.parseColor("#F3F5F6"));
//                        follow_friend.setText("Friend");
//                        follow_friend.setTextColor(Color.BLACK);
//                        System.out.println("Friend");
//                        state = true;
                        System.out.println("user does not existed!");
//                        sendemailotp(s);


//                                        Intent intent=new Intent(getActivity(), OtpVerification_Activity.class);
//                                        intent.putExtra("email", abc);
//                                        System.out.println(s);
//                                        startActivity(intent);
                    }
//                            adapters = new addFriendsonclick_adapter(getApplication(),userList);
//                            recyclerView.setAdapter(new addFriendsonclick_adapter(getApplication(), (ArrayList<User>) userList));
                }

            }




            @Override
            public void onFailure(Call<addfriends_model> call, Throwable t) {
                System.out.println("Error"+call.request().url());
                if (t instanceof IOException) {
//                    Toast.makeText(AddFriends.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                    System.out.println("this is an actual network failure :( inform the user and possibly retry");
                }
                else {
//                    Toast.makeText(AddFriends.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                    System.out.println("conversion issue! big problem :)");
                }
            }
        });
    }


    private  void addfriend(String user_profile_id, String user_id){
        Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().follow(user_id, user_profile_id);

        call.enqueue(new Callback<addfriends_model>() {
            @Override
            public void onResponse(Call<addfriends_model> call, Response<addfriends_model> response) {

                System.out.println("sucessfull");
                System.out.println(response);
                System.out.println('*');
//                userList= response.body().getUsers();
//                System.out.println(userList);
//                adapters = new addFriendsonclick_adapter(getApplication(),userList);
//                recyclerView.setAdapter(new addFriendsonclick_adapter(getApplication(), (ArrayList<User>) userList));
                if(response.isSuccessful()){

                    String check = response.body().getResponse();
                    System.out.println(check);
                    if(check.equals("followed")){
                        System.out.println("followed");
                        state = true;
                        System.out.println(state);
                        followw.setCardBackgroundColor(Color.parseColor("#F3F5F6"));
                        follow_friend.setText("Friend");
                        follow_friend.setTextColor(Color.BLACK);
                        System.out.println("Friend");
//                        state = true;
                        System.out.println(state);
//                        checkfollower(user_profile_id, user_id);
//                        final ColorStateList cardBackgroundColor = followw.getCardBackgroundColor(Color.parseColor("#ffff"));
                    }
                    else{
//                        followw.setCardBackgroundColor(Color.parseColor("#F3F5F6"));
//                        follow_friend.setText("Friend");
//                        follow_friend.setTextColor(Color.BLACK);
//                        System.out.println("Friend");
//                        state = true;
                        System.out.println("user existed!");
//                        sendemailotp(s);


//                                        Intent intent=new Intent(getActivity(), OtpVerification_Activity.class);
//                                        intent.putExtra("email", abc);
//                                        System.out.println(s);
//                                        startActivity(intent);
                    }
//                            adapters = new addFriendsonclick_adapter(getApplication(),userList);
//                            recyclerView.setAdapter(new addFriendsonclick_adapter(getApplication(), (ArrayList<User>) userList));
                }

            }




            @Override
            public void onFailure(Call<addfriends_model> call, Throwable t) {
                System.out.println("Error"+call.request().url());
                if (t instanceof IOException) {
//                    Toast.makeText(AddFriends.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                    System.out.println("this is an actual network failure :( inform the user and possibly retry");
                }
                else {
//                    Toast.makeText(AddFriends.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                    System.out.println("conversion issue! big problem :)");
                }
            }
        });
    }

    private void checkfollower(String user_profile_id, String user_id) {
//        System.out.println(user_id);

        Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().followie(user_id, user_profile_id);

        call.enqueue(new Callback<addfriends_model>() {
            @Override
            public void onResponse(Call<addfriends_model> call, Response<addfriends_model> response) {

                System.out.println("sucessfull");
                System.out.println(response);
                System.out.println('*');
//                userList= response.body().getUsers();
//                System.out.println(userList);
//                adapters = new addFriendsonclick_adapter(getApplication(),userList);
//                recyclerView.setAdapter(new addFriendsonclick_adapter(getApplication(), (ArrayList<User>) userList));
                if(response.isSuccessful()){

                    String check = response.body().getResponse();
                    System.out.println(check);
                    if(check.equals("Follow")){
                        System.out.println("Follow");
                        state = false;
                        System.out.println(state);
                        followw.setCardBackgroundColor(Color.parseColor("#FF5B71"));
                        follow_friend.setText("Follow");
                        follow_friend.setTextColor(Color.WHITE);
                        System.out.println("Follow");
//                        final ColorStateList cardBackgroundColor = followw.getCardBackgroundColor(Color.parseColor("#ffff"));
                    }
                    else if(check.equals("Friend")){
                        followw.setCardBackgroundColor(Color.parseColor("#F3F5F6"));
                        follow_friend.setText("Friend");
                        follow_friend.setTextColor(Color.BLACK);
                        System.out.println("Friend");
                        state = true;
                        System.out.println(state);
//                        sendemailotp(s);


//                                        Intent intent=new Intent(getActivity(), OtpVerification_Activity.class);
//                                        intent.putExtra("email", abc);
//                                        System.out.println(s);
//                                        startActivity(intent);
                    }
//                            adapters = new addFriendsonclick_adapter(getApplication(),userList);
//                            recyclerView.setAdapter(new addFriendsonclick_adapter(getApplication(), (ArrayList<User>) userList));
                }

            }




            @Override
            public void onFailure(Call<addfriends_model> call, Throwable t) {
                System.out.println("Error"+call.request().url());
                if (t instanceof IOException) {
//                    Toast.makeText(AddFriends.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                    System.out.println("this is an actual network failure :( inform the user and possibly retry");
                }
                else {
//                    Toast.makeText(AddFriends.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                    System.out.println("conversion issue! big problem :)");
                }
            }
        });

    }

    class MyPagerAdapter extends FragmentPagerAdapter {


        String[] fragmentNames = {"Phone", "Email"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return viewUserVideo;
                case 1:
                    return viewUserFavourite;
            }
            return null;
        }

        @Override
        public int getCount() {
            return fragmentNames.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            Drawable image = getApplicationContext().getResources().getDrawable(imageResId[position]);
            image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
            SpannableString sb = new SpannableString("  ");
            ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
            sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return sb;
        }
    }

}
