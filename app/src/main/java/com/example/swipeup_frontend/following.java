package com.example.swipeup_frontend;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class following extends AppCompatActivity {



    private LinearLayout layout;
    private SearchView searchView;

    List<bookmark_hashtag_model> userList;
    follower_adpater adapters;

    public static final String SHARED_PREFS = "shared_prefs";

    public static final String EMAIL_KEY = "email_key";
    SharedPreferences sharedpreferences;
    String email;

    RecyclerView recyclerView;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following);


        searchView=findViewById(R.id.add_friend_search);
        layout=findViewById(R.id.card);

        // initializing our shared preferences.
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // getting data from shared prefs and
        // storing it in our string variable.
        email = sharedpreferences.getString(EMAIL_KEY, null);
        System.out.println(email);

        recyclerView = findViewById(R.id.recview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        String abcd = getIntent().getStringExtra("idd");
        System.out.println(abcd);

        fetchUsers(abcd);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                System.out.println(newText);
//                adapters.getFilter().filter(newText);
//                fetchUsers(newText);

                System.out.println(newText);

                return false;
            }


        });

    }

    private void fetchUsers(String abcd) {

        Call<hashtag_model> call = new RetrofitClient().getInstance().getApi().followingg(abcd);

        call.enqueue(new Callback<hashtag_model>() {
            @Override
            public void onResponse(Call<hashtag_model> call, Response<hashtag_model> response) {
                System.out.println("sucessfull");
                System.out.println(response);
                System.out.println('*');
                if(response.isSuccessful()){
                    userList= response.body().getHashtags();
                    System.out.println(userList);
                    adapters = new follower_adpater(getApplication(),userList);
                    recyclerView.setAdapter(new follower_adpater(getApplication(), userList));
                }
            }

            @Override
            public void onFailure(Call<hashtag_model> call, Throwable t) {

            }
        });


    }

}
