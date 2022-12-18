package com.tubes.myapplication.goKlinik.Model;

import com.google.gson.annotations.SerializedName;

public class Jadwal {
    @SerializedName("id_dokter")
    private String id_dokter;
    @SerializedName("id_jadwal")
    private String id;
    @SerializedName("jadwal")
    private String jadwal;
    @SerializedName("jam")
    private String jam;

    public Jadwal(){}

    public Jadwal(String id_dokter, String id, String jadwal, String jam) {
        this.id_dokter = id_dokter;
        this.id = id;
        this.jadwal = jadwal;
        this.jam = jam;
    }

    public String getId_dokter() {
        return id_dokter;
    }
    public void setId_dokter(String id_dokter) {
        this.id_dokter = id_dokter;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
