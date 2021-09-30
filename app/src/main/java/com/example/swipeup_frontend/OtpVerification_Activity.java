package com.example.swipeup_frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.goodiebag.pinview.Pinview;

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

public class OtpVerification_Activity extends AppCompatActivity {

    TextView t51;
    TextView t61;
    ImageView backo;
    String Appid1 = "application-123-scseh";
    String Appid = "swipeup_registration-qzrzs";
    PinView pinview;
    Editable Rd;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification_);

        Pinview pin = new Pinview(this);


        t61=findViewById(R.id.Resendcode);


        pin = (Pinview) findViewById(R.id.pinview);
      //  pin.setInputType(Pinview.InputType.NUMBER);
//  pin.setValue("1234");


        Intent intent = getIntent();
        String semail = intent.getStringExtra("email");
        String sphone = intent.getStringExtra("phone");
        System.out.println(semail);
        System.out.println(sphone);


        pin.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean fromUser) {
                //Make api calls here or what not
                Toast.makeText(OtpVerification_Activity.this, pinview.getValue(), Toast.LENGTH_SHORT).show();
                System.out.println(pinview.getValue());
                String a = (String) pinview.getValue().toString();
                System.out.println(pinview);
//                Intent intent = getIntent();
//                String semail = intent.getStringExtra("email");
//                String sphone = intent.getStringExtra("phone");
//                System.out.println(semail);
//                System.out.println(sphone);
                if(sphone == null)
                {
                    Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().OtpVerify(semail,a);

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
                                    Intent i = new Intent(OtpVerification_Activity.this,UsernameActivity.class);
                                    i.putExtra("email", semail);
                                    startActivity(i);
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


                    Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().OtpVerify(sphone,a);

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
                                    Intent i = new Intent(OtpVerification_Activity.this,UsernameActivity.class);
                                    i.putExtra("phone", sphone);
                                    startActivity(i);
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









//                Realm.init(getBaseContext());
//                App app=new App(new AppConfiguration.Builder(Appid1).build());

//                Credentials credentials = Credentials.anonymous();
//                app.loginAsync(credentials, it -> {
//                    if (it.isSuccess()) {
//                        Log.v("User","Logged In Anonymously");
//                        User user = app.currentUser();
//                        assert user != null;
//                        Functions functionsManager = app.getFunctions(user);
//                        List<String> a = new ArrayList<String>();
//                        Intent intent = getIntent();
//                        String semail = intent.getStringExtra("email");
//                        String sphone = intent.getStringExtra("phone");
//                        String resetpassword = intent.getStringExtra("resetpassword");
//                        System.out.println(semail);
//                        System.out.println(sphone);
////                        System.out.println(s);
//                        if(sphone == null) {
//                            a.add(semail);
//                            a.add((String) pinview.getValue().toString());
//                            System.out.println("**");
//                            functionsManager.callFunctionAsync("checkemailOtp", a, String.class, result -> {
//                                System.out.println("**");
//                                if (result.isSuccess()) {
//                                    Log.v("EXAMPLE", "Sum value: " + result.get());
//                                    if (result.get().equals("false")) {
//                                        Toast.makeText(getApplicationContext(), "Hello Javatpoint", Toast.LENGTH_SHORT).show();
//                                        System.out.println(pinview.getValue().toString());
//                                    } else {
//                                        if(resetpassword == "true"){
//                                            System.out.println("true");
//                                        }
//                                        else {
////                                            userregistrationtable();
//                                            Log.v("EXAMPLE", "CHCEK");
//                                        }
//                                        //    Intent i = new Intent(OtpVerification_Activity.this,Username_page_Fragment.class);
//                                        //   i.putExtra("email", semail);
//                                        // startActivity(i);
//                                    }
//                                } else {
//                                    Log.e("EXAMPLE", "failed to call sum function with: " + result.getError());
//                                }
//                            });
//                        }
//                        else{
//                            a.add(sphone);
//                            System.out.println(sphone);
//                            a.add((String) pinview.getValue().toString());
//                            System.out.println("**");
//                            functionsManager.callFunctionAsync("checkphoneotp", a, String.class, result -> {
//                                System.out.println("**");
//                                if (result.isSuccess()) {
//                                    Log.v("EXAMPLE", "Sum value: " + result.get());
//                                    if (result.get().equals("false")) {
//                                        Toast.makeText(getApplicationContext(), "Hello Javatpoint", Toast.LENGTH_SHORT).show();
//                                        System.out.println(pinview.getValue().toString());
//                                    } else {
//                                        if(resetpassword == "true"){
//                                            System.out.println("true");
//                                        }
//                                        else {
////                                            userregistrationtablephone();
//                                        }
//                                        Log.v("EXAMPLE", "CHCEK");
//                                        //    Intent i = new Intent(OtpVerification_Activity.this,Username_page_Fragment.class);
//                                        //   i.putExtra("email", semail);
//                                        // startActivity(i);
//                                    }
//                                } else {
//                                    Log.e("EXAMPLE", "failed to call sum function with: " + result.getError());
//                                }
//                            });
//
//                        }
//                    } else {
//                        Log.e("EXAMPLE", "Error logging into the Realm app. Make sure that anonymous authentication is enabled. Error: " + it.getError());
//                    }
//                });


            }

//            private void userregistrationtablephone() {
//                Realm.init(getBaseContext());
//
//                App app1 = new App(new AppConfiguration.Builder(Appid).build());
//
//                Credentials credentials = Credentials.anonymous();
//                app1.loginAsync(credentials, it -> {
//                    if (it.isSuccess()) {
//                        Log.v("User","Logged In Anonymously");
//                        User user = app1.currentUser();
//                        assert user != null;
//                        Functions functionsManager = app1.getFunctions(user);
//                        List<String> a = new ArrayList<String>();
//                        Intent intent = getIntent();
////                        String semail = intent.getStringExtra("email");
//                        String sphone = intent.getStringExtra("phone");
//                        String sresetpassword = intent.getStringExtra("resetpassword");
////                        System.out.println(semail);
////                        System.out.println(s);
////                        a.add(semail);
////                        a.add(ex_email.getText().toString());
//                        a.add(sphone);
//                        functionsManager.callFunctionAsync("registerUserPhone", a, String.class, result -> {
//                            if (result.isSuccess()) {
//                                Log.v("EXAMPLE", "Sum value: " + result.get());
//
//                                if (sresetpassword == null){
//                                    Intent i=new Intent(OtpVerification_Activity.this,UsernameActivity.class);
//                                    i.putExtra("phone", sphone);
//                                    startActivity(i);
//                                }
//                                else {
//                                    Intent i1 = new Intent(getApplicationContext(), PasswordActivity.class);
//                                    i1.putExtra("phone", sphone);
//                                    startActivity(i1);
//                                }
//
//                                //Redirect otp
//                            } else {
//                                Log.e("EXAMPLE", "failed to call sum function with: " + result.getError());
//                            }
//                        });
//                    } else {
//                        Log.e("EXAMPLE", "Error logging into the Realm app. Make sure that anonymous authentication is enabled. Error: " + it.getError());
//                    }
//                });
//            }

//            private void userregistrationtable() {
//                Realm.init(getBaseContext());
//
//                App app1 = new App(new AppConfiguration.Builder(Appid).build());
//
//                Credentials credentials = Credentials.anonymous();
//                app1.loginAsync(credentials, it -> {
//                    if (it.isSuccess()) {
//                        Log.v("User","Logged In Anonymously");
//                        User user = app1.currentUser();
//                        assert user != null;
//                        Functions functionsManager = app1.getFunctions(user);
//                        List<String> a = new ArrayList<String>();
//                        Intent intent = getIntent();
//                        String semail = intent.getStringExtra("email");
//                        String sresetpassword = intent.getStringExtra("resetpassword");
//                        System.out.println(semail);
////                        System.out.println(s);
//                        a.add(semail);
////                        a.add(ex_email.getText().toString());
//                        functionsManager.callFunctionAsync("registerUser", a, String.class, result -> {
//                            if (result.isSuccess()) {
//                                Log.v("EXAMPLE", "Sum value: " + result.get());
//                                if (sresetpassword == null){
//                                    Intent i=new Intent(OtpVerification_Activity.this,UsernameActivity.class);
//                                    i.putExtra("email", semail);
//                                    startActivity(i);
//                                }
//                                else {
//                                    Intent i1 = new Intent(getApplicationContext(), PasswordActivity.class);
//                                    i1.putExtra("email", semail);
//                                    startActivity(i1);
//                                }
//
//                                //Redirect otp
//                            } else {
//                                Log.e("EXAMPLE", "failed to call sum function with: " + result.getError());
//                            }
//                        });
//                    } else {
//                        Log.e("EXAMPLE", "Error logging into the Realm app. Make sure that anonymous authentication is enabled. Error: " + it.getError());
//                    }
//                });
//
//            }
        });



