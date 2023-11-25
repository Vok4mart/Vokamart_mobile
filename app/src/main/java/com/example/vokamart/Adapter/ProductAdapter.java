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
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private static ArrayList<produk> produkArrayList;
    private static Context context;
    public ClickListener clickListener;


    public interface ClickListener{
        void clicked(produk produk);
    }

    public ProductAdapter(Context context, ArrayList<produk> produkArrayList, ClickListener clickListener) {
        this.produkArrayList = produkArrayList;
        this.context = context;
        this.clickListener = clickListener;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.clicked(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return produkArrayList.size();
    }

    public void filterlist(List<produk> filteredList){
        produkArrayList = (ArrayList<produk>) filteredList;
        notifyDataSetChanged();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
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
        }
    }
}