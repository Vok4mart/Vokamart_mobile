package com.example.vokamart.API;

import com.example.vokamart.Models.produk;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductApi {

    @GET("TestApiProduct.php")
    Call<List<produk>> getproduk();
}
