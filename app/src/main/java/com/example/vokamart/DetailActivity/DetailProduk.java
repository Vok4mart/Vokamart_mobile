package com.example.vokamart.DetailActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vokamart.Models.produk;
import com.example.vokamart.R;

public class DetailProduk extends AppCompatActivity {
    TextView nama, stok, harga, Deskripsi;
    ImageView image;
    private produk Produk;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_detail_produk);

        nama = findViewById(R.id.Detail_nama_produk);
        stok = findViewById(R.id.Detail_produk_stok);
        harga = findViewById(R.id.Detail_produk_harga);
        Deskripsi = findViewById(R.id.Detail_produk_deskripsi);
        image = findViewById(R.id.card_photo);

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

                String imageUrl = Produk.getMimageUrl();

                Glide.with(getApplicationContext())
                        .load(imageUrl)
                        .placeholder(R.drawable.baseline_fastfood_24) // Optional placeholder image
                        .into(image);

                int beratProduk = Produk.getStok();
                stok.setText(String.valueOf(beratProduk));
            }
        }
    }
}