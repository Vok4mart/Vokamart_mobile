package com.example.vokamart.DetailActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.vokamart.LoginFamily.Login;
import com.example.vokamart.MainFamily.UpdateProdukActivity;
import com.example.vokamart.MainFamily.list_produk;
import com.example.vokamart.Models.produk;
import com.example.vokamart.Navbar;
import com.example.vokamart.R;

import java.util.HashMap;
import java.util.Map;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class DetailProduk extends AppCompatActivity {
    TextView nama, stok, harga, Deskripsi;
    ImageView image;
    private produk Produk;
    Intent intent;
    Button btnHps, btnEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_detail_produk);

        nama = findViewById(R.id.Detail_nama_produk);
        stok = findViewById(R.id.Detail_produk_stok);
        harga = findViewById(R.id.Detail_produk_harga);
        Deskripsi = findViewById(R.id.Detail_produk_deskripsi);
        image = findViewById(R.id.card_photo);
        btnHps = findViewById(R.id.hapus_btn);
        btnEdt = findViewById(R.id.edit_btn);

        SharedPreferences sharedPreferences = getSharedPreferences("detail", MODE_PRIVATE);
        String productId = sharedPreferences.getString("id_produk", "");

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

                String idProduk = Produk.getId();

                // Set click listener for delete button
                btnHps.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Check if the product ID is not empty
                        if (!TextUtils.isEmpty(idProduk)) {
                            // Call the method to delete the product with the specified ID
                            Toast.makeText(DetailProduk.this, "Produk Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                            deleteProduct(idProduk);
                            startActivity(new Intent(DetailProduk.this, list_produk.class));
                        } else {
                            // Handle the case where the product ID is not available
                            Toast.makeText(DetailProduk.this, "Product ID not found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                btnEdt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        produk produkObject = new produk(
                                idProduk,
                                Produk.getNama(),
                                Produk.getHarga(),
                                Produk.getStok(),
                                Produk.getDeskripsi_produk(),
                                Produk.getMimageUrl(),
                                Produk.getBerat(),
                                Produk.getId_katergori(),
                                Produk.getNama_kategori()
                        );

                        Intent i = new Intent(DetailProduk.this, UpdateProdukActivity.class);
                        i.putExtra("data", produkObject);  // Assuming produk is a Parcelable or Serializable object
                        startActivity(i);
                    }
            });
            }
        }
    }

    private void deleteProduct(String productId) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://vok4mart.000webhostapp.com/DeleteProdukAPI.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the response, e.g., show a toast
                        Toast.makeText(DetailProduk.this, response, Toast.LENGTH_SHORT).show();
                        // Add logic to navigate back to the previous screen or perform other actions
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
                Toast.makeText(DetailProduk.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            // Override getParams() to include parameters in the request
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_produk", productId);
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void navigateToListProdukFragment() {
        // Create a new instance of the ListProduk fragment
        Fragment listProdukFragment = new list_produk();

        // Get the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Begin the FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the existing fragment (if any) with the new ListProdukFragment
        fragmentTransaction.replace(R.id.nav_view, listProdukFragment);

        // Add the transaction to the back stack (optional)
        fragmentTransaction.addToBackStack(null);

        // Commit the transaction
        fragmentTransaction.commit();
    }
}
