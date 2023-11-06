package com.example.vokamart

import com.google.gson.annotations.SerializedName

data class userpostrequest(

    @SerializedName("email_akun") val email: String,
    @SerializedName("password") val password: String
)
