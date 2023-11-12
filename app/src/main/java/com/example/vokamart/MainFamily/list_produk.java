package com.example.vokamart.MainFamily;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.vokamart.Adapter.ProductAdapter;
import com.example.vokamart.Models.produk;
import com.example.vokamart.R;
import com.example.vokamart.RetrofitClient.RetrofitProduct;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class list_produk extends Fragment {
    private ProductAdapter adapter;
    private ArrayList<produk> produkArrayList = new ArrayList<>();
//    private ArrayList<produk> produkArrayList;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main_list_produk, container, false);

        // Inisialisasi recyclerView
        recyclerView = rootView.findViewById(R.id.recycler_list_produk);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ProductAdapter(produkArrayList);
        recyclerView.setAdapter(adapter);

        fetchproduk();

        // Initialize the adapter if it hasn't been already
//        if (adapter == null) {
//            adapter = new ProductAdapter(produkArrayList);
//        }
//
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);

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

    private void fetchproduk() {
        RetrofitProduct.getRetrofitClient().getproduk().enqueue(new Callback<List<produk>>() {
            @Override
            public void onResponse(Call<List<produk>> call, Response<List<produk>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    produkArrayList.addAll(response.body());

                    // Initialize the adapter here after data is fetched
                    adapter = new ProductAdapter(produkArrayList);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<produk>> call, Throwable t) {
                Log.e("API Error", "Error fetching data", t);
                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    }








//    private void addData() {
//        produkArrayList = new ArrayList<>();
//        produkArrayList.add(new produk("Donat Kentang", 121821, 12));
//        produkArrayList.add(new produk("Motherboard", 1300000, 5));
//        produkArrayList.add(new produk("Sepatu la Tetanus", 250000, 8));
//        produkArrayList.add(new produk("Donat Kentang", 121821, 12));
//        produkArrayList.add(new produk("Motherboard", 1300000, 5));
//        produkArrayList.add(new produk("Sepatu la Tetanus", 250000, 8));
//        produkArrayList.add(new produk("Donat Kentang", 121821, 12));
//        produkArrayList.add(new produk("Motherboard", 1300000, 5));
//    }
