package com.example.vokamart.DetailActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.vokamart.Models.MPesananBaru;
import com.example.vokamart.R;

public class DetailPesananBaru extends AppCompatActivity {
    Intent intent;
    private MPesananBaru MPesananBaru;
    TextView namaPembeli, noHp, Alamat, totalHarga, Ongkir, JumlahTotal;
    Button TolakPesanan, TerimaPesanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan_baru);

        namaPembeli = findViewById(R.id.nama_pembeli_baru);
        noHp = findViewById(R.id.no_hp_baru);
        Alamat = findViewById(R.id.alamat_baru);
        totalHarga = findViewById(R.id.rupiah_total_baru);
        Ongkir = findViewById(R.id.rupiah_ongkir_baru);
        JumlahTotal = findViewById(R.id.jumlah_total_baru);
        TolakPesanan = findViewById(R.id.btn_tolak_pesanan_baru);
        TerimaPesanan = findViewById(R.id.btn_terima_pesanan_baru);

        intent = getIntent();
        MPesananBaru = (MPesananBaru) intent.getSerializableExtra("dataPesananBaru");
    }
}