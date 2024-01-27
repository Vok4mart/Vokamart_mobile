package com.example.vokamart.Models;

import java.io.Serializable;

public class produk implements Serializable {
    private String id;
    private String nama;
    private int harga;
    private int stok;
    private String deskripsi_produk;
    private String MimageUrl;
    private int berat;
    private String id_katergori;
    private String nama_kategori;



    public produk(String id_produk, String nama, int harga, int stok, String deskripsiProduk, String MimageUrl, int berat, String id_kategori, String nama_kategori) {
        this.id = id_produk;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
        this.deskripsi_produk = deskripsiProduk;
        this.MimageUrl = MimageUrl;
        this.berat = berat;
        this.id_katergori = id_kategori;
        this.nama_kategori = nama_kategori;
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

    public String getMimageUrl() {
        return MimageUrl;
    }

    public void setMimageUrl(String mimageUrl) {
        this.MimageUrl = mimageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBerat() {
        return berat;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }

    public String getId_katergori() {
        return id_katergori;
    }

    public void setId_katergori(String id_katergori) {
        this.id_katergori = id_katergori;
    }

    public String getNama_kategori() {
        return nama_kategori;
    }

    public void setNama_kategori(String nama_kategori) {
        this.nama_kategori = nama_kategori;
    }
}