package com.example.vokamart.RetrofitClient;

import com.example.vokamart.API.ProductApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProduct {

    private static final String BASE_URL = "https://vok4mart.000webhostapp.com/";
    private static Retrofit retrofit= null;

    public static ProductApi getRetrofitClient(){

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ProductApi.class);
    }
}
