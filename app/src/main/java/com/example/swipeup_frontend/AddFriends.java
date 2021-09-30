package com.example.swipeup_frontend;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Network;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.RealmResultTask;
import io.realm.mongodb.mongo.MongoClient;
//import io.realm.mongodb.mongo.MongoClientURI;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.iterable.MongoCursor;
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