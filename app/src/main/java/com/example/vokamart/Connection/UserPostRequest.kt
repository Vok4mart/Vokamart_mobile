package com.example.vokamart.Connection

import android.provider.ContactsContract.CommonDataKinds.Email
import com.example.vokamart.LoginFamily.Lupa_Password
import com.google.gson.annotations.SerializedName

data class UserPostRequest(
    @SerializedName("email_akun") val email: String,
    @SerializedName("password") val password: String
)
