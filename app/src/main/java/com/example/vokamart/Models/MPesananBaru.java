package com.example.vokamart.Models;

import java.io.Serializable;

public class MPesananBaru implements Serializable {
    private String nama_produk, alamat_lengkap;
    private int harga_produk;
    private String pesananBaruImg;

    public MPesananBaru(String nama_produk, String alamat_lengkap, int harga_produk, String pesananBaruImg){
        this.nama_produk = nama_produk;
        this.alamat_lengkap = alamat_lengkap;
        this.harga_produk =harga_produk;
        this.pesananBaruImg = pesananBaruImg;
    }


    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public String getAlamat_lengkap() {
        return alamat_lengkap;
    }

    public void setAlamat_lengkap(String alamat_lengkap) {
        this.alamat_lengkap = alamat_lengkap;
    }

    public int getHarga_produk() {
        return harga_produk;
    }

    public void setHarga_produk(int harga_produk) {
        this.harga_produk = harga_produk;
    }

    public String getPesananBaruImg() {
        return pesananBaruImg;
    }

    public void setPesananBaruImg(String pesananBaruImg) {
        this.pesananBaruImg = pesananBaruImg;
    }
}
