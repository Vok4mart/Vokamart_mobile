package com.example.vokamart

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofit_client {

    private const val BASE_URL = "https://vok4mart.000webhostapp.com/"

    val instance : api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(api:: class.java)
    }

}