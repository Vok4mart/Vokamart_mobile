package com.example.vokamart.Connection.ResponsePackage

import com.google.gson.annotations.SerializedName

class APIResponse (

    @SerializedName("code") val code: Int,
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: List<LoginResponse>

)


