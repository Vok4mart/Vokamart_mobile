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
import com.example.vokamart.Models.MPerluDikirim;
import com.example.vokamart.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterPerluDikirim extends RecyclerView.Adapter<AdapterPerluDikirim.MyViewholder> {

    private ArrayList<MPerluDikirim> mPerluDikirim;
    private static Context context;
    public ClickListener clickListener;

    public interface ClickListener {
        void clicked(MPerluDikirim pesananPerluDikirim);
    }

    public AdapterPerluDikirim(ArrayList<MPerluDikirim> MPerluDikirim, Context context, ClickListener clickListener) {
        this.mPerluDikirim = MPerluDikirim;
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public AdapterPerluDikirim.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_item_pesanan, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        MPerluDikirim pesanan = mPerluDikirim.get(position);

        holder.nama_produk.setText(pesanan.getNama_produk());
        holder.alamat_lengkap.setText(pesanan.getAlamat_lengkap());
        holder.harga_produk.setText("Harga: " + pesanan.getHarga_produk());

//         Periksa apakah imageUrl tidak null sebelum memuat gambar dengan Glide
        if (pesanan.getPesananPerluDikirimImg() != null) {
            String imageUrl = pesanan.getPesananPerluDikirimImg();
            Glide.with(holder.itemView.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.baseline_fastfood_24)
                    .into(holder.imgPerluDikirim);
        } else {
            // Handle case when imageUrl is null
            // Misalnya, set default image atau tampilkan pesan kesalahan
            holder.imgPerluDikirim.setImageResource(R.drawable.baseline_fastfood_24); // Ganti dengan default image Anda
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.clicked(pesanan);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPerluDikirim.size();
    }

    public void FilterPerluDikirim(List<MPerluDikirim> filterList) {
        mPerluDikirim = new ArrayList<>(filterList); // Hindari mengganti referensi langsung
        notifyDataSetChanged();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder {

        TextView nama_produk;
        TextView alamat_lengkap;
        TextView harga_produk;
        ImageView imgPerluDikirim;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            nama_produk = itemView.findViewById(R.id.nama_Pesanan);
            alamat_lengkap = itemView.findViewById(R.id.alamat_pesanan_lengkap);
            harga_produk = itemView.findViewById(R.id.harga_pesanan);
            imgPerluDikirim = itemView.findViewById(R.id.gambar_Pesanan);
        }
    }
}