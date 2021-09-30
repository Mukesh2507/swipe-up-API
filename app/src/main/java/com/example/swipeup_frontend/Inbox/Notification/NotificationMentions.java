package com.example.swipeup_frontend.Inbox.Notification;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swipeup_frontend.Inbox.video_details;
import com.example.swipeup_frontend.R;
import com.example.swipeup_frontend.api.RetrofitClient;
import com.example.swipeup_frontend.videos.videos;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationMentions#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationMentions extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    SharedPreferences sharedPreferences;
    public static final String SHARED_PREFS = "shared_prefs";

    NotificationMentions_adpater adapters;
    List<videos> userList;

    RecyclerView recyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotificationMentions() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationMentions.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationMentions newInstance(String param1, String param2) {
        NotificationMentions fragment = new NotificationMentions();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification_mentions, container, false);
        recyclerView = view.findViewById(R.id.User_recyclerview_notify_mention);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("username", null);
        System.out.println(id);
        System.out.println("**");

        fetchUserss(id);

        recyclerView = getActivity().findViewById(R.id.User_recyclerview_notify_mention);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapters = new NotificationMentions_adpater(getActivity(),userList);
        recyclerView.setAdapter(adapters);

    }

    private void fetchUserss(String id) {

        Call<video_details>   call = new RetrofitClient().getInstance().getApi().notifyMention(id);


//        call.enqueue(new Callback<addfriends_model>() {
//            @Override
//            public void onResponse(Call<addfriends_model> call, Response<addfriends_model> response) {
//                System.out.println("sucessfull");
//                System.out.println(response);
//                System.out.println('*');
//                if(response.isSuccessful()){
//                    String resp = response.body().getResponse();
//                    userList= response.body().;
//                    System.out.println(userList);
//                    adapters = new NotificationMentions_adpater(getContext(),userList);
//                    recyclerView.setAdapter(adapters);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<addfriends_model> call, Throwable t) {
//
//            }
//        });

        call.enqueue(new Callback<video_details>() {
            @Override
            public void onResponse(Call<video_details> call, Response<video_details> response) {
                System.out.println(response);
                if(response.isSuccessful()){
                    userList = response.body().getVideodetailss();
                    System.out.println(userList);
                    System.out.println("mentions");
                    adapters = new NotificationMentions_adpater(getContext(), userList);
                    recyclerView.setAdapter(adapters);
                }
            }

            @Override
            public void onFailure(Call<video_details> call, Throwable t) {

            }
        });

    }


}