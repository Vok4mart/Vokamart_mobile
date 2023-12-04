package com.example.vokamart.DetailActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vokamart.Models.MDikirim;
import com.example.vokamart.Models.MPesananBaru;
import com.example.vokamart.R;

public class DetailPesananDikirim extends AppCompatActivity {
    Intent intent;
    private MDikirim Dikirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detai_pesanan_dikirim);

        intent = getIntent();
        Dikirim = (MDikirim) intent.getSerializableExtra("dataPesananBaru");
    }
}
