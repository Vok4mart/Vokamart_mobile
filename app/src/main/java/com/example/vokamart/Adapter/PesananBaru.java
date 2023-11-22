package com.example.vokamart.Adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vokamart.DetailActivity.DetailPesanan;
import com.example.vokamart.DetailActivity.DetailProduk;
import com.example.vokamart.Models.MPesananBaru;
import com.example.vokamart.R;

import java.util.ArrayList;

public class PesananBaru extends RecyclerView.Adapter<PesananBaru.MyViewHolder>{

    private static ArrayList<MPesananBaru> MPesananBaru;
    private static Context context;

    public PesananBaru(ArrayList<MPesananBaru> Mpesanan_baru, Context context) {
        this.MPesananBaru = Mpesanan_baru;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = LayoutInflater.from(context).inflate(R.layout.card_item_pesanan, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MPesananBaru pesanan = MPesananBaru.get(position);

        holder.nama_produk.setText(pesanan.getNama_produk());
        holder.alamat_lengkap.setText(pesanan.getAlamat_lengkap());
//        holder.kurir.setText(pesanan.getKurir());
        holder.harga_produk.setText("Harga: " + pesanan.getHarga_produk());
    }

    @Override
    public int getItemCount() {
        return MPesananBaru.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nama_produk;
        TextView alamat_lengkap;
//        TextView kurir;
        TextView harga_produk;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nama_produk = itemView.findViewById(R.id.nama_Pesanan);
            alamat_lengkap = itemView.findViewById(R.id.alamat_pesanan_lengkap);
//            kurir = itemView.findViewById(R.id.jenis_kurir);
            harga_produk = itemView.findViewById(R.id.harga_pesanan);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent intent = new Intent(context, DetailPesanan.class);
            intent.putExtra("nama_produk", MPesananBaru.get(position).getNama_produk());
            context.startActivity(intent);
        }
    }
}