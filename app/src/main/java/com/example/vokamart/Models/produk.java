package com.example.vokamart.Models;

import java.io.Serializable;

public class produk implements Serializable {
    private String nama;
    private int harga;
    private int stok;
    private String deskripsi_produk;

    private String imageUrl;

    public produk(String nama, int harga, int stok, String deskripsiProduk, String imageUrl) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
        this.deskripsi_produk = deskripsiProduk;
        this.imageUrl = imageUrl;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public String getDeskripsi_produk() {
        return deskripsi_produk;
    }

    public void setDeskripsi_produk(String deskripsi_produk) {
        this.deskripsi_produk = deskripsi_produk;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}