package com.example.vokamart.DetailActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vokamart.Models.MSelesai;
import com.example.vokamart.R;

public class DetailPesananSelesai extends AppCompatActivity {

    Intent intent;
    private MSelesai pesananSelesai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan_selesai);

        intent = getIntent();
        pesananSelesai = (MSelesai) intent.getSerializableExtra("dataPesananSelesai");
    }
}
