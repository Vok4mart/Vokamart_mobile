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
import com.example.vokamart.Adapter.AdapterPerluDikirim;
import com.example.vokamart.DetailActivity.DetailPesananPerluDikirim;
import com.example.vokamart.Models.MPerluDikirim;
import com.example.vokamart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PerluDikirim extends Fragment {

    private AdapterPerluDikirim adapterPerluDikirim;
    private ArrayList<MPerluDikirim> arrayPerluDikirim;
    private View rootView;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private SearchView search2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_pesanan_perlu_dikirim, container, false);

        search2 = rootView.findViewById(R.id.cari_pesanan_perlu_dikirim);
        recyclerView = rootView.findViewById(R.id.recycler_pesanan_perlu_dikirim);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        arrayPerluDikirim = new ArrayList<>();
        adapterPerluDikirim = new AdapterPerluDikirim(arrayPerluDikirim, getContext(), this::cliked);
        recyclerView.setAdapter(adapterPerluDikirim);

        if (getContext() != null) {
            requestQueue = Volley.newRequestQueue(getContext());
            parseJSON();
        } else {
            Toast.makeText(getContext(), "Konteks null", Toast.LENGTH_SHORT).show();
        }

        search2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                FilterPerluDikirim(newText);
                return false;
            }

            private void FilterPerluDikirim(String newText) {
                List<MPerluDikirim> filterList = new ArrayList<>();
                for (MPerluDikirim item : arrayPerluDikirim){
                    if (item.getNama_produk().toLowerCase().contains(newText.toLowerCase())){
                        filterList.add(item);
                    }
                }
                adapterPerluDikirim.FilterPerluDikirim(filterList);
            }
        });
        return rootView;
    }

    private void cliked(MPerluDikirim mPerluDikirim) {
        Intent intent = new Intent(getActivity(), DetailPesananPerluDikirim.class);
        intent.putExtra("dataPesananPerluDikirim", mPerluDikirim);
        startActivity(intent);
    }

    private void parseJSON() {
        String url = "https://vok4mart.000webhostapp.com/ApiPesananPerluDikirim.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Clear existing data in pesananArrayList
                            arrayPerluDikirim.clear();

                            // Parse pesanan data
                            if (response != null) {
                                JSONArray jsonArray = response.getJSONArray("data");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject hit = jsonArray.getJSONObject(i);

                                    String namaProduk = hit.getString("Nama_produk");
                                    String alamatLengkap = hit.getString("alamat_lengkap"); // Ganti dengan nama kolom yang sesuai
                                    int totalHarga = hit.getInt("sub_total");
                                    String pesananPerluDikirimImg = hit.getString("gbr_produk");
                                    String idPesanan = hit.getString("id_pesanan");
                                    // You can add more fields as needed

                                    // Create and add data to pesananArrayList
                                    arrayPerluDikirim.add(new MPerluDikirim(namaProduk, alamatLengkap, totalHarga, pesananPerluDikirimImg, idPesanan));
                                }

                                // Notify the adapter that the data has changed
                                adapterPerluDikirim.notifyDataSetChanged();
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