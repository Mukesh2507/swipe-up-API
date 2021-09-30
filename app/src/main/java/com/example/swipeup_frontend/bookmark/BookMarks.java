package com.example.swipeup_frontend.bookmark;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.swipeup_frontend.R;
import com.google.android.material.tabs.TabLayout;

public class BookMarks extends AppCompatActivity {
    TabLayout bookMarkstab;
    ViewPager bookMarksviewPager;
    TextView text;


    SharedPreferences sharedPreferences;
    public static final String SHARE_PREFS =  "shared_prefs";


    BookMarksVideos bookMarksVideos;
    BookMarkHashtages bookMarkHashtages;
    BookMarksSounds bookMarksSounds;
    BookMarksEffects bookMarksEffects;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_marks);


        bookMarkstab=findViewById(R.id.bookmarks_tab);
        bookMarksviewPager=findViewById(R.id.bookmarks_viewpager);

        sharedPreferences = getSharedPreferences(SHARE_PREFS, Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", null);
        System.out.println(id);
        System.out.println("**********");

        bookMarksVideos=new BookMarksVideos();
        bookMarkHashtages=new BookMarkHashtages();
        bookMarksSounds=new BookMarksSounds();
        bookMarksEffects=new BookMarksEffects();


//        Bundle bundle = new Bundle();
//        bookMarkHashtages = new BookMarkHashtages();
//        bundle.putString("id", id);
//        bookMarkHashtages.setArguments(bundle);
//        getParentFragmentManager().setFragmentResult("id", bundle);

        BookMarks.MyPagerAdapter myPagerAdapter = new BookMarks.MyPagerAdapter(getSupportFragmentManager());

        bookMarkstab.setupWithViewPager(bookMarksviewPager);
        bookMarksviewPager.setAdapter(myPagerAdapter);

        bookMarksviewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

    class MyPagerAdapter extends FragmentPagerAdapter {

        String[] fragmentNames = {"Video", "Hashtags","Sounds","Effects"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return bookMarksVideos;
                case 1:
                    return bookMarkHashtages;
                case 2:
                    return bookMarksSounds;
                case 3:
                    return bookMarksEffects;
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
            return fragmentNames[position];
        }
    }

}
