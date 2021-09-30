package com.example.swipeup_frontend.addfriends;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swipeup_frontend.R;
import com.example.swipeup_frontend.api.RetrofitClient;
import com.example.swipeup_frontend.profile.User;

import java.io.IOException;
import java.util.ArrayList;

//import io.realm.mongodb.mongo.MongoClientURI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class AddFriends extends AppCompatActivity {
    private LinearLayout layout;
    private SearchView searchView;

    ArrayList<User> userList;
//    private static final String url="http://10.0.2.2:5000/find/";

    addfriend_adapter myaddfriend_adapter;

//    String Appid="swipeup_registration-qzrzs";
//
//    MongoClient mongoClient;
//    MongoCollection mongoCollection;
//    MongoDatabase mongoDatabase;
//
//    MongoCursor<Document> results;

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing email.
    public static final String EMAIL_KEY = "email_key";
    SharedPreferences sharedpreferences;
    String email;


    RecyclerView recyclerView;
//    public AddFriends() {
//        super();
//    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);
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


        Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().find();

        call.enqueue(new Callback<addfriends_model>() {
            @Override
            public void onResponse(Call<addfriends_model> call, Response<addfriends_model> response) {

                System.out.println("sucessfull");
                System.out.println(response);
                userList= new ArrayList<>(response.body().getUsers());
                myaddfriend_adapter = new addfriend_adapter(getApplication(),userList);
                recyclerView.setAdapter(new addfriend_adapter(getApplication(),userList));
                if(response.isSuccessful()){
                    userList= new ArrayList<>(response.body().getUsers());
                    recyclerView.setAdapter(new addfriend_adapter(getApplicationContext(),userList));
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

        searchView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddFriendsonclick.class);

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(searchView, "slide");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AddFriends.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });


//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
////                System.out.println(newText);
//                myaddfriend_adapter.getFilter().filter(newText);
//                return false;
//            }
//        });



            }


}