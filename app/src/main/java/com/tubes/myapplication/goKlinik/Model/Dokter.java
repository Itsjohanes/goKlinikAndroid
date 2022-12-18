package com.tubes.myapplication.goKlinik.Model;

import com.google.gson.annotations.SerializedName;

public class Dokter {
    @SerializedName("id_dokter")
    private String id_dokter;
    @SerializedName("nama_dokter")
    private String nama_dokter;
    @SerializedName("spesialis")
    private String spesialis;
    @SerializedName("keterangan")
    private String keterangan;
    @SerializedName("foto_dokter")
    private String foto_dokter;

    public Dokter(){}

    public Dokter(String id_dokter, String nama_dokter, String spesialis, String keterangan, String foto_dokter) {
        this.id_dokter = id_dokter;
        this.nama_dokter = nama_dokter;
        this.spesialis = spesialis;
        this.keterangan = keterangan;
        this.foto_dokter = foto_dokter;
    }

    public String getId() {
        return id_dokter;
    }
    public void setId(String id_dokter) {
        this.id_dokter= id_dokter;
    }

    public String getNama_dokter() {
        return nama_dokter;
    }
    public void setNama_dokter(String nama_dokter) {
        this.nama_dokter = nama_dokter;
    }

    public String getSpesialis() {
        return spesialis;
    }
    public void setSpesialis(String spesialis) {
        this.spesialis = spesialis;
    }

    public String getKeterangan() {
        return keterangan;
    }
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getFoto_dokter() {
        return foto_dokter;
    }
    public void setFoto_dokter(String foto_dokter) {
        this.foto_dokter = foto_dokter;
    }
}