//        t61.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Realm.init(getBaseContext());
//                App app=new App(new AppConfiguration.Builder(Appid1).build());
//
//                Credentials credentials = Credentials.anonymous();
//                app.loginAsync(credentials, it -> {
//                    if (it.isSuccess()) {
//                        Log.v("User","Logged In Anonymously");
//                        User user = app.currentUser();
//                        assert user != null;
//                        Functions functionsManager = app.getFunctions(user);
//                        List<String> a = new ArrayList<String>();
//                        Intent intent = getIntent();
//                        String semail = intent.getStringExtra("email");
//                        System.out.println("semail"+semail);
//                        System.out.println('*');
//                        a.add(semail);
//                        functionsManager.callFunctionAsync("resendEmailOtp", a, String.class, result -> {
//                            if (result.isSuccess()) {
//                                Log.v("EXAMPLE", "Sum value: " + result.get());
//                            } else {
//                                Log.e("EXAMPLE", "failed to call sum function with: " + result.getError());
//                            }
//                        });
//                    } else {
//                        Log.e("EXAMPLE", "Error logging into the Realm app. Make sure that anonymous authentication is enabled. Error: " + it.getError());
//                    }
//                });
//            }
//        });

/*
        backo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OtpVerification_Activity.this,bottom_navigation_menu.class);
                startActivity(i);
            }
        });*/
    }

}