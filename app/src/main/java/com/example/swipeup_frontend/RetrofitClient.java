package com.example.swipeup_frontend;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class
RetrofitClient {
   // private static String BASE_URL="http://10.0.2.2:5000";
    private static String BASE_URL="https://swipe-up-rest-api.herokuapp.com";
    private  static RetrofitClient retrofitClient;
    private Retrofit retrofit;


    RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance(){

        if(retrofitClient == null){
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }

}
