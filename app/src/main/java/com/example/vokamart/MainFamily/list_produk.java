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

import androidx.appcompat.widget.SearchView;
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
import com.example.vokamart.DetailActivity.DetailProduk;
import com.example.vokamart.Models.produk;
import com.example.vokamart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class list_produk extends Fragment implements ProductAdapter.ClickListener {
    private ProductAdapter adapter;
    private ArrayList<produk> produkArrayList;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private View rootView;
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main_list_produk, container, false);

        searchView = rootView.findViewById(R.id.cari);

        recyclerView = rootView.findViewById(R.id.recycler_list_produk);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        produkArrayList = new ArrayList<>();

        if (isAdded() && getContext() != null) {
            requestQueue = Volley.newRequestQueue(getContext());
            // Inisialisasi adapter hanya satu kali pada saat inisialisasi
            adapter = new ProductAdapter(getContext(), produkArrayList, this::clicked);
            recyclerView.setAdapter(adapter);

            parseJSON();
        } else {
            Toast.makeText(getContext(), "Konteks null atau fragmen tidak terkait dengan aktivitas", Toast.LENGTH_SHORT).show();
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterlist(newText);
                return true;
            }
        });

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

    private void filterlist(String newText) {
        List<produk> filteredList = new ArrayList<>();
        for (produk item : produkArrayList){
            if (item.getNama().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(item);
            }
        }
        adapter.filterlist(filteredList);
    }

    private void parseJSON() {
        try {
            if (getContext() != null) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("detail", Context.MODE_PRIVATE);

                String url = "https://vok4mart.000webhostapp.com/ListProductApi.php";

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    if (response != null) {
                                        JSONArray jsonArray = response.getJSONArray("result");

                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject hit = jsonArray.getJSONObject(i);

                                            String nama = hit.getString("nama_produk");
                                            int harga = hit.getInt("harga_produk");
                                            int stok = hit.getInt("stok");
                                            String deskripsiProduk = hit.getString("deskripsi_produk");
                                            String imageUrl = hit.getString("gbr_produk");

                                            produkArrayList.add(new produk(nama, harga, stok, deskripsiProduk, imageUrl));
                                        }

                                        // Setelah mendapatkan data, panggil notifyDataSetChanged() pada adapter
                                        adapter.notifyDataSetChanged();

                                        // Simpan data terakhir dari loop
                                        if (!produkArrayList.isEmpty()) {
                                            int lastIndex = produkArrayList.size() - 1;
                                            produk lastProduk = produkArrayList.get(lastIndex);

                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("nama_produk", lastProduk.getNama());
                                            editor.putInt("harga_produk", lastProduk.getHarga());
                                            editor.putInt("berat", lastProduk.getStok());
                                            editor.putString("deskripsi_produk", lastProduk.getDeskripsi_produk());
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
            } else {
                Toast.makeText(getContext(), "Konteks null", Toast.LENGTH_SHORT).show();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Kesalahan: Konteks null saat pemanggilan getSharedPreferences()", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void clicked(produk produk) {
        Intent intent = new Intent(getActivity(), DetailProduk.class);
        intent.putExtra("data", produk);
        startActivity(intent);
    }
}