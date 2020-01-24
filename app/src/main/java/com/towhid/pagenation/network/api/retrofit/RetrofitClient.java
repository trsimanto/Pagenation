package com.towhid.pagenation.network.api.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://192.168.10.149:8080";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient() {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.MINUTES)
                .writeTimeout(30, TimeUnit.MINUTES)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }
    public Retrofit getRetrofit() {
        return retrofit;
    }

}