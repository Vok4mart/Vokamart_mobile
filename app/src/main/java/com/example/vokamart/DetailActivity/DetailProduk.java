package com.example.vokamart.DetailActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.vokamart.Models.produk;
import com.example.vokamart.R;

import java.util.ArrayList;

public class DetailProduk extends AppCompatActivity {
    TextView nama, stok, harga, Deskripsi;
    private produk Produk;
    Intent intent;
    private SharedPreferences preferences;
    private ArrayList<produk> produkArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_detail_produk);

        nama = findViewById(R.id.Detail_nama_produk);
        stok = findViewById(R.id.Detail_produk_stok);
        harga = findViewById(R.id.Detail_produk_harga);
        Deskripsi = findViewById(R.id.Detail_produk_deskripsi);

        // Get the product from the intent
        intent = getIntent();
        if (intent != null) {
            Produk = (produk) intent.getSerializableExtra("data");
            if (Produk != null) {
                // Display the name of the product
                String namaProduk = Produk.getNama();
                nama.setText(namaProduk);

                // Display other details using Produk directly
                String deskripsiProduk = Produk.getDeskripsi_produk();
                Deskripsi.setText(deskripsiProduk);

                int hargaProduk = Produk.getHarga();
                harga.setText(String.valueOf(hargaProduk));

                int beratProduk = Produk.getStok();
                stok.setText(String.valueOf(beratProduk));
            }
        }
    }
}