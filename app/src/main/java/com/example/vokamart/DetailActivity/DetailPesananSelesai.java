package com.example.vokamart.DetailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vokamart.Models.MSelesai;
import com.example.vokamart.R;

public class DetailPesananSelesai extends AppCompatActivity {

    Intent intent;
    TextView namaPembeli, noHp, Alamat, totalHarga, Ongkir, JumlahTotal;
    private MSelesai pesananSelesai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan_selesai);

//        namaPembeli = findViewById(R.id.nama);
        noHp = findViewById(R.id.no_hp_selesai);
        Alamat = findViewById(R.id.alamat_selesai);
        totalHarga = findViewById(R.id.total_rupiah_selesai);
        Ongkir = findViewById(R.id.ongkir_selesai);
        JumlahTotal = findViewById(R.id.jumlah_total_harga_selesai);

        intent = getIntent();
        pesananSelesai = (MSelesai) intent.getSerializableExtra("dataPesananSelesai");
    }
}
