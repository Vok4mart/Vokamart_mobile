package com.example.vokamart

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface api {

    @POST("ApiLogin")
    fun post(
        @Body body: userpostrequest
    ): Call<apiResponse>
}