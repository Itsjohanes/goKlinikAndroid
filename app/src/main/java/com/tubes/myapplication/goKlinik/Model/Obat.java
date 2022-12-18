package com.tubes.myapplication.goKlinik.Model;

import com.google.gson.annotations.SerializedName;

public class Obat {
    @SerializedName("id_obat")
    private String id_obat;
    @SerializedName("nama_obat")
    private String nama_obat;
    @SerializedName("stok_obat")
    private String stok_obat;
    @SerializedName("harga")
    private String harga_obat;
    @SerializedName("keterangan")
    private String keterangan;
    @SerializedName("gambar_obat")
    private String gambar_obat;

    public String getId_obat() {
        return id_obat;
    }
    public void setId_obat(String id_obat) {
        this.id_obat = id_obat;
    }

    public String getNama_obat() {
        return nama_obat;
    }
    public void setNama_obat(String nama_obat) {
        this.nama_obat = nama_obat;
    }

    public String getStok_obat() {
        return stok_obat;
    }
    public void setStok_obat(String stok_obat_obat) {
        this.stok_obat = stok_obat;
    }

    public String getKeterangan() {
        return keterangan;
    }
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getHarga_obat() {
        return harga_obat;
    }
    public void setHarga_obat(String harga_obat) {
        this.harga_obat = harga_obat;
    }

    public String getGambar_obat() {
        return gambar_obat;
    }
    public void setGambar_obat(String gambar_obat) {
        this.gambar_obat = gambar_obat;
    }
}
