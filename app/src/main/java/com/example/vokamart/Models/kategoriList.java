package com.example.vokamart.Models;

public class kategoriList {

    private String id_kategori;
    public String getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
    }


    private String nama_kategori;
    public kategoriList(String nama) {
        this.nama_kategori = nama;
    }

    public String getNama_kategori() {
        return nama_kategori;
    }

    public void setNama_kategori(String nama_kategori) {
        this.nama_kategori = nama_kategori;
    }
}
