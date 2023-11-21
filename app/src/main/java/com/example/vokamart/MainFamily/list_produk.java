package com.example.vokamart.MainFamily;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.vokamart.Adapter.ProductAdapter;
import com.example.vokamart.Models.produk;
import com.example.vokamart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class list_produk extends Fragment {
    private ProductAdapter adapter;
    private ArrayList<produk> produkArrayList;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main_list_produk, container, false);

        recyclerView = rootView.findViewById(R.id.recycler_list_produk);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        produkArrayList = new ArrayList<>();

        if (getContext() != null) {
            requestQueue = Volley.newRequestQueue(getContext());
            parseJSON(); // Panggil method parseJSON jika konteks tidak null
        } else {
            Toast.makeText(getContext(), "Konteks null", Toast.LENGTH_SHORT).show();
        }

        ImageView imageView = rootView.findViewById(R.id.plus);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), tambah_produk.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    private void parseJSON() {
        String url = "https://vok4mart.000webhostapp.com/TestApiProduct.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response != null) {
                                adapter = new ProductAdapter(getContext(), produkArrayList);
                                recyclerView.setAdapter(adapter);
                                JSONArray jsonArray = response.getJSONArray("result");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject hit = jsonArray.getJSONObject(i);

                                    String nama = hit.getString("Nama_produk");
                                    int harga = hit.getInt("Harga_produk");
                                    int stok = hit.getInt("berat");
                                    String deskripsiProduk = hit.getString("deskripsi_produk");

                                    produkArrayList.add(new produk(nama, harga, stok, deskripsiProduk));
                                }

                                adapter = new ProductAdapter(getContext(), produkArrayList);
                                recyclerView.setAdapter(adapter);

                                // Simpan data terakhir dari loop
                                if (!produkArrayList.isEmpty()) {
                                    SharedPreferences preferences = getContext().getSharedPreferences("detail", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("nama_produk", produkArrayList.get(produkArrayList.size() - 1).getNama());
                                    editor.putInt("harga_produk", produkArrayList.get(produkArrayList.size() - 1).getHarga());
                                    editor.putInt("berat", produkArrayList.get(produkArrayList.size() - 1).getStok());
                                    editor.putString("deskripsi_produk", produkArrayList.get(produkArrayList.size() - 1).getDeskripsi());
                                    editor.apply();
                                }
                            } else {
                                // Penanganan respons null
                                Toast.makeText(getContext(), "Respons null", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }
}