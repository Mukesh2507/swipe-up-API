package com.example.swipeup_frontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sign_in_Activity extends AppCompatActivity {
private TextView resetpassword;
    private TextView Sign_up, t31;
    private EditText ex_phone,ex_password;
    private TextView tvColor;
    private CardView btnsign_in;
    private boolean issign_inClickable = false;
    String Appid="swipeup_registration-qzrzs";


    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";
    // key for storing email.
//    public static final String EMAIL_KEY = "email_key";
    SharedPreferences sharedpreferences;
//    SharedPrefManager sharedPrefManager;
    String password;
    Boolean email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_);
        resetpassword=findViewById(R.id.resetpassword);

        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Reset_password_Activity.class);
                startActivity(intent);
            }
        });

        ex_phone=findViewById(R.id.phone);
        ex_password=findViewById(R.id.password);

        Sign_up = findViewById(R.id.signup);
        t31 = findViewById(R.id.t3);
        ex_phone=findViewById(R.id.phone);
        ex_password=findViewById(R.id.password);
        btnsign_in=findViewById(R.id.Signin_button);
        tvColor = findViewById(R.id.tvColor_signin);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        email = sharedpreferences.getBoolean("logged", false);
//        password = sharedpreferences.getString(PASSWORD_KEY, null);

//        sharedPrefManager = new SharedPrefManager(getApplicationContext());

        btnsign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = ex_phone.getText().toString();
                String password = ex_password.getText().toString();
                System.out.println(phone);
                System.out.println(password);
                System.out.println("***");


                Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().signIn(phone, password);

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
                                profile_model check1 = response.body().getXyz();
                                System.out.println(check1.get_Id());
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString("id", check1.get_Id());
                                System.out.println(check1.get_Id());
                                editor.putString("username", check1.getUsername());
                                System.out.println(check1.getUsername());
                                editor.putString("name", check1.getName());
                                System.out.println(check1.getName());
                                editor.putString("img_url", check1.getImg_url());
                                System.out.println(check1.getImg_url());
                                editor.putString("website", check1.getWebsite());
                                System.out.println(check1.getWebsite());
                                editor.putString("bio", check1.getBio());
                                System.out.println(check1.getBio());
                                editor.putString("email", check1.getEmail());
                                System.out.println(check1.getEmail());
                                editor.putString("phone", check1.getPhone());
                                System.out.println(check1.getPhone());
                                editor.putString("birthdate", check1.getBirthdate());
                                System.out.println(check1.getBirthdate());
                                editor.putString("pagename", check1.getPagename());
                                System.out.println(check1.getPagename());
                                editor.putBoolean("logged", true);
                                editor.apply();
//                                sharedpreferences.getString("id", null);
//        password = sharedpreferences.getString(PASSWORD_KEY, null);
//                                sharedPrefManager.saveUser(check1);
                                Intent intent = new Intent(getApplicationContext(), bottom_navigation_menu.class);
//                                intent.putExtra("email", semail);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
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













//                Realm.init(getApplicationContext());
//                App app=new App(new AppConfiguration.Builder(Appid).build());
//                Credentials credentials=Credentials.anonymous();
//                app.loginAsync(credentials,it -> {
//                    if (it.isSuccess()){
//                        Log.v("User","as");
//                        User user=app.currentUser();
//                        assert  user!=null;
//                        Functions functionMaager = app.getFunctions(user);
//                        List<String> a = new ArrayList<String>();
//                        a.add(phone);
//                        a.add(password);
//                        functionMaager.callFunctionAsync("login",a,String.class,result ->
//                        {
//                            if (result.isSuccess()){
//                                Log.v("EXAMPLE","sum value:"+result.get());
//                                if (result.get().equals("false")) {
//                                    Toast.makeText(getApplicationContext(), "false", Toast.LENGTH_LONG).show();
//                                } else {
//                                    //  Intent from this actvity to Login Activity with email in intent
//                                    SharedPreferences.Editor editor = sharedpreferences.edit();
//
//                                    editor.putString(EMAIL_KEY, ex_phone.getText().toString());
//                                    editor.apply();
//
//
//                                    Intent intent = new Intent(getApplicationContext(), bottom_navigation_menu.class);
////                            intent.putExtra("email", semail);
//                                    startActivity(intent);
//                                }
//                            }else {
//                                Log.e("EXAMPLE","sum failed:"+result.getError());
//                            }
//                        });
//
//                    }else {
//                        Log.e("EXAMPLE","sum failed:");
//                    }
//
//                });
            }
        });










        Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Sign_in_Activity.this, SignUp_Activity.class);
                startActivity(intent);
            }
        });



        inputChange();

    }

    @SuppressLint("ResourceType")
    private void checkAllData() {
        String phone = ex_phone.getText().toString();
        String password = ex_password.getText().toString();

        if ((!phone.isEmpty() && !password.isEmpty())) {
            issign_inClickable = true;
            tvColor.setTextColor(Color.WHITE);
            btnsign_in.setCardBackgroundColor(Color.parseColor(getString(R.color.pink)));
        } else {
            issign_inClickable = false;
            btnsign_in.setCardBackgroundColor(Color.parseColor(getString(R.color.light_gray)));
        }
    }


    private void inputChange() {
        ex_password.addTextChangedListener(new TextWatcher() {
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

    @Override
    protected void onStart() {
        super.onStart();
//        boolean email = sharedpreferences.getBoolean("logged", false);
        if(email == true){
            System.out.println("logged in");
            Intent intent = new Intent(getApplicationContext(), bottom_navigation_menu.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}