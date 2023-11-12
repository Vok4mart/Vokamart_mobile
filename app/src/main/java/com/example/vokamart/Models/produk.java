package com.example.vokamart.Models;

public class produk {
    private String Nama_produk;
    private int harga_produk;
    private int berat;

    public produk(String Nama_produk, int harga_produk, int berat) {
        this.Nama_produk = Nama_produk;
        this.harga_produk = harga_produk;
        this.berat = berat;
    }

    public String getNama() {
        return Nama_produk;
    }

    public void setNama(String nama) {
        this.Nama_produk = nama;
    }

    public int getHarga() {
        return harga_produk;
    }

    public void setHarga(int harga_produk) {
        this.harga_produk = harga_produk;
    }

    public int getStok() {
        return berat;
    }

    public void setStok(int stok) {
        this.berat = berat;
    }
}

