package com.example.swipeup_frontend;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchActivity extends Fragment implements SearchUsers.searchuserListener {
    TabLayout searchMarkstab;
    ViewPager searchviewPager;
    View view;
    private SearchView searchView;
    SearchTop searchTop;
    SearchVideo searchVideos;
    SearchUsers searchUsers;
    SearchHashtags searchHashtags;
    SearchSounds searchSounds;

    private SharedViewModel viewModel;
    private EditText editText;


    public SearchActivity() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchActivity.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchActivity newInstance(String param1, String param2) {
        SearchActivity fragment = new SearchActivity();
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
        view= inflater.inflate(R.layout.fragment_search_activity, container, false);


        searchView = view.findViewById(R.id.searchviewer);
        searchMarkstab=view.findViewById(R.id.search_tab);
        searchviewPager=view.findViewById(R.id.search_viewpager);



        searchTop=new SearchTop();
        searchVideos=new SearchVideo();
        searchUsers=new SearchUsers();
        searchHashtags=new SearchHashtags();
        searchSounds=new SearchSounds();


        SearchActivity.MyPagerAdapter myPagerAdapter = new SearchActivity.MyPagerAdapter(getActivity().getSupportFragmentManager());

        searchMarkstab.setupWithViewPager(searchviewPager);
        searchviewPager.setAdapter(myPagerAdapter);


        searchviewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                System.out.println(newText);
//                if(newText != null) {
//                    Bundle bundle = new Bundle();
////                    heartRateListener.onHeartRate(newText);
//                }
//                viewModel.setText(newText);
//               bundle.putString("key", newText);
//                getParentFragmentManager().setFragmentResult("datafrom1", bundle);




                Bundle bundle = new Bundle();
                searchUsers = new SearchUsers();
                bundle.putString("key", newText);
                searchUsers.setArguments(bundle);
                getParentFragmentManager().setFragmentResult("datafrom1", bundle);
//                getFragmentManager().beginTransaction().replace(R.id.search_viewpager, searchUsers).commit();
//                searchVideos = new SearchVideo();
//                searchVideos.setArguments(bundle);
////                getFragmentManager().beginTransaction().replace(R.id.search_viewpager, searchVideos).commit();
//                searchHashtags = new SearchHashtags();
//                searchHashtags.setArguments(bundle);
////                getFragmentManager().beginTransaction().replace(R.id.search_viewpager, searchHashtags).commit();
//                searchSounds = new SearchSounds();
//                searchSounds.setArguments(bundle);
////                getFragmentManager().beginTransaction().replace(R.id.search_viewpager, searchSounds).commit();
//                searchTop = new SearchTop();
//                searchTop.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.search_viewpager, searchTop).commit();

                return false;
            }
        });




        return view;
    }

    @Override
    public void onInputAsent(CharSequence input) {

    }

//    private static HeartRateListener heartRateListener;
//    public static void setHeartRateListener(HeartRateListener listener){
//        heartRateListener = listener;
//    }
//    public static interface HeartRateListener{
//        void onHeartRate(String value);
//    }


    private class MyPagerAdapter extends FragmentPagerAdapter {

        String[] fragmentNames = {"Top","Video","Users", "Hashtags","Sounds"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return searchTop;
                case 1:
                    return searchVideos;
                case 2:
                    return searchUsers;
                case 3:
                    return searchHashtags;
                case 4:
                    return searchSounds;
            }
//            Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.search_viewpager);
//            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//            fragmentTransaction.detach(currentFragment);
//            fragmentTransaction.attach(currentFragment);
//            fragmentTransaction.commit();
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