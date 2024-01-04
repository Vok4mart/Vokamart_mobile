package com.example.vokamart.PesananFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.vokamart.Adapter.AdapterPesananDikirim;
import com.example.vokamart.Adapter.AdapterPesananSelesai;
import com.example.vokamart.DetailActivity.DetailPesananSelesai;
import com.example.vokamart.Models.MSelesai;
import com.example.vokamart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Selesai extends Fragment {

    private AdapterPesananSelesai adapter;
    private ArrayList<MSelesai> pesananSelesai;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private View rootView;
    private SearchView search4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_pesanan_selesai, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_pesanan);
        search4 = rootView.findViewById(R.id.cari_pesanan_selesai);

        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);

            pesananSelesai = new ArrayList<>();
            adapter = new AdapterPesananSelesai(pesananSelesai, getContext(), this::cliked);
            recyclerView.setAdapter(adapter);

            if (getContext() != null) {
                requestQueue = Volley.newRequestQueue(getContext());
                parseJSON();
            } else {
                Toast.makeText(getContext(), "Konteks null", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "RecyclerView is null", Toast.LENGTH_SHORT).show();
        }

        search4.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                OrderDone(newText);
                return false;
            }

            private void OrderDone(String newText) {
                List<MSelesai> filterList = new ArrayList<>();
                for (MSelesai item : pesananSelesai){
                    if (item.getNama_produk().toLowerCase().contains(newText.toLowerCase())){
                        filterList.add(item);
                    }
                }
                adapter.OrderDone(filterList);
            }
        });

        return rootView;
    }

    private void cliked(MSelesai mSelesai) {
        Intent intent = new Intent(getActivity(), DetailPesananSelesai.class);
        intent.putExtra("dataPesananSelesai", mSelesai);
        startActivity(intent);
    }

    private void parseJSON() {
        String url = "https://vok4mart.000webhostapp.com/ApiPesananSelesai.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Clear existing data in pesananArrayList
                            pesananSelesai.clear();

                            // Parse pesanan data
                            if (response != null) {
                                JSONArray jsonArray = response.getJSONArray("data");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject hit = jsonArray.getJSONObject(i);

                                    String namaProduk = hit.getString("Nama_produk");
                                    String alamatLengkap = hit.getString("alamat_lengkap"); // Ganti dengan nama kolom yang sesuai
                                    int totalHarga = hit.getInt("sub_total");
                                    String gbr = hit.getString("gbr_produk");
                                    // You can add more fields as needed

                                    // Create and add data to pesananArrayList
                                    pesananSelesai.add(new MSelesai(namaProduk, alamatLengkap, totalHarga, gbr));
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
    }