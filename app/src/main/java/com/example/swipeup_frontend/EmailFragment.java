package com.example.swipeup_frontend;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.functions.Functions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailFragment extends Fragment {

    EditText ex_email;
    private TextView tvColor, signin;
    private CardView btnEmail;
    private boolean isemailClickable = false;
    String Appid = "swipeup_registration-qzrzs";

    String Appid1 = "application-123-scseh";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=(ViewGroup)inflater.inflate(R.layout.emailfragment,container,false);




        ex_email=view.findViewById(R.id.email);
        btnEmail=view.findViewById(R.id.email_button);
        tvColor = view.findViewById(R.id.tvColor_email);
        signin = view.findViewById(R.id.t6);
        System.out.println(ex_email.getText().toString());
        String email1 = ex_email.getText().toString();
        System.out.println("****");
        System.out.println(email1);
        System.out.println("****");
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(getContext(), Sign_in_Activity.class);
                startActivity(intents);
            }
        });

        System.out.println(ex_email);

//        Intent intent=new Intent(getActivity(), OtpVerification_Activity.class);
//        intent.putExtra("email", "sushant234.sarvade@gmail.com");
////        System.out.println(s);
//        startActivity(intent);

        inputChange();
//        checkAllData();

        return view;

    }


    @SuppressLint("ResourceType")
    private void checkAllData() {
        String email = ex_email.getText().toString();
        if ((!email.isEmpty())) {
            isemailClickable = true;
            tvColor.setTextColor(Color.WHITE);
            btnEmail.setCardBackgroundColor(Color.parseColor(getString(R.color.pink)));
        } else {
            isemailClickable = false;
            btnEmail.setCardBackgroundColor(Color.parseColor(getString(R.color.light_gray)));
        }
    }


    private void inputChange() {
        ex_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkAllData();

            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.println(s);
                btnEmail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String abc = s.toString();
//                        System.out.println(ex_email.getText().toString());
                        System.out.println(s);
                        Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().email12(s);

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
                                    if(check.equals("registerd")){
                                        System.out.println("user registered!");
                                    }
                                    else{


                                        sendemailotp(s);


//                                        Intent intent=new Intent(getActivity(), OtpVerification_Activity.class);
//                                        intent.putExtra("email", abc);
//                                        System.out.println(s);
//                                        startActivity(intent);
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




//                ex_email take data
//                call retrofit api and pass the data (ex_email)
//                get response from api
//                if api response is "otp send"
//                redirect to otp fragment "using Intent"
//                Realm.init(getContext());
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
//                        a.add(ex_email.getText().toString());
//                        functionsManager.callFunctionAsync("checkemail", a, String.class, result -> {
//                            if (result.isSuccess()) {
//                                Log.v("EXAMPLE", "Sum value: " + result.get());
//                                if (result.get().equals("false")){
//                                    sendEmailOtp();
//                                    Log.v("EXAMPLE", "CHCEK");
//                                }
//                                else{
//                                    Intent intent=new Intent(getContext(), Sign_in_Activity.class);
//                                    startActivity(intent);
//                                }
//                            } else {
//                                Log.e("EXAMPLE", "failed to call sum function with: " + result.getError());
//                            }
//                        });
//                    } else {
//                        Log.e("EXAMPLE", "Error logging into the Realm app. Make sure that anonymous authentication is enabled. Error: " + it.getError());
//                    }
//                });



                    }

                    private void sendemailotp(Editable s) {
                        System.out.println(s);
                        System.out.println("****");
                        Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().sendemail(s);

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
                                    if(check.equals("success")){
                                        System.out.println("Otp Send");
                                        Intent intent=new Intent(getActivity(), OtpVerification_Activity.class);
                                        intent.putExtra("email", s.toString());
                                        System.out.println(s);
                                        startActivity(intent);
                                    }
                                    else{
                                        Intent intent=new Intent(getActivity(), OtpVerification_Activity.class);
                                        intent.putExtra("email", s.toString());
                                        System.out.println(s);
                                        startActivity(intent);
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
//            private void sendEmailOtp() {
//                Realm.init(getContext());
//
//                App app1 = new App(new AppConfiguration.Builder(Appid1).build());
//
//                Credentials credentials = Credentials.anonymous();
//                app1.loginAsync(credentials, it -> {
//                    if (it.isSuccess()) {
//                        Log.v("User","Logged In Anonymously");
//                        User user = app1.currentUser();
//                        assert user != null;
//                        Functions functionsManager = app1.getFunctions(user);
//                        List<String> a = new ArrayList<String>();
//                        a.add(ex_email.getText().toString());
//                        functionsManager.callFunctionAsync("checkuser", a, String.class, result -> {
//                            if (result.isSuccess()) {
//                                Log.v("EXAMPLE", "Sum value: " + result.get());
//
//                                Intent intent=new Intent(getContext(), OtpVerification_Activity.class);
//                                intent.putExtra("email", ex_email.getText().toString());
//                                startActivity(intent);
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
            }
        });
    }

}

