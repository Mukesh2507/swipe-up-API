package com.example.swipeup_frontend.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.swipeup_frontend.qrcode.QrScan;
import com.example.swipeup_frontend.post.Post;
import com.example.swipeup_frontend.R;
import com.example.swipeup_frontend.api.RetrofitClient;
import com.example.swipeup_frontend.addfriends.AddFriends;
import com.example.swipeup_frontend.addfriends.addfriends_model;
import com.example.swipeup_frontend.bookmark.BookMarks;
import com.example.swipeup_frontend.load_website;
import com.google.android.material.tabs.TabLayout;

import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileActivity extends Fragment {
  private TabLayout tabLayout;
  private ViewPager viewPager;
  private View view;
  private ImageView settings_image,add_friends,bookmarks, plusbutton;
  private CardView editprofile;
  private ProfileVidios profileVidios;
  private ProfileFavourite profileFavourite;
  private ProfileLock profileLock;
  private TextView Username, names, profileimage, bio, website;
  private  TextView follower_count, following_count;
  private  ImageView qrscanner;

  private LinearLayout webviews;

    String Appid="swipeup_registration-qzrzs";

    MongoClient mongoClient;
    MongoCollection mongoCollection;
    MongoDatabase mongoDatabase;

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing email.
//    public static final String EMAIL_KEY = "email_key";
    SharedPreferences sharedpreferences;
//    SharedPrefManager sharedPrefManager;

    String email;



  private int[] imageResId = {
            R.drawable.video_icon_selector,
            R.drawable.heart_icon,
            R.drawable.lock_icon

    };


    public ProfileActivity() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileActivity.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileActivity newInstance(String param1, String param2) {
        ProfileActivity fragment = new ProfileActivity();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile_activity, container, false);


//        sharedPrefManager = new SharedPrefManager(getActivity());

        // initializing our shared preferences.
        sharedpreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // getting data from shared prefs and
        // storing it in our string variable.
        String username = sharedpreferences.getString("username", null);
        String name = sharedpreferences.getString("name", null);
        String bioo = sharedpreferences.getString("bio", null);
        String websites = sharedpreferences.getString("website", null);
        System.out.println(username);
        System.out.println(websites);
        System.out.println(name);
        System.out.println(bioo);

        tabLayout = view.findViewById(R.id.profile_tab);
        settings_image = view.findViewById(R.id.settings_image);
        add_friends = view.findViewById(R.id.add_friends);
        viewPager = view.findViewById(R.id.proflie_slider);
        bookmarks = view.findViewById(R.id.bookmark);
        editprofile = view.findViewById(R.id.edit_profile);

        Username = view.findViewById(R.id.t50);
        names = view.findViewById(R.id.textView2);
        bio = view.findViewById(R.id.textView4);
        website = view.findViewById(R.id.website);
        plusbutton = view.findViewById(R.id.postbutton);

        webviews = view.findViewById(R.id.linearLayout10);
        qrscanner = view.findViewById(R.id.qrscanner);


        follower_count = view.findViewById(R.id.follower_count);
        following_count = view.findViewById(R.id.following_count);

        String id = sharedpreferences.getString("id", null);
        qrscanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), QrScan.class);
                intent.putExtra(username,"username");
                startActivity(intent);
            }
        });

        follower_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), follower.class);
                intent.putExtra("idd", id);
                startActivity(intent);
            }
        });

        following_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), following.class);
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
                    follower_count.setText(followercount);
                    following_count.setText(followingcount);
                }
            }

            @Override
            public void onFailure(Call<addfriends_model> call, Throwable t) {

            }
        });





        Username.setText(username);
        names.setText(name);
        bio.setText(bioo);
        website.setText(websites);





//        User usernamee = SharedPrefManager.getXyz();
//        System.out.println(usernamee);
//        Username.setText(usernamee.);

//        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
//        String s1 = sh.getString("name", "");
//        int a = sh.getInt("age", 0);
//        Realm.init(getContext());
//        App app=new App(new AppConfiguration.Builder(Appid).build());
//        Credentials credentials=Credentials.anonymous();
//        app.loginAsync(credentials,it -> {
//            if (it.isSuccess()) {
//                Log.v("User","as");
//                User user=app.currentUser();
//                mongoClient = user.getMongoClient("mongodb-atlas");
//                mongoDatabase = mongoClient.getDatabase("swipeup");
//                mongoCollection = mongoDatabase.getCollection("registration");
//
//                Document querfilter = new Document().append("email",email);
//                mongoCollection.findOne(querfilter).getAsync(result -> {
//                    if(result.isSuccess()){
//                        Log.v("data", result.toString());
//                        Document resultdata = (Document) result.get();
//                        System.out.println(resultdata.get("username"));
//                        Username.setText((String) resultdata.get("username"));
//                        names.setText((String) resultdata.get("name"));
//                        bio.setText((String) resultdata.get("bio"));
//                        website.setText((String) resultdata.get("website"));
//                    }
//                    else{
//                        Log.v("data", result.toString());
//                    }
//                });
//            }
//            else {
//                Log.e("EXAMPLE","sum failed:");
//            }
//
//        });


        plusbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Post.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });


        webviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), load_website.class);
                System.out.println(bioo);
                intent.putExtra("urll", websites);
                startActivity(intent);
                System.out.println("webviews");
            }
        });



        bookmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getContext(), BookMarks.class);
                startActivity(intent);
            }
        });

        bio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), load_website.class);
                startActivity(intent);
                System.out.println("bio");
            }
        });

        add_friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), AddFriends.class);
                startActivity(intent);
            }
        });
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eintent=new Intent(getContext(), EditProfile.class);
                startActivity(eintent);
            }
        });


        settings_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), SettingActicity.class);
                startActivity(intent);
            }
        });

        profileVidios=new ProfileVidios();
        profileFavourite=new ProfileFavourite();
        profileLock=new ProfileLock();

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());

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

        return view;
    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        String[] fragmentNames = {"Phone", "Email","Email"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return profileVidios;
                case 1:
                    return profileFavourite;
                case 2:
                    return profileLock;
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
            Drawable image = getContext().getResources().getDrawable(imageResId[position]);
            image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
            SpannableString sb = new SpannableString("  ");
            ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
            sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return sb;
        }

    }
}