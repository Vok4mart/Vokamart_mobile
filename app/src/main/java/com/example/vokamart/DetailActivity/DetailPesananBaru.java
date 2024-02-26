package com.example.vokamart.DetailActivity;

// Menggunakan import yang sesuai
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vokamart.Adapter.DetailPesananBaruAdapter;
import com.example.vokamart.MainFamily.pesanan;
import com.example.vokamart.Models.DetailPesanan;
import com.example.vokamart.Models.MPesananBaru;
import com.example.vokamart.PesananFragment.FPesananBaru;
import com.example.vokamart.PesananFragment.PerluDikirim;
import com.example.vokamart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailPesananBaru extends AppCompatActivity {
    private DetailPesananBaruAdapter adapter;
    private ArrayList<DetailPesanan> detailPesanan;
    private RecyclerView recyclerView;
    private MPesananBaru MPesananBaru;
    TextView namaPembeli, noHp, Alamat;
    private RequestQueue requestQueue;
    Intent intent;
    Button btnTerima, btnTolak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan_baru);

        namaPembeli = findViewById(R.id.nama_pembeli_baru);
        noHp = findViewById(R.id.no_hp_baru);
        Alamat = findViewById(R.id.alamat_baru);
        btnTerima = findViewById(R.id.btn_terima_pesanan_baru);
        btnTolak = findViewById(R.id.btn_tolak_pesanan_baru);

        // Menghilangkan penggunaan SharedPreferences yang tidak diperlukan
        // String pesananid = sharedPreferences.getString("id_pesanan", "");

        intent = getIntent();
        if (intent != null) {
            MPesananBaru = (MPesananBaru) intent.getSerializableExtra("dataPesananBaru");
            if (MPesananBaru != null) {
                String id = MPesananBaru.getIdPesanan();
                // Tidak diperlukan lagi karena penggunaan SharedPreferences dihilangkan

                btnTerima.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!TextUtils.isEmpty(id)) {
                            Toast.makeText(DetailPesananBaru.this, "pesanan Berhasil di update", Toast.LENGTH_SHORT).show();
                            update(id);
                        } else {
                            showToast("ID Pesanan tidak valid");
                        }
                    }

                    private void update(String id) {
                        // Menggunakan this sebagai Context karena berada di dalam AppCompatActivity
                        RequestQueue queue = Volley.newRequestQueue(DetailPesananBaru.this);
                        String url = "https://vok4mart.000webhostapp.com/Api_mobile/UpdatePesananBaru.php";

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Toast.makeText(DetailPesananBaru.this, response, Toast.LENGTH_SHORT).show();

                                        navigateToPesananBaruFragment();
                                    }

                                    private void navigateToPesananBaruFragment() {
                                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                        transaction.replace(R.id.recycler_pesanan, new pesanan());
                                        transaction.addToBackStack(null);
                                        transaction.commit();
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(DetailPesananBaru.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<>();
                                params.put("id_pesanan", id);
                                return params;
                            }
                        };

                        queue.add(stringRequest);
                    }
                });

            btnTolak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(id)) {
                        Toast.makeText(DetailPesananBaru.this, "pesana Berhasil dibatalkan", Toast.LENGTH_SHORT).show();
                        tolak(id);
                    } else {
                        showToast("ID Pesanan tidak valid");
                    }
                }

                private void tolak(String id) {
                    // Menggunakan this sebagai Context karena berada di dalam AppCompatActivity
                    RequestQueue queue = Volley.newRequestQueue(DetailPesananBaru.this);
                    String url = "https://vok4mart.000webhostapp.com/Api_mobile/HapusPesanan.php";

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(DetailPesananBaru.this, response, Toast.LENGTH_SHORT).show();

                                    navigateToPesananBaruFragment();
                                }

                                private void navigateToPesananBaruFragment() {
                                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                    transaction.replace(R.id.recycler_pesanan, new pesanan());
                                    transaction.addToBackStack(null);
                                    transaction.commit();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(DetailPesananBaru.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("id_pesanan", id);
                            return params;
                        }
                    };

                    queue.add(stringRequest);
                }
            });
            }
        }

        recyclerView = findViewById(R.id.recyler_detail_pesanan_baru);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        detailPesanan = new ArrayList<>();
        adapter = new DetailPesananBaruAdapter(detailPesanan);
        recyclerView.setAdapter(adapter);

        requestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON() {
        String url = "https://vok4mart.000webhostapp.com/Api_mobile/ApiPesananBaru.php?id_pesanan=" + MPesananBaru.getIdPesanan();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response != null) {
                                // Bersihkan data yang sudah ada di detailPesanan
                                detailPesanan.clear();

                                JSONArray jsonArray = response.getJSONArray("data");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject hit = jsonArray.getJSONObject(i);

                                    String imgPesananBaru = hit.getString("gbr_produk");
                                    String alamat = hit.getString("alamat_lengkap");
                                    String imgPerluDikirim = null;
                                    String imgDikirim = null;
                                    String imgSelesai = null;
                                    detailPesanan.add(new DetailPesanan(imgPesananBaru, imgPerluDikirim, imgDikirim, imgSelesai));
                                    Alamat.append(alamat);
                                }

                                // Beritahu adapter bahwa data telah berubah
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        requestQueue.add(request);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}