package com.example.swipeup_frontend.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swipeup_frontend.R;
import com.example.swipeup_frontend.api.RetrofitClient;
import com.example.swipeup_frontend.videos.videos;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFavourite#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFavourite extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    List<videos> userList;
    profileVideoAdapter adapter;
    RecyclerView recyclerView;

    SharedPreferences sharedPreferences;
    public static final String SHARED_PREFS = "shared_prefs";


    public ProfileFavourite() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFavourite.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFavourite newInstance(String param1, String param2) {
        ProfileFavourite fragment = new ProfileFavourite();
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
        View view = inflater.inflate(R.layout.fragment_profile_favourite, container, false);
        recyclerView = view.findViewById(R.id.videorecyclerView_favourite);
        recyclerView.setHasFixedSize(true);
        /* recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));*/
        recyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        );
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

        recyclerView = getActivity().findViewById(R.id.videorecyclerView_favourite);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        adapter = new profileVideoAdapter(getContext(), userList);
//        adapters = new BookMarkSounds_adpater(getActivity(),userList);
        recyclerView.setAdapter(adapter);



    }

    private void fetchUserss(String id) {
        Call<profileVideoRow> call = new RetrofitClient().getInstance().getApi().userVideosFavourite(id);
        call.enqueue(new Callback<profileVideoRow>() {
            @Override
            public void onResponse(Call<profileVideoRow> call, Response<profileVideoRow> response) {
                System.out.println("sucessfull");
                System.out.println(response);
                System.out.println('*');
                if(response.isSuccessful()){
                    userList= response.body().getVideos();
                    System.out.println(userList);
                    adapter = new profileVideoAdapter(getContext(),userList);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<profileVideoRow> call, Throwable t) {
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