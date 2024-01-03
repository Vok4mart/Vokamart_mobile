package com.example.vokamart.DetailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vokamart.Models.MPerluDikirim;
import com.example.vokamart.PesananFragment.PerluDikirim;
import com.example.vokamart.R;

import java.util.HashMap;
import java.util.Map;

public class DetailPesananPerluDikirim extends AppCompatActivity {

    Intent intent;
    private MPerluDikirim perluDikirim;
    TextView namaPembeli, noHp, alamat, totalHarga, ongkir;
    EditText resi;
    Button kirimResi;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan_perlu_dikirim);

        namaPembeli = findViewById(R.id.nama_pembeli_perlu_dikirim);
        noHp = findViewById(R.id.no_hp_perlu_dikirim);
        alamat = findViewById(R.id.alamat_perlu_dikirim);
        totalHarga = findViewById(R.id.jumlah_rupiah_perlu_dikirim);
        ongkir = findViewById(R.id.rupiah_ongkir_perlu_dikirim);
        resi = findViewById(R.id.No_resi);
        kirimResi = findViewById(R.id.btn_kirim_resi);

        intent = getIntent();
        if (intent != null) {
            perluDikirim = (MPerluDikirim) intent.getSerializableExtra("dataPesananPerluDikirim");
            if (perluDikirim != null) {
                String id = perluDikirim.getIdPesanan();

                kirimResi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!TextUtils.isEmpty(id)) {
                            Toast.makeText(DetailPesananPerluDikirim.this, "Produk Berhasil di update", Toast.LENGTH_SHORT).show();
                            update(id);
                        } else {
                            showToast("ID Pesanan tidak valid");
                        }
                    }

                    private void update(String id) {
                        RequestQueue queue = Volley.newRequestQueue(DetailPesananPerluDikirim.this);
                        String url = "https://vok4mart.000webhostapp.com/UpdatePesananPerluDikirimApi.php";

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Toast.makeText(DetailPesananPerluDikirim.this, response, Toast.LENGTH_SHORT).show();
                                        navigateToPesananBaruFragment();
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(DetailPesananPerluDikirim.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<>();
                                params.put("id_pesanan", id);
                                params.put("no_resi", resi.getText().toString()); // Corrected usage of resi
                                return params;
                            }
                        };

                        queue.add(stringRequest);
                    }

                    private void navigateToPesananBaruFragment() {
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.recycler_pesanan, new PerluDikirim());
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });
            }
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}