package com.example.vokamart.DetailActivity;



import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vokamart.Models.MPerluDikirim;
import com.example.vokamart.R;

public class DetailPesananPerluDikirim extends AppCompatActivity {

    Intent intent;
    private MPerluDikirim PerluDikirim;
    TextView namaPembeli, noHp, Alamat, totalHarga, Ongkir, JumlahTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan_perlu_dikirim);

        namaPembeli = findViewById(R.id.nama_pembeli_perlu_dikirim);
        noHp = findViewById(R.id.no_hp_perlu_dikirim);
        Alamat = findViewById(R.id.alamat_perlu_dikirim);
        totalHarga = findViewById(R.id.jumlah_rupiah_perlu_dikirim);
        Ongkir = findViewById(R.id.rupiah_ongkir_perlu_dikirim);
//        JumlahTotal = findViewById(R.id.rupiah_total_dikirim);

        intent = getIntent();
        PerluDikirim = (MPerluDikirim) intent.getSerializableExtra("dataPesananPerluDikirim");
    }
}
