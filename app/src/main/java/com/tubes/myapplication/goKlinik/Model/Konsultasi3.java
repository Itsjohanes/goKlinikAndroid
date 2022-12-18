package com.tubes.myapplication.goKlinik.Model;

import com.google.gson.annotations.SerializedName;

public class Konsultasi3 {

    @SerializedName("id_selesai")
    private String id_selesai;
    @SerializedName("id_pasien")
    private String id_pasien;
    @SerializedName("nama_pasien")
    private String nama_pasien;
    @SerializedName("jadwal")
    private String jadwal;
    @SerializedName("jam")
    private  String jam;
    @SerializedName("nama_dokter")
    private  String nama_dokter;
    @SerializedName("nohp")
    private  String nohp;

    public Konsultasi3(){}

    public Konsultasi3(String id_selesai, String id_pasien, String nama_pasien, String jadwal, String jam, String nama_dokter, String nohp) {
        this.id_selesai = id_selesai;
        this.id_pasien = id_pasien;
        this.nama_pasien = nama_pasien;
        this.jadwal = jadwal;
        this.jam = jam;
        this.nama_dokter = nama_dokter;
        this.nohp = nohp;
    }

    public String getId_selesai() {
        return id_selesai;
    }
    public void setId_selesai(String id_selesai) {
        this.id_selesai = id_selesai;
    }

    public String getId_pasien() {
        return id_pasien;
    }
    public void setId_pasien(String id_pasien) {
        this.id_pasien = id_pasien;
    }

    public String getNama_pasien() {
        return nama_pasien;
    }
    public void setNama_pasien(String nama_pasien) {
        this.nama_pasien = nama_pasien;
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

    public String getNama_dokter() {
        return nama_dokter;
    }
    public void setNama_dokter(String nama_dokter) {
        this.nama_dokter = nama_dokter;
    }

    public String getNohp() {
        return nohp;
    }
    public void setNohp(String nohp) {
        this.nohp = nohp;
    }
}
