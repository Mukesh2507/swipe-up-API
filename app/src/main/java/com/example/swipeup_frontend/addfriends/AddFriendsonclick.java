package com.example.swipeup_frontend.addfriends;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.swipeup_frontend.R;
import com.example.swipeup_frontend.api.RetrofitClient;
import com.example.swipeup_frontend.profile.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFriendsonclick extends AppCompatActivity {

    LinearLayout linearLayout;
    private SearchView searchView;
//    addfriend_adapter adapters;
    addFriendsonclick_adapter adapters;
    RecyclerView recyclerView;
    List<User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friendsonclick);

        searchView=findViewById(R.id.searchFriends);

        recyclerView = findViewById(R.id.reecview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));



//        fetchUsers("");





        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                System.out.println(newText);
//                adapters.getFilter().filter(newText);
                fetchUsers(newText);

                 System.out.println(newText);

                return false;
            }
        });





    }

    public void fetchUsers(String Key){
        Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().searchFriend(Key);

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

                    userList= response.body().getUsers();
                    System.out.println(userList);
                    adapters = new addFriendsonclick_adapter(getApplication(),userList);
                    recyclerView.setAdapter(new addFriendsonclick_adapter(getApplication(), (ArrayList<User>) userList));
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