package com.example.vokamart.Models;

public class kategoriList {

    private String id_kategori;
    private String nama_kategori;

    public kategoriList(String nama, String id) {
        this.nama_kategori = nama;
        this.id_kategori = id;
    }
    public String getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
    }


    public String getNama_kategori() {
        return nama_kategori;
    }

    public void setNama_kategori(String nama_kategori) {
        this.nama_kategori = nama_kategori;
    }
}
