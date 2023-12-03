package com.example.vokamart.DetailActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.vokamart.Models.MPesananBaru;
import com.example.vokamart.R;

public class DetailPesananBaru extends AppCompatActivity {
    Intent intent;
    private MPesananBaru MPesananBaru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan_detail_pesanan_baru);

        intent = getIntent();
        MPesananBaru = (MPesananBaru) intent.getSerializableExtra("dataPesananBaru");
    }
}