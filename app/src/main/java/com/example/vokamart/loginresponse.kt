package com.example.vokamart

import com.google.gson.annotations.SerializedName

data class loginresponse (

    @SerializedName("id_akun") val id_akun: String,
    @SerializedName("email_akun") val email_akun: String,
    @SerializedName("password") val password: String,
    @SerializedName("user_level") val user_level: String,
    @SerializedName("nama_akun") val nama_akun: String,

    )