package com.example.vokamart.PesananFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vokamart.Adapter.AdapterPerluDikirim;
import com.example.vokamart.Models.MPerluDikirim;
import com.example.vokamart.R;

import java.util.ArrayList;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link PerluDikirim#newInstance} factory method to
// * create an instance of this fragment.
// */
public class PerluDikirim extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public PerluDikirim() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment PerluDikirim.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static PerluDikirim newInstance(String param1, String param2) {
//        PerluDikirim fragment = new PerluDikirim();
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


    private AdapterPerluDikirim adapter;
    private ArrayList<MPerluDikirim> pesananArrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_pesanan_perlu_dikirim, container, false);

        if (pesananArrayList == null) {
            addData();
        }

        View rootView = inflater.inflate(R.layout.fragment_pesanan_perlu_dikirim, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_pesanan_perlu_dikirim);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        if (adapter == null) {
            adapter = new AdapterPerluDikirim(pesananArrayList);
        }


        // Set the adapter and layoutManager for the RecyclerView
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    private void addData() {
        pesananArrayList = new ArrayList<>();
        pesananArrayList.add(new MPerluDikirim("Donat Kentang", "Banyuwangi", "JNT INDO",1333));
        pesananArrayList.add(new MPerluDikirim("Motherboard", "Bali", "Jnt Bali", 10000));
        pesananArrayList.add(new MPerluDikirim("Sepatu la Tetanus", "aBanas", "asdfdfd", 89999));
        pesananArrayList.add(new MPerluDikirim("Sepatu la Tetanus", "aBanas", "asdfdfd", 89999));
        pesananArrayList.add(new MPerluDikirim("Sepatu la Tetanus", "aBanas", "asdfdfd", 89999));
        pesananArrayList.add(new MPerluDikirim("Sepatu la Tetanus", "aBanas", "asdfdfd", 89999));
        pesananArrayList.add(new MPerluDikirim("Sepatu la Tetanus", "aBanas", "asdfdfd", 89999));
    }
}