package com.example.swipeup_frontend.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swipeup_frontend.R;
import com.example.swipeup_frontend.addfriends.addfriends_model;
import com.example.swipeup_frontend.api.RetrofitClient;
import com.example.swipeup_frontend.login.PagenameActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsernameActivity extends AppCompatActivity {
    EditText ex_username;
    private TextView tvColor;
    private CardView btnUsername;
    private boolean isusernameClickable = false;

    String Appid = "swipeup_registration-qzrzs";

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Toast.makeText(UsernameActivity.this,"There is no back action",Toast.LENGTH_LONG).show();
        return;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);


        ex_username=findViewById(R.id.Username);
        btnUsername=findViewById(R.id.Username_button);
        tvColor = findViewById(R.id.tvColor);
        String username = ex_username.getText().toString();

//        Intent intent = getIntent();
//        String semail = intent.getStringExtra("email");
//        String sphone = intent.getStringExtra("phone");
//        System.out.println(semail);
//        System.out.println(sphone);




//                if(sphone == null)
//                {
//                    Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().OtpVerify(semail,a);
//
//                    call.enqueue(new Callback<addfriends_model>() {
//                        @Override
//                        public void onResponse(Call<addfriends_model> call, Response<addfriends_model> response) {
//
//                            System.out.println("sucessfull");
//                            System.out.println(response);
//                            System.out.println('*');
////                userList= response.body().getUsers();
////                System.out.println(userList);
////                adapters = new addFriendsonclick_adapter(getApplication(),userList);
////                recyclerView.setAdapter(new addFriendsonclick_adapter(getApplication(), (ArrayList<User>) userList));
//                            if(response.isSuccessful()){
//
//                                String check = response.body().getResponse();
//                                System.out.println(check);
//                                if(check.equals("verified")){
//                                    Intent intent = new Intent(getApplicationContext(), PagenameActivity.class);
//                                    intent.putExtra("email", semail);
//                                    startActivity(intent);
//                                    System.out.println("verified");
//                                }
//                                else{
////                                    Intent intent=new Intent(getContext(), OtpVerification_Activity.class);
////                                    intent.putExtra("email", s);
////                                    startActivity(intent);
//                                    System.out.println("wrong otp!");
//                                }
////                            adapters = new addFriendsonclick_adapter(getApplication(),userList);
////                            recyclerView.setAdapter(new addFriendsonclick_adapter(getApplication(), (ArrayList<User>) userList));
//                            }
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<addfriends_model> call, Throwable t) {
//                            System.out.println("Error"+call.request().url());
//                            if (t instanceof IOException) {
////                    Toast.makeText(AddFriends.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
//                                // logging probably not necessary
//                                System.out.println("this is an actual network failure :( inform the user and possibly retry");
//                            }
//                            else {
////                    Toast.makeText(AddFriends.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
//                                // todo log to some central bug tracking service
//                                System.out.println("conversion issue! big problem :)");
//                            }
//                        }
//                    });
//                }
//                else {
//
//
//                    Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().OtpVerify(sphone,a);
//
//                    call.enqueue(new Callback<addfriends_model>() {
//                        @Override
//                        public void onResponse(Call<addfriends_model> call, Response<addfriends_model> response) {
//
//                            System.out.println("sucessfull");
//                            System.out.println(response);
//                            System.out.println('*');
////                userList= response.body().getUsers();
////                System.out.println(userList);
////                adapters = new addFriendsonclick_adapter(getApplication(),userList);
////                recyclerView.setAdapter(new addFriendsonclick_adapter(getApplication(), (ArrayList<User>) userList));
//                            if(response.isSuccessful()){
//
//                                String check = response.body().getResponse();
//                                System.out.println(check);
//                                if(check.equals("verified")){
//                                    Intent intent = new Intent(getApplicationContext(), PagenameActivity.class);
//                                    intent.putExtra("email", semail);
//                                    startActivity(intent);
//                                    System.out.println("verified");
//                                }
//                                else{
////                                    Intent intent=new Intent(getContext(), OtpVerification_Activity.class);
////                                    intent.putExtra("email", s);
////                                    startActivity(intent);
//                                    System.out.println("wrong otp!");
//                                }
////                            adapters = new addFriendsonclick_adapter(getApplication(),userList);
////                            recyclerView.setAdapter(new addFriendsonclick_adapter(getApplication(), (ArrayList<User>) userList));
//                            }
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<addfriends_model> call, Throwable t) {
//                            System.out.println("Error"+call.request().url());
//                            if (t instanceof IOException) {
////                    Toast.makeText(AddFriends.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
//                                // logging probably not necessary
//                                System.out.println("this is an actual network failure :( inform the user and possibly retry");
//                            }
//                            else {
////                    Toast.makeText(AddFriends.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
//                                // todo log to some central bug tracking service
//                                System.out.println("conversion issue! big problem :)");
//                            }
//                        }
//                    });
//
//
//                }



//                Realm.init(getApplicationContext());
//                App app=new App(new AppConfiguration.Builder(Appid).build());
//
//                Credentials credentials = Credentials.anonymous();
//                app.loginAsync(credentials, it -> {
//                    if (it.isSuccess()) {
//                        Log.v("User","Logged In Anonymously");
//                        User user = app.currentUser();
//                        assert user != null;
//                        Functions functionsManager = app.getFunctions(user);
//                        List<String> a = new ArrayList<String>();
////                        Intent i = getIntent();
////                        String semail = i.getStringExtra("email");
////                        String sphone = i.getStringExtra("phone");
//
//                        if(sphone == null) {
//                            a.add(semail);
//                            a.add(ex_username.getText().toString());
//                            functionsManager.callFunctionAsync("cusername", a, String.class, result -> {
//                                if (result.isSuccess()) {
//                                    Log.v("EXAMPLE", "Sum value: " + result.get());
//                                    if (result.get().equals("username exists!")) {
//                                        Toast.makeText(getApplicationContext(), "username exists!", Toast.LENGTH_LONG).show();
//                                    } else {
//                                        // Intent from this actvity to Choose Pagename Activity with email in intent
//                                        Intent intent = new Intent(getApplicationContext(), PagenameActivity.class);
//                                        intent.putExtra("email", semail);
//                                        startActivity(intent);
//
//                                    }
//                                } else {
//                                    Log.e("EXAMPLE", "failed to call sum function with: " + result.getError());
//                                }
//                            });
//                        }
//                        else{
//                            a.add(sphone);
//                            a.add(ex_username.getText().toString());
//                            functionsManager.callFunctionAsync("cusernamePhone", a, String.class, result -> {
//                                if (result.isSuccess()) {
//                                    Log.v("EXAMPLE", "Sum value: " + result.get());
//                                    if (result.get().equals("username exists!")) {
//                                        Toast.makeText(getApplicationContext(), "username exists!", Toast.LENGTH_LONG).show();
//                                    } else {
//                                        // Intent from this actvity to Choose Pagename Activity with email in intent
//                                        Intent intent = new Intent(getApplicationContext(), PagenameActivity.class);
//                                        intent.putExtra("phone", sphone);
//                                        startActivity(intent);
//                                    }
//                                } else {
//                                    Log.e("EXAMPLE", "failed to call sum function with: " + result.getError());
//                                }
//                            });
//                        }
//
//
//                    } else {
//                        Log.e("EXAMPLE", "Error logging into the Realm app. Make sure that anonymous authentication is enabled. Error: " + it.getError());
//                    }
//                });
//
//            }
//        });
        inputChange();

    }

    @SuppressLint("ResourceType")
    private void checkAllData() {
        String username = ex_username.getText().toString();
        if ((!username.isEmpty())) {
            isusernameClickable = true;
            tvColor.setTextColor(Color.WHITE);
            btnUsername.setCardBackgroundColor(Color.parseColor(getString(R.color.pink)));
        } else {
            isusernameClickable = false;
            btnUsername.setCardBackgroundColor(Color.parseColor(getString(R.color.light_gray)));
        }
    }


    private void inputChange() {
        ex_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkAllData();

            }

            @Override
            public void afterTextChanged(Editable s) {
                btnUsername.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String password = ex_username.getText().toString();
                        System.out.println(password);
                        String abc = s.toString();
                        Intent i = getIntent();
                        String semail = i.getStringExtra("email");
                        String sphone = i.getStringExtra("phone");


                        if(sphone == null)
                        {
                            Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().username(semail,abc);

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
                                            Intent intent = new Intent(getApplicationContext(), PagenameActivity.class);
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


                            Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().username(sphone, abc);

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
                                            Intent intent = new Intent(getApplicationContext(), PagenameActivity.class);
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








                    }});
            }
        });

    }
}