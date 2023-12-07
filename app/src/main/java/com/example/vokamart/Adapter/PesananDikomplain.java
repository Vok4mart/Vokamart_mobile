package com.example.vokamart.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vokamart.Models.MDikomplain;
import com.example.vokamart.R;

import java.util.ArrayList;

public class PesananDikomplain extends RecyclerView.Adapter<PesananDikomplain.MyViewholder> {

    private final ArrayList<MDikomplain> MDikomplain;

    public PesananDikomplain(ArrayList<MDikomplain> MDikomplain) {
        this.MDikomplain = MDikomplain;
    }

    @NonNull
    @Override
    public PesananDikomplain.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_item_pesanan, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PesananDikomplain.MyViewholder holder, int position) {
        MDikomplain pesanan = MDikomplain.get(position);

        holder.nama_produk.setText(pesanan.getNama_produk());
        holder.alamat_lengkap.setText(pesanan.getAlamat_lengkap());
        holder.kurir.setText(pesanan.getKurir());
        holder.harga_produk.setText("Harga: " + pesanan.getHarga_produk());

    }

    @Override
    public int getItemCount() {
        return MDikomplain.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        TextView nama_produk;
        TextView alamat_lengkap;
        TextView kurir;
        TextView harga_produk;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            nama_produk = itemView.findViewById(R.id.nama_Pesanan);
            alamat_lengkap = itemView.findViewById(R.id.alamat_pesanan_lengkap);
//            kurir = itemView.findViewById(R.id.jenis_kurir);
            harga_produk = itemView.findViewById(R.id.harga_pesanan);
        }
    }
}
