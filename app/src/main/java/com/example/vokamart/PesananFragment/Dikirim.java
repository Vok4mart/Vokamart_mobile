package com.example.vokamart.PesananFragment;

import android.os.Bundle;

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

import com.example.vokamart.Models.MDikirim;

import com.example.vokamart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Dikirim extends Fragment {

    private AdapterPesananDikirim adapter;
    private ArrayList<MDikirim> pesananDikirim;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_pesanan_dikirim, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_pesanan);

        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);

            pesananDikirim = new ArrayList<>();
            adapter = new AdapterPesananDikirim(pesananDikirim, getContext());
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

        return rootView;
    }

    private void parseJSON() {
        String url = "https://vok4mart.000webhostapp.com/ApiPesananDikirim.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Clear existing data in pesananArrayList
                            pesananDikirim.clear();

                            // Parse pesanan data
                            if (response != null) {
                                JSONArray jsonArray = response.getJSONArray("data");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject hit = jsonArray.getJSONObject(i);

                                    String namaProduk = hit.getString("Nama_produk");
                                    String alamatLengkap = hit.getString("alamat_lengkap"); // Ganti dengan nama kolom yang sesuai
                                    int totalHarga = hit.getInt("sub_total");
                                    // You can add more fields as needed

                                    // Create and add data to pesananArrayList
                                    pesananDikirim.add(new MDikirim(namaProduk, alamatLengkap, totalHarga));
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