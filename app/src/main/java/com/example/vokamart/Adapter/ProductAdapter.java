package com.example.vokamart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vokamart.MainFamily.list_produk;
import com.example.vokamart.Models.produk;
import com.example.vokamart.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private ArrayList<produk> produkArrayList;
private Context context;

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
    }

    @Override
    public int getItemCount() {
        return produkArrayList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView ProductName;
        TextView ProductPrice;
        TextView ProductStock;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            ProductName = itemView.findViewById(R.id.nama_produk);
            ProductPrice = itemView.findViewById(R.id.harga_produk);
            ProductStock = itemView.findViewById(R.id.stok_produk);
        }
    }
}
