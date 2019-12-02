package com.gharin.qurandigital.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit getClient() {

        return new Retrofit.Builder()
                .baseUrl("https://al-quran-8d642.firebaseio.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }


    public static BaseApiService getShurah(){
        return getClient().create(BaseApiService.class);
    }

}
