package com.example.vokamart.Models;

public class DetailPesanan {

    public String getImgPesananBaru() {
        return imgPesananBaru;
    }

    public void setImgPesananBaru(String imgPesananBaru) {
        this.imgPesananBaru = imgPesananBaru;
    }

    public String getImgPerluDikirim() {
        return ImgPerluDikirim;
    }

    public void setImgPerluDikirim(String imgPerluDikirim) {
        ImgPerluDikirim = imgPerluDikirim;
    }

    public String getImgDikirim() {
        return ImgDikirim;
    }

    public void setImgDikirim(String imgDikirim) {
        ImgDikirim = imgDikirim;
    }

    public String getImgSelesai() {
        return ImgSelesai;
    }

    public void setImgSelesai(String imgSelesai) {
        ImgSelesai = imgSelesai;
    }

    public DetailPesanan(String imgPesananBaru, String imgPerluDikirim, String imgDikirim, String imgSelesai) {
        this.imgPesananBaru = imgPesananBaru;
        ImgPerluDikirim = imgPerluDikirim;
        ImgDikirim = imgDikirim;
        ImgSelesai = imgSelesai;
    }

    String imgPesananBaru, ImgPerluDikirim, ImgDikirim, ImgSelesai;
}