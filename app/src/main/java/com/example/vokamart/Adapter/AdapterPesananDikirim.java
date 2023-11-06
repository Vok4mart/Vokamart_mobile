package com.example.vokamart.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vokamart.Models.Dikirim;
import com.example.vokamart.R;

import java.util.ArrayList;

public class AdapterPesananDikirim extends RecyclerView.Adapter<AdapterPesananDikirim.MyViewholder> {

    private final ArrayList<Dikirim> Dikirim;

    public AdapterPesananDikirim(ArrayList<Dikirim> dikirim) {
        this.Dikirim = dikirim;
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
        Dikirim pesanan = Dikirim.get(position);

        holder.nama_produk.setText(pesanan.getNama_produk());
        holder.alamat_lengkap.setText(pesanan.getAlamat_lengkap());
        holder.kurir.setText(pesanan.getKurir());
        holder.harga_produk.setText("Harga: " + pesanan.getHarga_produk());
    }

    @Override
    public int getItemCount() {
        return Dikirim.size();
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
            kurir = itemView.findViewById(R.id.jenis_kurir);
            harga_produk = itemView.findViewById(R.id.harga_pesanan);
        }
    }
}
