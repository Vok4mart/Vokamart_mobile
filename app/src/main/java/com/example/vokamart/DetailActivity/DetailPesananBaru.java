package com.example.vokamart.DetailActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.vokamart.Adapter.DetailPesananBaruAdapter;
import com.example.vokamart.Models.DetailPesanan;
import com.example.vokamart.Models.MPesananBaru;
import com.example.vokamart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailPesananBaru extends AppCompatActivity {
    private DetailPesananBaruAdapter adapter;
    private ArrayList<DetailPesanan> detailPesanan;
    private RecyclerView recyclerView;
    private MPesananBaru MPesananBaru;
    TextView namaPembeli, noHp, Alamat;

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan_baru);

        namaPembeli = findViewById(R.id.nama_pembeli_baru);
        noHp = findViewById(R.id.no_hp_baru);
        Alamat = findViewById(R.id.alamat_baru);

        recyclerView = findViewById(R.id.recyler_detail_pesanan_baru);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        detailPesanan = new ArrayList<>();
        adapter = new DetailPesananBaruAdapter(detailPesanan);
        recyclerView.setAdapter(adapter);

        requestQueue = Volley.newRequestQueue(this);
        parseJSON();

        Intent intent = getIntent();
        MPesananBaru = (MPesananBaru) intent.getSerializableExtra("dataPesananBaru");

        // Other initialization code...

        // Assuming you have a valid id_pesanan, replace "YOUR_ID_PESANAN" with the actual value
        String idPesanan = "ORDR00000001";
        String url = "https://vok4mart.000webhostapp.com/testUpdatePesanan.php";

        JSONObject params = new JSONObject();
        try {
            params.put("id_pesanan", idPesanan);
            params.put("status_pesanan", "Status PerluDikirim");
        } catch (JSONException e) {
            e.printStackTrace();
            showToast("JSON ERROR");
            return;
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params,
                response -> handleResponse(response),
                error -> handleError(error));

        requestQueue.add(request);
    }

    private void parseJSON() {
        String url = "https://vok4mart.000webhostapp.com/ApiPesananBaru.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        if (response != null) {
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

                            adapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> error.printStackTrace());

        requestQueue.add(request);
    }

    private void handleResponse(JSONObject response) {
        // Handle the response from the server
        // You may implement this method based on your requirements
    }

    private void handleError(VolleyError error) {
        // Handle the error from the server
        // You may implement this method based on your requirements
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}