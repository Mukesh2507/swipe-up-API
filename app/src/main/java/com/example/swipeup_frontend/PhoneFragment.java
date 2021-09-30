package com.example.swipeup_frontend;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.functions.Functions;

public class PhoneFragment extends Fragment {



    private TextView sign_in,tvColor;
    private EditText phone;
    private View view;
    private CardView Signup_Button;
    private boolean isphoneClickable = false;
    CountryCodePicker cpp;

    String Appid = "swipeup_registration-qzrzs";

    String Appid1 = "application-123-scseh";



    public  static  final  String MyPreferences="My Perf";
    public  static  final  String UserPhone="phoneKey";

    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.phonefragment, container, false);


        sign_in=view.findViewById(R.id.Signin);
        phone=view.findViewById(R.id.et_phone);
        Signup_Button=view.findViewById(R.id.phone_signup_button);

//        CountryCodePicker cpp = findViewById()

        cpp =  view.findViewById(R.id.et_phone1);
        cpp.registerCarrierNumberEditText(phone);

        tvColor = view.findViewById(R.id.tvColor_phone);


        sharedPreferences = getActivity().getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);

        Signup_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User_Phone = phone.getText().toString().trim();
//                String country = cpp.
                SharedPreferences.Editor editor=sharedPreferences.edit();

                if (User_Phone.isEmpty()){
                    phone.setError("Please Enter Phone Number");
                }
                editor.putString(UserPhone,User_Phone);
                editor.commit();
                System.out.println(User_Phone);
                String phoneNumber = cpp.getFullNumber();
//                System.out.println();
                System.out.println(phoneNumber);

                Realm.init(getContext());
                App app=new App(new AppConfiguration.Builder(Appid).build());

                Credentials credentials = Credentials.anonymous();
                app.loginAsync(credentials, it -> {
                    if (it.isSuccess()) {
                        Log.v("User","Logged In Anonymously");
                        User user = app.currentUser();
                        assert user != null;
                        Functions functionsManager = app.getFunctions(user);
                        List<String> a = new ArrayList<String>();
                        a.add(phoneNumber);
                        functionsManager.callFunctionAsync("checkphone", a, String.class, result -> {
                            if (result.isSuccess()) {
                                Log.v("EXAMPLE", "Sum value: " + result.get());
                                if (result.get().equals("false")){
                                    sendPhoneOtp();
                                    Log.v("EXAMPLE", "CHCEK");
                                }
                                else{
                                    Intent intent=new Intent(getContext(), Sign_in_Activity.class);
                                    startActivity(intent);
                                }
                            } else {
                                Log.e("EXAMPLE", "failed to call sum function with: " + result.getError());
                            }
                        });
                    } else {
                        Log.e("EXAMPLE", "Error logging into the Realm app. Make sure that anonymous authentication is enabled. Error: " + it.getError());
                    }
                });
//                    Intent intenti=new Intent(getContext(), OtpVerification_Activity.class);
//                    startActivity(intenti);
            }

            private void sendPhoneOtp() {
                String phoneNumber = cpp.getFullNumber();
                System.out.println(phoneNumber);
                Realm.init(getContext());

                App app1 = new App(new AppConfiguration.Builder(Appid1).build());

                Credentials credentials = Credentials.anonymous();
                app1.loginAsync(credentials, it -> {
                    if (it.isSuccess()) {
                        Log.v("User","Logged In Anonymously");
                        User user = app1.currentUser();
                        assert user != null;
                        Functions functionsManager = app1.getFunctions(user);
                        List<String> a = new ArrayList<String>();
                        System.out.println(phoneNumber);
                        System.out.println("*******");
                        a.add(phoneNumber);
                        functionsManager.callFunctionAsync("phoneOtp", a, String.class, result -> {
                            if (result.isSuccess()) {
                                Log.v("EXAMPLE", "Sum value: " + result.get());

                                Intent intent=new Intent(getContext(), OtpVerification_Activity.class);
                                intent.putExtra("phone", phoneNumber);
                                startActivity(intent);

                                //Redirect otp
                            } else {
                                Log.e("EXAMPLE", "failed to call sum function with: " + result.getError());
                            }
                        });
                    } else {
                        Log.e("EXAMPLE", "Error logging into the Realm app. Make sure that anonymous authentication is enabled. Error: " + it.getError());
                    }
                });
            }

        });



        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Sign_in_Activity.class);
                startActivity(intent);
            }
        });
        inputChange();


/*
        login=view.findViewById(R.id.Signin);

        Signup_Button=view.findViewById(R.id.phone_signup_button);

        Signup_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), UsernameActivity.class);
                startActivity(intent);

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Sign_in_Activity.class);
                startActivity(intent);

            }
        });
*/

        return view;
    }




    @SuppressLint("ResourceType")
    private void checkAllData() {
        String userphone = phone.getText().toString();
        System.out.println(userphone);
        if ((!userphone.isEmpty())) {
            isphoneClickable = true;
            tvColor.setTextColor(Color.WHITE);
            Signup_Button.setCardBackgroundColor(Color.parseColor(getString(R.color.pink)));
        } else {
            isphoneClickable = false;
            Signup_Button.setCardBackgroundColor(Color.parseColor(getString(R.color.light_gray)));
        }
    }


    private void inputChange() {
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkAllData();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
