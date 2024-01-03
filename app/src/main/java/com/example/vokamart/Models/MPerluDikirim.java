package com.example.vokamart.Models;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;

public class MPerluDikirim implements Serializable {
    private String nama_produk, alamat_lengkap, kurir;
    private int harga_produk;
    private String pesananPerluDikirimImg;

    public String getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanan(String idPesanan) {
        this.idPesanan = idPesanan;
    }

    private String idPesanan;


    public MPerluDikirim(String nama_produk, String alamat_lengkap, int harga_produk, String pesananPerluDikirimImg, String idPesanan){
        this.nama_produk = nama_produk;
        this.alamat_lengkap = alamat_lengkap;
        this.kurir = kurir;
        this.harga_produk =harga_produk;
        this.pesananPerluDikirimImg = pesananPerluDikirimImg;
        this.idPesanan = idPesanan;
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

    public String getKurir() {
        return kurir;
    }

    public void setKurir(String kurir) {
        this.kurir = kurir;
    }

    public int getHarga_produk() {
        return harga_produk;
    }

    public void setHarga_produk(int harga_produk) {
        this.harga_produk = harga_produk;
    }

    public String getPesananPerluDikirimImg() {
        return pesananPerluDikirimImg;
    }

    public void setPesananPerluDikirimImg(String pesananPerluDikirimImg) {
        this.pesananPerluDikirimImg = pesananPerluDikirimImg;
    }
}
