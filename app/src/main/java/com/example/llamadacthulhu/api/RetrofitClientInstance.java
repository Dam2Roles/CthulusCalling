package com.example.llamadacthulhu.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static  String BASE_URL="http://c08b0fe4c757.ngrok.io/api/servicios/";
    private static Retrofit retrofit;
    private static Gson gson;

    public static Retrofit getRetrofitInstance(){
        if(retrofit == null){
            gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
