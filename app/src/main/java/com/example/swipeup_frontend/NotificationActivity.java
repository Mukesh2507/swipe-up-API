package com.example.swipeup_frontend;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationActivity extends Fragment {
private View view;

    NotificationFollowers notificationFollowers;
    NotificationMentions notificationMentions;

    SharedPreferences sharedPreferences;
    public static final String SHARED_PREFS = "shared_prefs";

    ViewPager tabpager;
    TabLayout tabLayout;
    CardView tabCard;
    int whiteicon[] = {R.drawable.speech_white_48px,R.drawable.heart_outline_white_100px,R.drawable.chat_bubble_white_100px,R.drawable.user_white_60px,R.drawable.email_white_64px,R.drawable.prize_white_48px};
    int greyicon[] = {R.drawable.speech_grey_48px,R.drawable.heart_outline__grey_100px,R.drawable.chat_bubble_grey_100px,R.drawable.user_grey_60px,R.drawable.email_grey_64px,R.drawable.prize_grey_48px};
    String Name[] = {"All","Likes","Comments","Followers","Mention","Swipe Up"};
    ImageView tabicon;
    TextView tabtitle;



    public NotificationActivity() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationActivity.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationActivity newInstance(String param1, String param2) {
        NotificationActivity fragment = new NotificationActivity();
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
        view= inflater.inflate(R.layout.fragment_notification_activity, container, false);
        tabLayout = view.findViewById(R.id.index_tab);
        tabpager = view.findViewById(R.id.index_viewPager);

        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", null);


        tabCard = tabLayout.getTabAt(0).getCustomView().findViewById(R.id.tabCard);
        tabCard.setCardBackgroundColor(getResources().getColor(R.color.pink));
        for (int i=1;i<6;i++)
        {
            tabicon = tabLayout.getTabAt(i).getCustomView().findViewById(R.id.tabicon);
            tabicon.setImageResource(greyicon[i]);
            tabtitle = tabLayout.getTabAt(i).getCustomView().findViewById(R.id.tabtitle);
            tabtitle.setText(Name[i]);

        }
        final tabpageradapter Adapter = new tabpageradapter(getActivity().getSupportFragmentManager(),tabLayout.getTabCount());
        tabpager.setAdapter(Adapter);
        tabpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabCard = tab.getCustomView().findViewById(R.id.tabCard);
                tabCard.setCardBackgroundColor(getResources().getColor(R.color.pink));
                tabicon = tab.getCustomView().findViewById(R.id.tabicon);
                tabicon.setImageResource(whiteicon[tab.getPosition()]);
                tabtitle = tab.getCustomView().findViewById(R.id.tabtitle);
                tabtitle.setTextColor(Color.WHITE);
                tabpager.setCurrentItem(tab.getPosition());

                Bundle bundle = new Bundle();
                notificationFollowers = new NotificationFollowers();
                bundle.putString("key", id);
                System.out.println("*Notify*");
                System.out.println(id);
                notificationFollowers.setArguments(bundle);
                getParentFragmentManager().setFragmentResult("id", bundle);

                notificationMentions = new NotificationMentions();



            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tabCard = tab.getCustomView().findViewById(R.id.tabCard);
                tabCard.setCardBackgroundColor(Color.WHITE);
                tabicon = tab.getCustomView().findViewById(R.id.tabicon);
                tabicon.setImageResource(greyicon[tab.getPosition()]);
                tabtitle = tab.getCustomView().findViewById(R.id.tabtitle);
                tabtitle.setTextColor(Color.BLACK);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

}