package com.example.swipeup_frontend.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swipeup_frontend.profile.BirthdayActivity;
import com.example.swipeup_frontend.R;
import com.example.swipeup_frontend.api.RetrofitClient;
import com.example.swipeup_frontend.addfriends.addfriends_model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.functions.Functions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagenameActivity extends AppCompatActivity {
    EditText ex_pagename;
    private TextView tvColor;
    private CardView btnPagename;
    private boolean ispagenameClickable = false;

    String Appid = "swipeup_registration-qzrzs";
    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Toast.makeText(PagenameActivity.this,"There is no back action",Toast.LENGTH_LONG).show();
        return;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagename);
        ex_pagename=findViewById(R.id.Page_name);
        btnPagename=findViewById(R.id.Pagename_button);
        tvColor = findViewById(R.id.tvColor_page);





        btnPagename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pagename = ex_pagename.getText().toString();
                System.out.println(pagename);







                Realm.init(getApplicationContext());
                App app=new App(new AppConfiguration.Builder(Appid).build());

                Credentials credentials = Credentials.anonymous();
                app.loginAsync(credentials, it -> {
                    if (it.isSuccess()) {
                        Log.v("User","Logged In Anonymously");
                        User user = app.currentUser();
                        assert user != null;
                        Functions functionsManager = app.getFunctions(user);
                        List<String> a = new ArrayList<String>();
                        Intent i = getIntent();
                        String semail = i.getStringExtra("email");
                        String sphone = i.getStringExtra("phone");

                        if(sphone == null) {
                            a.add(semail);
                            a.add(ex_pagename.getText().toString());
                            functionsManager.callFunctionAsync("c_pagename", a, String.class, result -> {
                                if (result.isSuccess()) {
                                    Log.v("EXAMPLE", "Sum value: " + result.get());
                                    if (result.get().equals("pagename exists!")) {
                                        //Toast Message
                                        Toast.makeText(getApplicationContext(), "pagename exists!", Toast.LENGTH_LONG).show();
                                    } else {
                                        //Intent from this actvity to Choose Birthdate Activity with email in intent
                                        Intent intent = new Intent(getApplicationContext(), BirthdayActivity.class);
                                        intent.putExtra("email", semail);
                                        startActivity(intent);

                                    }
                                } else {
                                    Log.e("EXAMPLE", "failed to call sum function with: " + result.getError());
                                }
                            });
                        }else{
                            a.add(sphone);
                            a.add(ex_pagename.getText().toString());
                            functionsManager.callFunctionAsync("c_pagenamephone", a, String.class, result -> {
                                if (result.isSuccess()) {
                                    Log.v("EXAMPLE", "Sum value: " + result.get());
                                    if (result.get().equals("pagename exists!")) {
                                        //Toast Message
                                        Toast.makeText(getApplicationContext(), "pagename exists!", Toast.LENGTH_LONG).show();
                                    } else {
                                        //Intent from this actvity to Choose Birthdate Activity with email in intent
                                        Intent intent = new Intent(getApplicationContext(), BirthdayActivity.class);
                                        intent.putExtra("phone", sphone);
                                        startActivity(intent);

                                    }
                                } else {
                                    Log.e("EXAMPLE", "failed to call sum function with: " + result.getError());
                                }
                            });
                        }

                    } else {
                        Log.e("EXAMPLE", "Error logging into the Realm app. Make sure that anonymous authentication is enabled. Error: " + it.getError());
                    }
                });

            }
        });
        inputChange();

    }







    @SuppressLint("ResourceType")
    private void checkAllData() {
        String username = ex_pagename.getText().toString();
        if ((!username.isEmpty())) {
            ispagenameClickable = true;
            tvColor.setTextColor(Color.WHITE);
            btnPagename.setCardBackgroundColor(Color.parseColor(getString(R.color.pink)));
        } else {
            ispagenameClickable = false;
            btnPagename.setCardBackgroundColor(Color.parseColor(getString(R.color.light_gray)));
        }
    }


    private void inputChange() {
        ex_pagename.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkAllData();

            }

            @Override
            public void afterTextChanged(Editable s) {

                btnPagename.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String abc = s.toString();
                        Intent i = getIntent();
                        String semail = i.getStringExtra("email");
                        String sphone = i.getStringExtra("phone");

                        if(sphone == null)
                        {
                            Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().pagename(semail,abc);

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

                                        String check = response.body().getResponse();
                                        System.out.println(check);
                                        if(check.equals("verified")){
                                            Intent intent = new Intent(getApplicationContext(), BirthdayActivity.class);
                                            intent.putExtra("email", semail);
                                            startActivity(intent);
                                            System.out.println("verified");
                                        }
                                        else{
                                            //                                    Intent intent=new Intent(getContext(), OtpVerification_Activity.class);
                                            //                                    intent.putExtra("email", s);
                                            //                                    startActivity(intent);
                                            System.out.println("wrong otp!");
                                        }
                                        //                            adapters = new addFriendsonclick_adapter(getApplication(),userList);
                                        //                            recyclerView.setAdapter(new addFriendsonclick_adapter(getApplication(), (ArrayList<User>) userList));
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
                        else {


                            Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().pagename(sphone, abc);

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

                                        String check = response.body().getResponse();
                                        System.out.println(check);
                                        if(check.equals("verified")){
                                            Intent intent = new Intent(getApplicationContext(), BirthdayActivity.class);
                                            intent.putExtra("email", semail);
                                            startActivity(intent);
                                            System.out.println("verified");
                                        }
                                        else{
                                            //                                    Intent intent=new Intent(getContext(), OtpVerification_Activity.class);
                                            //                                    intent.putExtra("email", s);
                                            //                                    startActivity(intent);
                                            System.out.println("wrong otp!");
                                        }
                                        //                            adapters = new addFriendsonclick_adapter(getApplication(),userList);
                                        //                            recyclerView.setAdapter(new addFriendsonclick_adapter(getApplication(), (ArrayList<User>) userList));
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
                });

            }
        });

    }
}