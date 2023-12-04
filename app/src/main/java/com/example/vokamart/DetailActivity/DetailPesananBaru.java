package com.example.vokamart.DetailActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.vokamart.Adapter.DetailPesananBaruAdapter; // Ubah nama adapter
import com.example.vokamart.Models.DetailPesanan;
import com.example.vokamart.Models.MPesananBaru;
import com.example.vokamart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailPesananBaru extends AppCompatActivity {
    Intent intent;
    private DetailPesananBaruAdapter adapter; // Ubah nama adapter
    private ArrayList<DetailPesanan> detailPesanan;
    private RecyclerView recyclerView;
    private MPesananBaru MPesananBaru;
    TextView namaPembeli, noHp, Alamat, totalHarga, Ongkir, JumlahTotal;
    Button TolakPesanan, TerimaPesanan;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan_baru);

        TolakPesanan = findViewById(R.id.btn_tolak_pesanan_baru);
        TerimaPesanan = findViewById(R.id.btn_terima_pesanan_baru);

        recyclerView = findViewById(R.id.recyler_detail_pesanan_baru);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        detailPesanan = new ArrayList<>();
        adapter = new DetailPesananBaruAdapter(detailPesanan); // Ubah nama adapter
        recyclerView.setAdapter(adapter);

        requestQueue = Volley.newRequestQueue(this);

        // Memanggil metode untuk parsing JSON (pastikan metode ini telah didefinisikan)
        parseJSON();

        intent = getIntent();
        MPesananBaru = (MPesananBaru) intent.getSerializableExtra("dataPesananBaru");
    }

    // Tambahkan metode parseJSON sesuai kebutuhan aplikasi Anda
    private void parseJSON() {
        String url = "https://vok4mart.000webhostapp.com/ApiPesananBaru.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Clear existing data in pesananArrayList

                            // Parse pesanan data
                            if (response != null) {
                                JSONArray jsonArray = response.getJSONArray("data");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject hit = jsonArray.getJSONObject(i);

                                    // Create and add data to pesananArrayList
                                    String imgPesananBaru = hit.getString("gbr_produk");
                                    String alamat = hit.getString("alamat_lengkap");
                                    String imgPerluDikirim = null;
                                    String imgDikirim = null;
                                    String imgSelesai = null;
                                    detailPesanan.add(new DetailPesanan (imgPesananBaru, imgPerluDikirim, imgDikirim, imgSelesai));
                                    Alamat.append(alamat);
                                }

                                // Notify the adapter that the data has changed
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Handle JSON parsing error here
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                // Handle Volley error here
            }
        });

        // Add the request to the requestQueue
        requestQueue.add(request);
    }
        // Implementasi parsing JSON di sini
        // Misalnya, penggunaan requestQueue untuk mengambil data dari server
}
