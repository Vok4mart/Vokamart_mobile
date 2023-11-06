package com.example.vokamart

import com.google.gson.annotations.SerializedName

class apiResponse (

    @SerializedName("code") val code: Int,
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: List<loginresponse>
)