package com.example.swipeup_frontend;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeActivity extends Fragment {


    ViewPager2 viewPager2;
    VideosAdapter adapter;
    List<Home_Model_List> videoList;
    SharedPreferences sharedPreferences;
    public static final String SHARED_PREFS = "shared_prefs";
    public HomeActivity() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeActivity.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeActivity newInstance(String param1, String param2) {
        HomeActivity fragment = new HomeActivity();
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

        View view = inflater.inflate(R.layout.fragment_home_activity, container, false);
        viewPager2=(ViewPager2)view.findViewById(R.id.ViewPager2);

        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", null);
        System.out.println(id);
        System.out.println("**");
        fetchuserss(id);



//    adapter=new VideosAdapter(options);
//        viewPager2.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", null);
        System.out.println(id);
        System.out.println("**");
          fetchuserss(id);

        viewPager2=getActivity().findViewById(R.id.ViewPager2);
      //  adapter = new VideosAdapter(getActivity(),videoList);
        viewPager2.setAdapter(adapter);

    }


    private void fetchuserss(String id) {
        System.out.println("printing home activity id" +id);
        Call<Home_Model> call = new RetrofitClient().getInstance().getApi().homeVideos(id);

        call.enqueue(new Callback<Home_Model>() {
            @Override
            public void onResponse(Call<Home_Model> call, Response<Home_Model> response) {
                System.out.println("sucessfull");
                System.out.println(response);
                System.out.println('*');


                if(response.isSuccessful()){

//                    String resp = response.body().getResponse();
                    videoList= response.body().getVideos();
                    System.out.println(videoList);
//                    System.out.println(userList);
                    adapter = new VideosAdapter(getContext(),videoList);
//                    recyclerView.setAdapter(new SearchUser_adapter(getContext(), userList));
                    viewPager2.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<Home_Model> call, Throwable t) {

            }
        });


    }

}