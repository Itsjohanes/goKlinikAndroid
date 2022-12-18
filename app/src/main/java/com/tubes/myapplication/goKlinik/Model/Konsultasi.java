package com.tubes.myapplication.goKlinik.Model;

import com.google.gson.annotations.SerializedName;

public class Konsultasi {

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
    public Konsultasi(){}

    public Konsultasi(String id_jadwal, String status, String id_pasien,String id_konsultasi,String antrian) {
            this.id_jadwal = id_jadwal;
            this.status = status;
            this.id_pasien = id_pasien;
            this.antrian = antrian;
            this.id_konsultasi = id_konsultasi;
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
    public String getAntrian() {
        return antrian;
    }

    public void setAntrian(String antrian) {
        this.antrian = antrian;
    }
    public void setId_konsultasi(String id_konsultasi) {
        this.id_konsultasi = id_konsultasi;
    }
}
