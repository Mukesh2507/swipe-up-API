package com.example.swipeup_frontend;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.functions.Functions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordActivity extends AppCompatActivity {
    private EditText etPassword;
    private TextView tvPasswordError, tvColor;
    private CardView frameOne, frameTwo;
    private CardView btnRegister;
    String Appid = "swipeup_registration-qzrzs";
    private boolean isAtLeast8 = false, hasUppercase = false, hasNumber = false, hasSymbol = false, isRegistrationClickable = false;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^"+
                    "(?=.*[0-9])"+
                    "(?=.*[a-z])"+
                    "(?=.*[A-Z])"+
                    "(?=\\+$)"+
                    "$"
            );


    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Toast.makeText(PasswordActivity.this,"There is no back action",Toast.LENGTH_LONG).show();
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);


        etPassword=findViewById(R.id.et_password);
        frameOne=findViewById(R.id.cardone);
        frameTwo=findViewById(R.id.cardtwo);
        btnRegister=findViewById(R.id.setpassword);
        tvColor = findViewById(R.id.tvColor);
        // tvPasswordError = view.findViewById(R.id.tvPasswordError);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String paassword = etPassword.getText().toString();
                System.out.println(etPassword.getText().toString());
                if (paassword.length() > 0) {
//                    if (isRegistrationClickable) {
//                        Toast.makeText(getContext(), "REGISTRATION", Toast.LENGTH_LONG).show();
//                    }






                    Intent i = getIntent();
                    String semail = i.getStringExtra("email");
                    String sphone = i.getStringExtra("phone");


                    if(sphone == null)
                    {
                        Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().passsword(semail,paassword);

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
                                        Intent intent = new Intent(getApplicationContext(), Sign_in_Activity.class);
//                                        intent.putExtra("email", semail);
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
                        Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().passsword(sphone, paassword);

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
                                        Intent intent = new Intent(getApplicationContext(), Sign_in_Activity.class);
//                                        intent.putExtra("email", semail);
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











//                    Realm.init(getApplicationContext());
//                    App app=new App(new AppConfiguration.Builder(Appid).build());
//
//                    Credentials credentials = Credentials.anonymous();
//                    app.loginAsync(credentials, it -> {
//                        if (it.isSuccess()) {
//                            Log.v("User","Logged In Anonymously");
//                            User user = app.currentUser();
//                            assert user != null;
//                            Functions functionsManager = app.getFunctions(user);
//                            List<String> a = new ArrayList<String>();
//                            Intent i = getIntent();
//                            String semail = i.getStringExtra("email");
//                            String sphone = i.getStringExtra("phone");
//
//                            if(sphone == null) {
//
//                                a.add(semail);
//                                a.add(etPassword.getText().toString());
//                                functionsManager.callFunctionAsync("password", a, String.class, result -> {
//                                    if (result.isSuccess()) {
//                                        Log.v("EXAMPLE", "Sum value: " + result.get());
//                                        if (result.get().equals("false")) {
//                                            Toast.makeText(getApplicationContext(), "false", Toast.LENGTH_LONG).show();
//                                        } else {
//                                            //  Intent from this actvity to Login Activity with email in intent
//                                            Intent intent = new Intent(getApplicationContext(), Sign_in_Activity.class);
//                                            intent.putExtra("email", semail);
//                                            startActivity(intent);
//                                        }
//                                    } else {
//                                        Log.e("EXAMPLE", "failed to call sum function with: " + result.getError());
//                                    }
//                                });
//
//                            }
//                            else{
//
//                                a.add(sphone);
//                                a.add(etPassword.getText().toString());
//                                functionsManager.callFunctionAsync("passwordPhone", a, String.class, result -> {
//                                    if (result.isSuccess()) {
//                                        Log.v("EXAMPLE", "Sum value: " + result.get());
//                                        if (result.get().equals("false")) {
//                                            Toast.makeText(getApplicationContext(), "false", Toast.LENGTH_LONG).show();
//                                        } else {
//                                            //  Intent from this actvity to Login Activity with email in intent
//                                            Intent intent = new Intent(getApplicationContext(), Sign_in_Activity.class);
//                                            intent.putExtra("phone", sphone);
//                                            startActivity(intent);
//                                        }
//                                    } else {
//                                        Log.e("EXAMPLE", "failed to call sum function with: " + result.getError());
//                                    }
//                                });
//
//                            }
//                        } else {
//                            Log.e("EXAMPLE", "Error logging into the Realm app. Make sure that anonymous authentication is enabled. Error: " + it.getError());
//                        }
//                    });


                } //else if (password.length() == 0){
                //           tvPasswordError.setVisibility(View.VISIBLE);
                //}

            }
        });
        inputChange();

    }

    @SuppressLint("ResourceType")
    private void checkAllData() {
        if (isAtLeast8 && hasUppercase) {
            isRegistrationClickable = true;
            tvColor.setTextColor(Color.WHITE);
            btnRegister.setCardBackgroundColor(Color.parseColor(getString(R.color.pink)));
        } else {
            isRegistrationClickable = false;
            btnRegister.setCardBackgroundColor(Color.parseColor(getString(R.color.light_gray)));
        }
    }

    @SuppressLint("ResourceType")
    private void registrationDataCheck() {
        String password = etPassword.getText().toString();

        if (password.matches("(.*[0-9!@#$%^&*()_+=].*)")){
            hasUppercase = true;

            frameTwo.setCardBackgroundColor(Color.parseColor(getString(R.color.light_green)));
        } else {
            hasUppercase = false;
            frameTwo.setCardBackgroundColor(Color.parseColor(getString(R.color.light_gray)));
        }


        if (password.matches("(.*[A-Za-z].*)"+"(?=\\S+$)"+".{7,}")) {
            isAtLeast8 = true;
            frameOne.setCardBackgroundColor(Color.parseColor(getString(R.color.light_green)));
        } else {
            isAtLeast8 = false;
            frameOne.setCardBackgroundColor(Color.parseColor(getString(R.color.light_gray)));
        }
        checkAllData();

    }
    private void inputChange() {
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                registrationDataCheck();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}