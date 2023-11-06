package com.example.vokamart.Connection.Interface

import com.example.vokamart.Connection.ResponsePackage.APIResponse
import com.example.vokamart.Connection.UserPostRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface API {

    @POST("ApiLogin")
    fun post(
        @Body body: UserPostRequest
    ): Call<APIResponse>
}