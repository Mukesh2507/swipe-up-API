package com.example.swipeup_frontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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

public class BirthdayActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog1;
    private Button dateButton;

    EditText pick;
    DatePickerDialog.OnDateSetListener listener;

    private TextView tvColor;
    private CardView btnBirthdate;
    private boolean isdateClickable = false;

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Toast.makeText(BirthdayActivity.this,"There is no back action",Toast.LENGTH_LONG).show();
        return;
    }
    String Appid = "swipeup_registration-qzrzs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);


        pick = findViewById(R.id.textview);
        btnBirthdate=findViewById(R.id.Birthdate_button);
        tvColor =findViewById(R.id.tvColor_page);

        pick = findViewById(R.id.textview);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatePickerDialog datePickerDialog = new DatePickerDialog(BirthdayActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth, listener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });


        listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                pick.setText(date);
            }
        };
        inputChange();
        System.out.println(pick.getText().toString());
        btnBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(pick.getText().toString());
                String birthdatee = pick.getText().toString();


                Intent i = getIntent();
                String semail = i.getStringExtra("email");
                String sphone = i.getStringExtra("phone");


                if(sphone == null)
                {
                    Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().birthdate(semail,birthdatee);

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
                                    Intent intent = new Intent(getApplicationContext(), PasswordActivity.class);
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
                    Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().birthdate(sphone, birthdatee);

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
                                    Intent intent = new Intent(getApplicationContext(), PasswordActivity.class);
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
//                        Intent i = getIntent();
//                        String semail = i.getStringExtra("email");
//                        String sphone = i.getStringExtra("phone");
//
//                        if (sphone == null) {
//
//                            a.add(semail);
//                            a.add(pick.getText().toString());
//                            functionsManager.callFunctionAsync("birthdate", a, String.class, result -> {
//                                if (result.isSuccess()) {
//                                    Log.v("EXAMPLE", "Sum value: " + result.get());
//                                    if (result.get().equals("pagename exists!")) {
//                                        //Toast Message
//                                        Toast.makeText(getApplicationContext(), "pagename exists!", Toast.LENGTH_LONG).show();
//                                    } else {
//                                        //Intent from this actvity to Choose Birthdate Activity with email in intent
//                                        Intent intent = new Intent(getApplicationContext(), PasswordActivity.class);
//                                        intent.putExtra("email", semail);
//                                        startActivity(intent);
//
//                                    }
//                                } else {
//                                    Log.e("EXAMPLE", "failed to call sum function with: " + result.getError());
//                                }
//                            });
//
//                        }else{
//
//                            a.add(sphone);
//                            a.add(pick.getText().toString());
//                            functionsManager.callFunctionAsync("birthdatephone", a, String.class, result -> {
//                                if (result.isSuccess()) {
//                                    Log.v("EXAMPLE", "Sum value: " + result.get());
//                                    if (result.get().equals("pagename exists!")) {
//                                        //Toast Message
//                                        Toast.makeText(getApplicationContext(), "pagename exists!", Toast.LENGTH_LONG).show();
//                                    } else {
//                                        //Intent from this actvity to Choose Birthdate Activity with email in intent
//                                        Intent intent = new Intent(getApplicationContext(), PasswordActivity.class);
//                                        intent.putExtra("phone", sphone);
//                                        startActivity(intent);
//
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
        });

    }
    @SuppressLint("ResourceType")
    private void checkAllData() {
        String username = pick.getText().toString();
        if ((!username.isEmpty())) {
            isdateClickable = true;
            tvColor.setTextColor(Color.WHITE);
            btnBirthdate.setCardBackgroundColor(Color.parseColor(getString(R.color.pink)));
        } else {
            isdateClickable = false;
            btnBirthdate.setCardBackgroundColor(Color.parseColor(getString(R.color.light_gray)));
        }
    }

    private void inputChange() {
        pick.addTextChangedListener(new TextWatcher() {
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