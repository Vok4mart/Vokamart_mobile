package com.example.vokamart.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vokamart.DetailActivity.DetailProduk;
import com.example.vokamart.Models.produk;
import com.example.vokamart.R;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private static ArrayList<produk> produkArrayList;
    private static Context context;

    public ProductAdapter(Context context, ArrayList<produk> produkArrayList) {
        this.produkArrayList = produkArrayList;
        this.context = context;
    }

    @Override
    public ProductViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        produk product = produkArrayList.get(position);

        holder.ProductName.setText(product.getNama());
        holder.ProductPrice.setText("Price: " + product.getHarga());
        holder.ProductStock.setText("Stock: " + product.getStok());
        Glide.with(holder.itemView.getContext()).load(product.getImageUrl()).into(holder.imageProduct);
    }

    @Override
    public int getItemCount() {
        return produkArrayList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView ProductName;
        TextView ProductPrice;
        TextView ProductStock;
        ImageView imageProduct;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            ProductName = itemView.findViewById(R.id.nama_produk);
            ProductPrice = itemView.findViewById(R.id.harga_produk);
            ProductStock = itemView.findViewById(R.id.stok_produk);
            imageProduct = itemView.findViewById(R.id.gambar_produk);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent intent = new Intent(context, DetailProduk.class);
            intent.putExtra("ProductName", produkArrayList.get(position).getNama());
            context.startActivity(intent);

        }
    }
}
