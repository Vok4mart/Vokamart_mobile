package com.example.vokamart.PesananFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< Updated upstream
=======
import android.widget.SearchView;
import android.widget.Toast;
>>>>>>> Stashed changes

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vokamart.Adapter.PesananBaru;
import com.example.vokamart.Models.MPesananBaru;
import com.example.vokamart.R;

import java.util.ArrayList;


public class FPesananBaru extends Fragment {

    private PesananBaru adapter;
    private ArrayList<MPesananBaru> pesananArrayList;
<<<<<<< Updated upstream
=======
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private View rootView;
    private SearchView searchView;
>>>>>>> Stashed changes

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

<<<<<<< Updated upstream
        if (pesananArrayList == null) {
            addData();
=======
        recyclerView = rootView.findViewById(R.id.recycler_pesanan);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        pesananArrayList = new ArrayList<>();
        adapter = new PesananBaru(pesananArrayList, getContext());
        recyclerView.setAdapter(adapter);


        if (getContext() != null) {
            requestQueue = Volley.newRequestQueue(getContext());
            parseJSON();
        } else {
            Toast.makeText(getContext(), "Konteks null", Toast.LENGTH_SHORT).show();
>>>>>>> Stashed changes
        }
        View rootView = inflater.inflate(R.layout.fragment_pesanan_baru, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_pesanan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        if (adapter == null) {
            adapter = new PesananBaru(pesananArrayList);
        }


        // Set the adapter and layoutManager for the RecyclerView
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    private void addData() {
        pesananArrayList = new ArrayList<>();
        pesananArrayList.add(new MPesananBaru("Donat Kentang", "Banyuwangi", "JNT INDO",1333));
        pesananArrayList.add(new MPesananBaru("Motherboard", "Bali", "Jnt Bali", 10000));
        pesananArrayList.add(new MPesananBaru("Sepatu la Tetanus", "aBanas", "asdfdfd", 89999));
        pesananArrayList.add(new MPesananBaru("Sepatu la Tetanus", "aBanas", "asdfdfd", 89999));
        pesananArrayList.add(new MPesananBaru("Sepatu la Tetanus", "aBanas", "asdfdfd", 89999));
        pesananArrayList.add(new MPesananBaru("Sepatu la Tetanus", "aBanas", "asdfdfd", 89999));
        pesananArrayList.add(new MPesananBaru("Sepatu la Tetanus", "aBanas", "asdfdfd", 89999));
    }
}