package com.example.vokamart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vokamart.Models.DetailPesanan;
import com.example.vokamart.R;

import java.util.ArrayList;

public class DetailPesananBaruAdapter extends RecyclerView.Adapter<DetailPesananBaruAdapter.MyViewholder> {
    private  final ArrayList<DetailPesanan> pesanan;
    Context context;

    public DetailPesananBaruAdapter(ArrayList<DetailPesanan> pesanan) {
        this.pesanan = pesanan;
        this.context = context;
    }

    @NonNull
    @Override
    public DetailPesananBaruAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_item_detail_pesanan, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailPesananBaruAdapter.MyViewholder holder, int position) {
        DetailPesanan model = pesanan.get(position);

        String image = model.getImgPesananBaru();
        Glide.with(holder.itemView.getContext())
                .load(image)
                .placeholder(R.drawable.baseline_fastfood_24)
                .into(holder.imgPesanan);
    }

    @Override
    public int getItemCount() {
        return pesanan.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        ImageView imgPesanan;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            imgPesanan = itemView.findViewById(R.id.gambar_produk_pesanan);
        }
    }
}
