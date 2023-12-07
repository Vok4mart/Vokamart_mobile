package com.example.vokamart.DetailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vokamart.Models.MDikirim;
import com.example.vokamart.R;

public class DetailPesananDikirim extends AppCompatActivity {
    Intent intent;
    private MDikirim Dikirim;
    TextView namaPembeli, noHp, Alamat, totalHarga, Ongkir, JumlahTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan_dikirim);

        namaPembeli = findViewById(R.id.nama_pembeli_dikirim);
        noHp = findViewById(R.id.no_hp_dikirim);
        Alamat = findViewById(R.id.alamat_dikirim);
        totalHarga = findViewById(R.id.rupiah_total_dikirim);
        Ongkir = findViewById(R.id.rupiah_ongkir_dikirim);
        JumlahTotal = findViewById(R.id.rupiah_total_dikirim);

        intent = getIntent();
        Dikirim = (MDikirim) intent.getSerializableExtra("dataPesananBaru");
    }
}
