package com.tubes.myapplication.goKlinik.Model;

import com.google.gson.annotations.SerializedName;

public class Konsultasi2 {
    @SerializedName("id_jadwal")
    private String id_jadwal;
    @SerializedName("status")
    private String status;
    @SerializedName("id_pasien")
    private String id_pasien;
    @SerializedName("id_konsultasi")
    private String id_konsultasi;
    @SerializedName("antrian")
    private String antrian;
    @SerializedName("nama_dokter")
    private  String nama_dokter;
    @SerializedName("jadwal")
    private  String jadwal;
    @SerializedName("jam")
    private  String jam;
    public Konsultasi2(){}

    public Konsultasi2(String id_jadwal, String status, String id_pasien, String id_konsultasi, String antrian, String nama_dokter, String jadwal, String jam) {
        this.id_jadwal = id_jadwal;
        this.status = status;
        this.id_pasien = id_pasien;
        this.id_konsultasi = id_konsultasi;
        this.antrian = antrian;
        this.nama_dokter = nama_dokter;
        this.jadwal = jadwal;
        this.jam = jam;
    }

    public String getId_jadwal() {
        return id_jadwal;
    }
    public void setId_jadwal(String id_jadwal) {
        this.id_jadwal = id_jadwal;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getId_pasien() {
        return id_pasien;
    }
    public void setId_pasien(String id_pasien) {
        this.id_pasien = id_pasien;
    }

    public String getId_konsultasi() {
        return id_konsultasi;
    }
    public void setId_konsultasi(String id_konsultasi) {
        this.id_konsultasi = id_konsultasi;
    }

    public String getAntrian() {
        return antrian;
    }
    public void setAntrian(String antrian) {
        this.antrian = antrian;
    }

    public String getNama_dokter() {
        return nama_dokter;
    }
    public void setNama_dokter(String nama_dokter) {
        this.nama_dokter = nama_dokter;
    }

    public String getJadwal() {
        return jadwal;
    }
    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }

    public String getJam() {
        return jam;
    }
    public void setJam(String jam) {
        this.jam = jam;
    }
}
