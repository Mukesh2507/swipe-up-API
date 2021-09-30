package com.example.swipeup_frontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HashtagsDisplay extends AppCompatActivity {

    TextView hashtagname;
    RecyclerView recyclerView;



    List<videos> userList;
    profileVideoAdapter adapter;

    SharedPreferences sharedPreferences;
    public static final String SHARED_PREFS = "shared_prefs";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hashtags_display);

        hashtagname = findViewById(R.id.textViewhashtag);
        recyclerView = findViewById(R.id.videorecyclerViewHashtag);


        String i = getIntent().getStringExtra("bookmark_hashtag");
        System.out.println(i);
        hashtagname.setText(i);


        sharedPreferences = this.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("username", null);
        System.out.println(id);
        System.out.println("**");
        String abcd = "#"+i;
        fetchUserss(abcd);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        adapter = new profileVideoAdapter(getApplication(), userList);
//        adapters = new BookMarkSounds_adpater(getActivity(),userList);
        recyclerView.setAdapter(adapter);
    }

    private void fetchUserss(String id) {
        Call<profileVideoRow> call = new RetrofitClient().getInstance().getApi().bookmarkHastagVideoss(id);

        call.enqueue(new Callback<profileVideoRow>() {
            @Override
            public void onResponse(Call<profileVideoRow> call, Response<profileVideoRow> response) {
                System.out.println("sucessfull");
                System.out.println(response);
                System.out.println('*');
                if(response.isSuccessful()){
                    userList= response.body().getVideos();
                    System.out.println(userList);
                    adapter = new profileVideoAdapter(getApplication(),userList);
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