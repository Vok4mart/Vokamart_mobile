package com.example.vokamart.DetailActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.vokamart.R;

public class DetailProduk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_detail_produk);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("ProductName");

        initdetailproduk();

        return ;
    }

    private void initdetailproduk() {


    }
}