package com.example.vokamart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vokamart.Models.MDikirim;
import com.example.vokamart.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterPesananDikirim extends RecyclerView.Adapter<AdapterPesananDikirim.MyViewholder> {

    private ArrayList<MDikirim> mDikirim;
    private static Context context;
    public ClickListener clickListener;

    public interface ClickListener{
        void clicked(MDikirim MDikirim);

    }

    public AdapterPesananDikirim(ArrayList<MDikirim> MDikirim, Context context, ClickListener clickListener) {
        this.mDikirim = MDikirim;
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public AdapterPesananDikirim.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_item_pesanan, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPesananDikirim.MyViewholder holder, int position) {
        MDikirim pesanan = mDikirim.get(position);

        holder.nama_produk.setText(pesanan.getNama_produk());
        holder.alamat_lengkap.setText(pesanan.getAlamat_lengkap());
//        holder.kurir.setText(pesanan.getKurir());
        holder.harga_produk.setText("Harga: " + pesanan.getHarga_produk());
        String imageUrl = pesanan.getImg_product();
        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.baseline_fastfood_24)
                .into(holder.gbr);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.clicked(pesanan);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDikirim.size();
    }

    public void OrderDikirim(List<MDikirim> filterList){
        mDikirim = (ArrayList<MDikirim>) filterList;
        notifyDataSetChanged();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        TextView nama_produk;
        TextView alamat_lengkap;
        TextView kurir;
        TextView harga_produk;
        ImageView gbr;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            nama_produk = itemView.findViewById(R.id.nama_Pesanan);
            alamat_lengkap = itemView.findViewById(R.id.alamat_pesanan_lengkap);
            kurir = itemView.findViewById(R.id.jenis_kurir);
            harga_produk = itemView.findViewById(R.id.harga_pesanan);
            gbr = itemView.findViewById(R.id.gambar_Pesanan);
        }
    }
}