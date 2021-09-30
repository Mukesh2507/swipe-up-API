package com.example.swipeup_frontend;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationFollowers#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFollowers extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    NotificationFollowers_adapter adapters;
    List<User> userList;

    SharedPreferences sharedPreferences;
    public static final String SHARED_PREFS = "shared_prefs";

    public NotificationFollowers() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationFollowers.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationFollowers newInstance(String param1, String param2) {
        NotificationFollowers fragment = new NotificationFollowers();
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
        View v = inflater.inflate(R.layout.fragment_notification_followers, container, false);
        recyclerView = v.findViewById(R.id.User_recyclerview_notify);
//        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
//        String id = sharedPreferences.getString("id", null);
//        System.out.println(id);
//        fetchUserss(id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        getParentFragmentManager().setFragmentResultListener("datafrom1", this, new FragmentResultListener() {
//            @Override
//            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
//                String data = result.getString("key");
//                System.out.println(data);
//                fetchUserss(data);
//            }
//        });
//        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", null);
        System.out.println(id);

        getParentFragmentManager().setFragmentResultListener("id", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String data = result.getString("key");
                System.out.println(data);
                fetchUserss(data);
            }
        });

//        fetchUserss(id);

        recyclerView = getActivity().findViewById(R.id.User_recyclerview_notify);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapters = new NotificationFollowers_adapter(getActivity(),userList);
        recyclerView.setAdapter(adapters);

//        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
//        viewModel.getText().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
//            @Override
//            public void onChanged(CharSequence charSequence) {
////                recyclerView.setHasFixedSize(true);
////                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
////                adapters = new SearchUser_adapter(getContext(),userList);
////                recyclerView.setAdapter(adapters);
//                System.out.println(charSequence);
//            }
//        });

    }



    public void fetchUserss(String Key){
        System.out.println(Key);
        Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().notifyFollower(Key);

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

                    String resp = response.body().getResponse();
                    userList= response.body().getUsers();
                    System.out.println(userList);
//                    System.out.println(userList);
                    adapters = new NotificationFollowers_adapter(getContext(),userList);
//                    recyclerView.setAdapter(new SearchUser_adapter(getContext(), userList));
                    recyclerView.setAdapter(adapters);
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



}