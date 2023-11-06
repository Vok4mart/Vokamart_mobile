package com.example.vokamart.PesananFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.example.vokamart.Adapter.OrderAdapter;
//import com.example.vokamart.Models.Pesanan;
import com.example.vokamart.Adapter.AdapterPesananDikirim;
import com.example.vokamart.Adapter.PesananBaru;
import com.example.vokamart.Models.Mpesanan_baru;
import com.example.vokamart.R;

import java.util.ArrayList;
import java.util.List;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link Dikirim#newInstance} factory method to
// * create an instance of this fragment.
// */
public class Dikirim extends Fragment {

//    List<Pesanan> newOrderItems = filterItemsByStatus(orderList, "new order");
//    OrderAdapter adapter = new OrderAdapter(newOrderItems, "new order");
//recyclerView.setAdapter(adapter);

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public Dikirim() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Dikirim.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static Dikirim newInstance(String param1, String param2) {
//        Dikirim fragment = new Dikirim();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    private AdapterPesananDikirim adapter;
    private ArrayList<Dikirim> pesananArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_pesanan_dikirim, container, false);
        if (pesananArrayList == null) {
            addData();
        }
        View rootView = inflater.inflate(R.layout.fragment_pesanan_dikirim, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_pesanan_dikirim);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        if (adapter == null) {
            adapter = new AdapterPesananDikirim(pesananArrayList);
        }


        // Set the adapter and layoutManager for the RecyclerView
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    private void addData() {
        pesananArrayList = new ArrayList<>();
        pesananArrayList.add(new Dikirim("Donat Kentang", "Banyuwangi", "JNT INDO",1333));
        pesananArrayList.add(new Dikirim("Motherboard", "Bali", "Jnt Bali", 10000));
        pesananArrayList.add(new Dikirim("Sepatu la Tetanus", "aBanas", "asdfdfd", 89999));
        pesananArrayList.add(new Dikirim("Sepatu la Tetanus", "aBanas", "asdfdfd", 89999));
        pesananArrayList.add(new Dikirim("Sepatu la Tetanus", "aBanas", "asdfdfd", 89999));
        pesananArrayList.add(new Dikirim("Sepatu la Tetanus", "aBanas", "asdfdfd", 89999));
        pesananArrayList.add(new Dikirim("Sepatu la Tetanus", "aBanas", "asdfdfd", 89999));
    }
}