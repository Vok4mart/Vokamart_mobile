package com.example.vokamart.PesananFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.vokamart.Adapter.PesananBaru;
import com.example.vokamart.DetailActivity.DetailPesananBaru;
import com.example.vokamart.Models.MPesananBaru;
import com.example.vokamart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FPesananBaru extends Fragment {
    private PesananBaru adapter;
    private ArrayList<MPesananBaru> pesananArrayList;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private View rootView;
    private SearchView search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_pesanan_baru, container, false);

        search = rootView.findViewById(R.id.cari_pesanan);
        recyclerView = rootView.findViewById(R.id.recycler_pesanan);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        pesananArrayList = new ArrayList<>();
        adapter = new PesananBaru(pesananArrayList, getContext(), this::clicked);
        recyclerView.setAdapter(adapter);

        requestQueue = Volley.newRequestQueue(requireContext()); // Using requireContext() instead of getContext() for non-nullable context

        if (getContext() != null) {
            parseJSON();
        } else {
            Toast.makeText(getContext(), "Konteks null", Toast.LENGTH_SHORT).show();
        }

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                NewOrderFilter(newText);
                return false;
            }

            private void NewOrderFilter(String newText) {
                List<MPesananBaru> filterList = new ArrayList<>();
                for (MPesananBaru item : pesananArrayList){
                    if (item.getNama_produk().toLowerCase().contains(newText.toLowerCase())){
                        filterList.add(item);
                    }
                }
                adapter.NewOrderFilter(filterList);
            }
        });

        return rootView;
    }

    private void parseJSON() {
        try {
            SharedPreferences sharedPreferences = requireContext().getSharedPreferences("detail", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            String url = "https://vok4mart.000webhostapp.com/Api_mobile/ApiPesananBaru.php";

            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                // Clear existing data in pesananArrayList
                                pesananArrayList.clear();

                                // Parse pesanan data
                                if (response != null) {
                                    JSONArray jsonArray = response.getJSONArray("data");

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject hit = jsonArray.getJSONObject(i);

                                        String namaProduk = hit.getString("Nama_produk");
                                        String alamatLengkap = hit.getString("alamat_lengkap");
                                        int totalHarga = hit.getInt("sub_total");
                                        String pesananBaruImg = hit.getString("gbr_produk");
                                        String idPesanan = hit.getString("id_pesanan");
                                        String statusPesanan = hit.getString("status_pesanan");

                                        // Create and add data to pesananArrayList
                                        pesananArrayList.add(new MPesananBaru(namaProduk, alamatLengkap, totalHarga, pesananBaruImg, idPesanan, statusPesanan));
                                    }

                                    // Notify the adapter that the data has changed
                                    adapter.notifyDataSetChanged();

                                    if (!pesananArrayList.isEmpty()) {
                                        int lastIndex = pesananArrayList.size() - 1;
                                        MPesananBaru lastPesanan = pesananArrayList.get(lastIndex);

                                        editor.putString("Nama_produk", lastPesanan.getNama_produk());
                                        editor.putString("alamat_lengkap", lastPesanan.getAlamat_lengkap());
                                        editor.putInt("sub_total", lastPesanan.getHarga_produk());
                                        editor.putString("gbr_produk", lastPesanan.getPesananBaruImg());
                                        editor.putString("id_pesanan", lastPesanan.getIdPesanan());
                                        editor.putString("status_pesanan", lastPesanan.getStatusPesanan());

                                        // Apply the changes
                                        editor.apply();
                                    }
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void clicked(MPesananBaru pesananBaru) {
        Intent intent = new Intent(requireActivity(), DetailPesananBaru.class);
        intent.putExtra("dataPesananBaru", pesananBaru);
        startActivity(intent);
    }
}