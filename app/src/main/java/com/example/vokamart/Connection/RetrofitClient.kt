package com.example.vokamart.Connection

import com.example.vokamart.Connection.Interface.API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitClient {

    private const val BASE_URL = "https://vok4mart.000webhostapp.com/"

    val instance : API by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(API:: class.java)
    }

}