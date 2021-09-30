package com.example.swipeup_frontend;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class tabpageradapter extends FragmentPagerAdapter {
    int totaltab;
    public tabpageradapter(@NonNull FragmentManager fm, int totaltab) {
        super(fm);
        this.totaltab = totaltab;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                NotificationAll allfragment = new NotificationAll();
                return allfragment;
            case 1:
                NotificationLikes allfragment1 = new NotificationLikes();
                return allfragment1;
            case 2:
                NotificationComments allfragment2 = new NotificationComments();
                return allfragment2;
            case 3:
                NotificationFollowers allfragment3 = new NotificationFollowers();
                return allfragment3;
            case 4:
                NotificationMentions allfragment4 = new NotificationMentions();
                return allfragment4;
            case 5:
                NotificationSwipeUp allfragment5 = new NotificationSwipeUp();
                return allfragment5;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totaltab;
    }

}
