package com.example.vokamart.DetailActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.vokamart.R;

public class DetailProduk extends AppCompatActivity {
    TextView nama, stok, harga, Deskripsi;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_detail_produk);

        Intent intent = getIntent();
//        String nama = intent.getStringExtra("ProductName");

        initdetailproduk();

        return ;
    }

    private void initdetailproduk() {
        nama = findViewById(R.id.Detail_nama_produk);
        stok = findViewById(R.id.Detail_produk_stok);
        harga = findViewById(R.id.Detail_produk_harga);
        Deskripsi = findViewById(R.id.Detail_produk_deskripsi);

        preferences = getApplicationContext().getSharedPreferences("detail", Context.MODE_PRIVATE);

        String nameProduk = preferences.getString("nama_produk", "-");
        nama.setText((nameProduk));

        String deskripsiProduk = preferences.getString("deskripsi_produk", "-");
        Deskripsi.setText((deskripsiProduk));

        int hargaProduk = preferences.getInt("harga_produk", Integer.parseInt("-1"));
        harga.setText(String.valueOf(hargaProduk));

        int beratProduk = preferences.getInt("berat", Integer.parseInt("-1"));
        stok.setText(String.valueOf(beratProduk));
    }
}